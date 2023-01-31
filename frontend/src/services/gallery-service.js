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

    updateGallery(id, name) {
        return axios.put(API_URL + '/' + id, {name: name}, {headers: authHeader()});
    }

    deleteGallery(id) {
        return axios.delete(API_URL + '/' + id, {headers: authHeader()});
    }

    addImagesTo(id, images) {
        return axios.post(API_URL + '/' + id + '/images', {images: images}, {headers: authHeader()});
    }

    removeImagesFrom(id, images) {
        return axios.delete(API_URL + '/' + id + '/images', {images: images}, {headers: authHeader()});
    }
}

export default new GalleryService();