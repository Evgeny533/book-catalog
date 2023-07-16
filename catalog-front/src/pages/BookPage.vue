<template>
  <div>
    <my-button @click="$router.go(-1)">Назад</my-button>

    <div v-if="isLoadingBookInfo">Loading...</div>

    <div v-else-if="errorMessage">{{ errorMessage }}</div>

    <div v-else>
      <h1>{{ book.title }} ({{ $route.params.id }})</h1>

      <div><strong>Название:</strong> {{ book.title }}</div>

      <div>Авторы</div>
      <author-list :authors="book.authors" />
    </div>
  </div>
</template>

<script>
import axios from "axios";
import AuthorList from "@/components/AuthorList";

export default {
  components:{
    AuthorList
  },
  data() {
    return {
      isLoadingBookInfo: false,
      errorMessage: null,
      book: {
        id: null,
        title: null,
        authors: []
      }
    };
  },
  created() {
    this.book.id = this.$route.params.id;
    this.getBook();
  },
  methods: {
    getBook() {
      this.isLoadingBookInfo = true;
      this.errorMessage = null;

      axios.get(`http://localhost:8085/api/v1/books/getBookById?id=${this.book.id}`)
        .then((response) => {
          this.book.title = response.data.data.title;
          this.book.authors = response.data.data.author;
          this.isLoadingBookInfo = false;
        }).catch((error) => {
          this.errorMessage = error;
        });
    }
  }
}
</script>

<style scoped></style>
