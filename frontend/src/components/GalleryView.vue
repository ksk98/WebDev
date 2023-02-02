<template>
  <div class="jumbotron">
    <header>
      <h3>
        <div class="text-center">
          <strong>{{content.name}}</strong>
          <hr>
          <button class="btn btn-primary" :disabled="loading">
            <span v-show="loading" class="spinner-border spinner-border-sm"></span><span>Rename</span>
          </button>

          <button class="btn btn-primary" :disabled="loading">
            <span v-show="loading" class="spinner-border spinner-border-sm"></span>
            <span>Add images</span>
          </button>

          <button class="btn btn-primary" :disabled="loading">
            <span v-show="loading" class="spinner-border spinner-border-sm"></span>
            <span>Remove images</span>
          </button>
        </div>

      </h3>
    </header>

    <div class="text-center">
      <br>
      <div id="content-list">

      </div>
    </div>

  </div>
</template>

<script>
import GalleryService from "@/services/gallery-service";
import {nextTick} from "vue";

export default {
  name: "GalleryView",
  data() {
    return {
      galleries_loaded: false,
      content: "",
      loading: true
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

    GalleryService.getGallery(this.$route.params.id).then(
        (response) => {
          this.content = response.data;
          this.galleries_loaded = true;
          this.loading = false;
          this.displayImages()
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
    async displayImages() {
      await nextTick()
      let gallery = this.content

      const imageList = document.getElementById("content-list")
      for (let imageInd in gallery.images){
        let image = gallery.images[imageInd]
        const figure = document.createElement("figure")

        const imageDisplay = document.createElement("img")
        imageDisplay.src = image.contentHeader + image.content
        imageDisplay.alt = "IMAGE"
        imageDisplay.style.maxWidth = "30vw"
        imageDisplay.style.maxHeight = "40vh"

        const caption = document.createElement("figcaption")
        caption.appendChild(document.createTextNode(image.name))

        figure.style.margin = "3%"
        figure.appendChild(imageDisplay)
        figure.appendChild(caption)

        imageList.appendChild(figure)
      }
    }
  }
}
</script>

<style scoped>

</style>