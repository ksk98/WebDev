<template>
  <div class="container">
    <header v-if="currentUser != null" class="jumbotron">
      <h3>
        <strong>{{currentUser.username}}'s galleries:</strong> Profile
      </h3>
    </header>
    <p v-for="gallery in data" :key="gallery">{{gallery}}</p>
    <p v-if="!data">No galleries present.</p>
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