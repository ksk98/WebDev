<template>
  <div class="jumbotron">
    <header>
      <div class="text-center">
        <h3><strong>{{content.name}}</strong></h3>
        <hr>
        <button id="button-rename" class="btn btn-primary" :disabled="loading">
          <span v-show="loading" class="spinner-border spinner-border-sm"></span>
          <span>Rename gallery</span>
        </button>

        <button id="button-upload-images" class="btn btn-primary" :disabled="loading">
          <span v-show="loading" class="spinner-border spinner-border-sm"></span>
          <span>Add images</span>
        </button>
      </div>
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
          this.prepContent()
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
    async prepContent() {
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

        const removeImageButton = document.createElement("button")
        removeImageButton.className = "btn btn-primary"
        removeImageButton.textContent = "delete"
        removeImageButton.style.margin = "1%"
        removeImageButton.onclick = () => {
          GalleryService.deleteImage(image.id)
          location.reload()
        }
        caption.appendChild(removeImageButton)

        figure.style.margin = "3%"
        figure.appendChild(imageDisplay)
        figure.appendChild(caption)

        imageList.appendChild(figure)
      }

      const buttonRename = document.getElementById("button-rename")
      buttonRename.onclick = () => location.href='/gallery/' + this.$route.params.id + '/rename'

      const buttonUploadImages = document.getElementById("button-upload-images")
      buttonUploadImages.onclick = () => location.href='/gallery/' + this.$route.params.id + '/uploadImages'
    }
  }
}
</script>

<style scoped>

</style>