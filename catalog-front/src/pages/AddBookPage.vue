<template>
  <div>
    <my-button @click="$router.go(-1)">Назад</my-button>

    <div v-if="isLoadingBookInfo">Loading...</div>

    <div v-else-if="errorMessage">{{ errorMessage }}</div>

    <div v-else>
      <div>
        <strong>Название:</strong>
        <input v-model="book.title" placeholder="Введите название книги" />
      </div>

      <div><strong>Авторы:</strong></div>
    </div>
    <author-list :authors="book.author" />
    <br />
    <my-button @click="addBook">Добавить книгу</my-button>
    <br />
    <br />
    <input
      @input="handleInput($event.target.value)"
      placeholder="Введите ФИО для поиска"
    />
    <add-author-list
      @data-updated-to-parent="handleDataUpdate"
      :authors1="this.authors"
    />
  </div>
</template>

<script>
import axios from "axios";
import AddAuthorList from "@/components/addBook/AddAuthorList";
import AuthorList from "@/components/AuthorList";
import { debounce } from "lodash";

export default {
  components: {
    AddAuthorList,
    AuthorList,
  },
  data() {
    return {
      isLoadingBookInfo: false,
      errorMessage: null,
      book: {
        id: null,
        title: null,
        author: [],
      },
      authors: [],
      nameAuthorForSearch: "",
      errorAddBook:""
    };
  },
  created() {
  
    this.fetchAuthorsByName(this.nameAuthorForSearch);
  },
  methods: {
    addBook() {
      console.log(this.book);
   
      axios
        .post(`http://localhost:8085/api/v1/books/addBook`, this.book, {
          headers: {
      
            "Content-Type": "application/json",
          },
        })
        .then((response) => {
          console.log(response);
          
          if (response.status == 207) {
            //this.errorAddAuthor = error;
          }
         
          if (response.status == 200) {
            this.book.id = response.data.data;
          }
        })
      .catch((error) => {
         this.errorAddAuthor = error;
      });
    },
  
    handleInput: debounce(function (name) {
      if (name) {
        this.fetchAuthorsByName(name);
      } else this.fetchPosts();
    }, 500),

    fetchAuthorsByName(name) {
      axios
        .get(`http://localhost:8085/api/v1/author/getAuthorByName?name=${name}`)
        .then((response) => {
      
          this.isLoadingBookInfo = false;
          this.authors = response.data.data;
        })
        .catch((error) => {
          this.errorMessage = error;
        });
    },
    handleDataUpdate(author) {
      console.log("AddBookPage");
      console.log(author);
      console.log(this.book.author);
      this.book.author.push(author);
      console.log(this.book.author);
    },
    async fetchPosts() {
      console.log("начало запроса");
      try {
        this.isPostsLoading = true;
        const response = await axios.get(
          "http://localhost:8085/api/v1/author/getAll",
          {}
        );
   
        this.authors = response.data.data;
      } catch (e) {
        alert("Ошибка");
      } finally {
        this.isPostsLoading = false;
      }
    },
  },
 
 
};
</script>

<style scoped></style>
