<template>
  <div class="post">
    <div>
      <div>{{ book.id }}</div>
    
      <div @click="$router.push(`/books/${book.id}`)"><strong>Название:</strong> {{ book.title }}</div>

    </div>
  
  </div>
</template>
  
<script>
export default {
  props: {
    book: {
      type: Object,
      required: true,
    }
  }
}
</script>
  
<style scoped>
.post {
  padding: 15px;
  border: 2px solid teal;
  margin-top: 15px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.post__btns {
  display: flex;
}
</style>
  