<template>
  <div>
      <h1>Главная</h1>
   
      <!-- <my-button v-if="!isShowAddForm" @click="$router.push('addBook')">Добавить</my-button>        -->

      <!-- <div>
          <br>
          <input @input="handleInput($event.target.value)" placeholder="Введите название для поиска" />
      </div> -->
      <book-list :books="this.books" />
  </div>
</template>


<script>
import axios from "axios";
import BookList from "@/components/BookList";
// import { debounce } from 'lodash';

export default {
  components: { BookList },
  data() {
      return {
          books: [],
          isShowAddForm: false,
          newBookName: null,
          isLoadingAddBook: false,
          errorAddBook: null,
          isBooksLoading: false,
      }
  },

  methods: {
      async fetchBooks(page, booksPerPage) {
          console.log('начало запроса');
          try {

              this.isPostsLoading = true;
              const response = await axios.get(`http://localhost:8085/api/v1/books/getAll?page=${page}&booksPerPage=${booksPerPage}`
              , {

              });
             
              console.log(response.data);
              this.books = response.data.data;
          } catch (e) {
              alert('Ошибка')
          } finally {
              this.isPostsLoading = false;
          }
      }
  }

  ,
  mounted() {
      this.fetchBooks(0, 10);
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
