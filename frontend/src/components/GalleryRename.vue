<template>
  <Form @submit="renameGallery" style="margin: 3%">
    <div class="form-group">
      <label for="name">Gallery name:</label>
      <Field name="name" type="text" class="form-control" />
      <ErrorMessage name="name" class="error-feedback" />
    </div>

    <div class="form-group">
      <button class="btn btn-primary btn-block" :disabled="loading">
            <span
                v-show="loading"
                class="spinner-border spinner-border-sm"
            ></span>
        <span>Login</span>
      </button>
    </div>

    <div class="form-group">
      <div v-if="message" class="alert alert-danger" role="alert">
        {{ message }}
      </div>
    </div>
  </Form>
</template>

<script>
import {ErrorMessage, Field, Form} from "vee-validate";
import galleryService from "@/services/gallery-service";

export default {
  name: "GalleryRename",
  components: {
    Form,
    Field,
    ErrorMessage,
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
  },
  methods: {
    renameGallery(gallery) {
      this.loading = true;

      galleryService.updateGallery(this.$route.params.id, gallery.name).then(
          // eslint-disable-next-line
          (_) => {
            this.$router.push("/gallery/" + this.$route.params.id);
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
}
</script>

<style scoped>

</style>