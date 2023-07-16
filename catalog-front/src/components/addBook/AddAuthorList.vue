<template>

  <transition-group name="user-list">
    <add-author-item
    @data-updated="sendDataToParent"
      v-for="author in authors1"
      :author="author"
      :key="author.id"
    />
  </transition-group>

</template>
  
<script>
import AddAuthorItem from "@/components/addBook/AddAuthorItem";

export default {
  components: { AddAuthorItem },
  props: {
    authors1: {
      type: Array,
      required: true,
    },
  },
  data() {
    return {
      author: null,
    };
  },
  
  methods: {
    sendDataToParent(authorrr) {
        console.log("AddAuthorList");
      console.log(authorrr);
      this.$emit("data-updated-to-parent", authorrr);
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
  