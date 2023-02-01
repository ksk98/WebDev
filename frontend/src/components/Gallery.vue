<template>
  <div class="jumbotron">
    <header v-if="currentUser != null">
      <h3>
        <strong>{{currentUser.username}}'s</strong> galleries:
      </h3>
    </header>
    <p v-for="gallery in data" :key="gallery">{{gallery}}</p>
    <p v-if="data.content === ''"><i>No galleries present.</i></p>
    <Form @submit="handleLogin" :validation-schema="schema">
      <div class="form-group">
        <button class="btn btn-primary btn-block" :disabled="loading">
          <span
            v-show="loading"
            class="spinner-border spinner-border-sm"
          ></span>
          <span>Create gallery</span>
        </button>
      </div>
    </Form>
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
  },
  methods: {
    createGallery() {

    }
  }
}
</script>

<style scoped>

</style>