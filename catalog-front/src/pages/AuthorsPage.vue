<template>
    <div>
        <h1>Страница авторами</h1>
        <my-button v-if="!isShowAddForm" @click="isShowAddForm = true">Добавить</my-button>

        <div v-else>
            <label>Введите ФИО нового автора:</label>
            <input v-model="newAuthorName" :disable="isLoadingAddAuthor" />
            <btn @click="addAuthor" :disable="isLoadingAddAuthor">Сохранить</btn>

            <div v-if="isLoadingAddAuthor">Loading....</div>

            <div v-if="errorAddAuthor">{{ errorAddAuthor }}</div>
        </div>
        <div>
            <input @input="handleInput($event.target.value)" placeholder="Введите ФИО для поиска" />
        </div>
        <author-list :authors="this.authors" />

    </div>
</template>


<script>
import axios from "axios";
import AuthorList from "@/components/AuthorList";
import { debounce } from 'lodash';
import MyButton from "@/components/UI/MyButton";
export default {
    components: { AuthorList, MyButton },
    data() {
        return {
            authors: [],
            isShowAddForm: false,
            newAuthorName: null,
            isLoadingAddAuthor: false,
            errorAddAuthor: null,
            isPostsLoading: false,
        }
    },

    methods: {
        async fetchPosts() {
            console.log('начало запроса');
            try {

                this.isPostsLoading = true;
                const response = await axios.get('http://localhost:8085/api/v1/author/getAll', {

                });
    
                console.log(response.data);
                this.authors = response.data.data;
            } catch (e) {
                alert('Ошибка')
            } finally {
                this.isPostsLoading = false;
            }
        },
        addAuthor() {
            this.isLoadingAddAuthor = true;
            this.errorAddAuthor = null;

            axios.post(`http://localhost:8085/api/v1/author/addAuthor`, {
                name: this.newAuthorName
            })
                .then(() => {
                    this.isShowAddForm = false;
                    this.isLoadingAuthorInfo = false;

                    this.fetchPosts();
                }).catch((error) => {
                    this.errorAddAuthor = error;
                });
        },
        handleInput: debounce(function (name) {
            if (name) {
                this.fetchAuthorsByName(name);
            }
            else
                this.fetchPosts();
        }, 500),
        fetchAuthorsByName(name) {
            axios.get(`http://localhost:8085/api/v1/author/getAuthorByName?name=${name}`)
                .then((response) => {
                
                    this.authors = response.data.data;
                }).catch((error) => {
                    this.errorMessage = error;
                });
        }
    }

    ,
    mounted() {
        this.fetchPosts();
    }

    
}
</script>


<style scoped>
.user-list-item {
    display: inline-block;
    margin-right: 10px;
}

.user-list-enter-active,
.user-list-leave-active {
    transition: all 0.4s ease;
}

.user-list-enter-from,
.user-list-leave-to {
    opacity: 0;
    transform: translateX(130px);
}

.user-list-move {
    transition: transform 0.4s ease;
}
</style>
