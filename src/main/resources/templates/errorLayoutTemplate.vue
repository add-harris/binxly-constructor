{@net.binxly.constructor.models.files.ErrorLayoutVue model}
<template>
  <v-app>
    <ErrorPage :error="error"></ErrorPage>
  </v-app>
</template>

<script>
import { ErrorPage } from "binxly-vue-lib"

export default {

  layout: 'empty',

  components: {
    ErrorPage
  },

  props: {
    error: {
      type: Object,
      default: null
    }
  },

}
</script>