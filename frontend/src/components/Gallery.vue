<template>
  <div class="jumbotron">
    <header v-if="currentUser != null">
      <h3>
        <strong>{{currentUser.username}}'s</strong> galleries:
      </h3>
    </header>

    <p v-if="!galleries_loaded">No galleries present.</p>
    <ul v-else-if="galleries_loaded">
      <li v-for="gallery in content" :key="gallery">{{ gallery }}</li>
    </ul>

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
  data() {
    return {
      galleries_loaded: false,
      content: ""
    }
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('/login');
    }

    GalleryService.getGalleries().then(
      (response) => {
        this.content = response.data;
        this.galleries_loaded = true;
        console.log("Got " + this.content.length)
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