<template>
  <Form @submit="createGallery" :validation-schema="schema">
    <div class="form-group">
      <label for="name">Gallery name:</label>
      <Field name="name" type="text" class="form-control" />
      <ErrorMessage name="name" class="error-feedback" />
    </div>
    <div class="form-group">
      <label for="files">Files:</label>
      <Field name="files" type="file" class="form-control" multiple="" />
      <ErrorMessage name="files" class="error-feedback" />
    </div>

    <div class="form-group">
      <button class="btn btn-primary btn-block" :disabled="loading">
            <span
                v-show="loading"
                class="spinner-border spinner-border-sm"
            ></span>
        <span>Create</span>
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
import * as yup from "yup";
import galleryService from "@/services/gallery-service";

export default {
  name: "CreateGallery.vue",
  components: {
    Form,
    Field,
    ErrorMessage,
  },
  data() {
    const schema = yup.object().shape({
      name: yup.string().required("Gallery name is required!"),
      files: yup.array().required("Upload must not be empty!"),
    });

    return {
      loading: false,
      message: "",
      schema,
    };
  },
  methods: {
    createGallery(gallery) {
      let files_promise = gallery.files.map(async file =>
        await this.getBase64(file),
      );
      // eslint-disable-next-line
      Promise.all(files_promise).then(results => {
        galleryService.createGallery(gallery.name, results)
        this.$router.push("/galleries")
      });
    },
    getBase64(file) {
      return new Promise ((resolve, reject) => {
        const reader = new FileReader ();
        reader.readAsDataURL (file);
        // eslint-disable-next-line
        reader.onload = _ => resolve(
            this.prepareFile(file.name, reader.result.split(/(?<=,)/))
        )
        reader.onerror = e => reject (e);
      });
    },
    prepareFile(file_name, base64_split) {
      return ({
        name: file_name,
        contentHeader: base64_split[0],
        content: base64_split[1]
      })
    }
  }
}
</script>

<style scoped>

</style>