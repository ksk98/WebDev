import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/gallery';

class GalleryService {
    createGallery(name, images) {
        return axios.post(API_URL, {name: name, images: images}, {headers: authHeader()});
    }

    getGalleries() {
        return axios.get(API_URL, {headers: authHeader()});
    }

    getGallery(id) {
        return axios.get(API_URL + '/' + id, {headers: authHeader()});
    }

    updateGallery(id, name) {
        return axios.put(API_URL + '/' + id, {name: name}, {headers: authHeader()});
    }

    deleteGallery(gallery_id, image_id) {
        return axios.delete(API_URL + '/' + gallery_id + '/images/' + image_id, {headers: authHeader()});
    }

    addImagesTo(id, images) {
        return axios.post(API_URL + '/' + id + '/images', {images: images}, {headers: authHeader()});
    }

    deleteImage(id) {
        return axios.delete(API_URL + '/images/' + id, {headers: authHeader()});
    }
}

export default new GalleryService();