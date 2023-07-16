<template>
  <div>
    <my-button @click="$router.go(-1)">Назад</my-button>

    <div v-if="isLoadingAuthorInfo">Loading...</div>

    <div v-else-if="errorMessage">{{ errorMessage }}</div>

    <div v-else>
      <h1>{{ author.name }} ({{ $route.params.id }})</h1>

      <div><strong>ФИО:</strong> {{ author.name }}</div>

      <div>Книги</div>
      
      <book-list :books="author.books" />

    </div>
  </div>
</template>

<script>
import axios from "axios";
import BookList from "@/components/BookList";

export default {
  components: { BookList },
  data() {
    return {
      isLoadingAuthorInfo: false,
      errorMessage: null,
      author: {
        id: null,
        name: null,
        books: []
      }
    };
  },
  created() {
    this.author.id = this.$route.params.id;
    this.getAuthor();
  },
  methods: {
    getAuthor() {
      this.isLoadingAuthorInfo = true;
      this.errorMessage = null;

      axios.get(`http://localhost:8085/api/v1/author/getAuthorById?id=${this.author.id}`)
        .then((response) => {
          this.author.name = response.data.data.name;
          this.author.books = response.data.data.books;
          this.isLoadingAuthorInfo = false;
        }).catch((error) => {
          this.errorMessage = error;
        });
    }
  }
}
</script>

<style scoped></style>
