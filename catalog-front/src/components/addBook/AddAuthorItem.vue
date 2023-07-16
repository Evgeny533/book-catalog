<template>
  <div class="post">
    <div>
      <div>{{ author.id }}</div>
      <div @click="sendDataToParent"><strong>ФИО:</strong> {{ author.name }}</div>
    </div>
   
  </div>
</template>
  
<script>
export default {
  props: {
    author: {
      type: Object,
      required: true,
    }
  },
  methods:{
    sendDataToParent() {
      console.log( this.author);
      this.$emit('data-updated', this.author);
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
  