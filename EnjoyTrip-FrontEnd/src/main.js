// import "./assets/main.css";

import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";

// bootstrap
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap";

// Vuetify
import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'

const vuetify = createVuetify({
    components,
    directives,
})

// fontawesome
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { fas } from '@fortawesome/free-solid-svg-icons';
import { far } from '@fortawesome/free-regular-svg-icons';
import { fab } from '@fortawesome/free-brands-svg-icons';
library.add(fas, far, fab);

// pinia
import { createPinia } from "pinia";
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";
const pinia = createPinia();

/*****************************************/

const app = createApp(App);
app.use(pinia.use(piniaPluginPersistedstate));
app.use(router);
app.use(vuetify);
app.component('font-awesome-icon', FontAwesomeIcon);
app.mount("#app");
