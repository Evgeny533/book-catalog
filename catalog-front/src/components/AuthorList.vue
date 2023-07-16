<template>

  <transition-group name="user-list">
    <author-item
     
      v-for="author in authors"
      :author="author"
      :key="author.id"
    />
  </transition-group>

</template>
  
<script>
import AuthorItem from "@/components/AuthorItem";

export default {
  components: { AuthorItem },
  props: {
    authors: {
      type: Array,
      required: true,
    },
  }
};
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
  