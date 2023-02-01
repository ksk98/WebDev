<template>
  <div class="jumbotron">
    <header v-if="currentUser != null">
      <h3>
        <strong>{{currentUser.username}}'s</strong> galleries:
      </h3>
    </header>
    <p v-for="gallery in data" :key="gallery">{{gallery}}</p>
    <p v-if="data.content === ''"><i>No galleries present.</i></p>
    <button onclick="location.href='/createGallery'" class="btn btn-primary btn-block" :disabled="loading">
      <span
        v-show="loading"
        class="spinner-border spinner-border-sm"
      ></span>
      <span>Create gallery</span>
    </button>
  </div>
</template>

<script>
import GalleryService from "@/services/gallery-service";

export default {
  name: "Gallery.vue",
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
    data() {
      return {
        content: "",
      };
    },
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }

    GalleryService.getGalleries().then(
      (response) => {
        this.content = response.data;
      },
      (error) => {
        this.content =
          (error.response &&
              error.response.data &&
              error.response.data.message) ||
          error.message ||
          error.toString();
      }
    )
  }
}
</script>

<style scoped>

</style>