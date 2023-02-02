<template>
  <div class="jumbotron">
    <header v-if="currentUser != null">
      <h3>
        <strong>{{currentUser.username}}'s</strong> galleries:
      </h3>
    </header>

    <p v-if="!galleries_loaded">No galleries present.</p>
    <ul v-else-if="galleries_loaded"  id="galleries">
<!--      <li v-for="gallery in content" :key="gallery">-->
<!--        <ul>-->
<!--          <li v-for="image in gallery.images" :key="image">-->
<!--            {{base64ToImage(image.contentHeader + image.content)}}-->
<!--          </li>-->
<!--        </ul>-->
<!--      </li>-->
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
import {nextTick} from "vue";

export default {
  name: "Gallery.vue",
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

    GalleryService.getGalleries().then(
      (response) => {
        this.content = response.data;
        this.galleries_loaded = true;
        this.loading = false;
        this.displayGalleries()
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
    base64ToImage(content) {
      const out = new Image()
      out.src = content;
      return out.tag;
    },
    async displayGalleries() {
      await nextTick()

      const galleryList = document.getElementById("galleries")
      for (let galleryInd in this.content) {
        const galleryListEntry = document.createElement("li")

        let gallery = this.content[galleryInd]

        const imageList = document.createElement("ul")
        for (let imageInd in gallery.images){
          let image = gallery.images[imageInd]
          const imageListEntry = document.createElement("li")
          const imageDisplay = document.createElement("img")
          imageDisplay.src = image.contentHeader + image.content
          imageDisplay.alt = "IMAGE"
          imageListEntry.appendChild(imageDisplay)
          imageList.appendChild(imageListEntry)
        }

        galleryListEntry.appendChild(imageList)
        galleryList.appendChild(galleryListEntry)
      }
    }
  }
}
</script>

<style scoped>

</style>