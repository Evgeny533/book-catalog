<template>
    
    <transition-group name="user-list">
        <book-item v-for="book in books" :book="book" :key="book.id" />
    </transition-group>

</template>
  
<script>
import BookItem from "@/components/BookItem";

export default {
    components: { BookItem },
    props: {
        books: {
            type: Array,
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
  