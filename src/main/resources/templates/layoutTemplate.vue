{@net.binxly.constructor.models.files.LayoutVue model}
<template>
  <v-app>

    <v-app-bar fixed app>
      <v-toolbar-title v-text="title" />
      <v-spacer />
    </v-app-bar>

    <v-main>
      <v-container>
        <nuxt />
      </v-container>
    </v-main>

    <v-footer app >
      <span>&copy; {{ new Date().getFullYear() }}</span>
    </v-footer>

  </v-app>
</template>

<script>
export default {
  data () {
    return {
      title: '{model.navBar.title}'
    }
  }
}
</script>
