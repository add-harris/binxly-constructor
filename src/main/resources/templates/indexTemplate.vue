{@net.binxly.constructor.models.files.IndexVue model}
<template>

  <v-row justify="center" align="center">

    <SecondComp></SecondComp>

    <v-col cols="12" sm="8" md="6">

      <v-card class="mt-6" >
        <v-card-title class="headline">
          Congratulations!
        </v-card-title>

        <v-row justify="space-around" align="center">

          <v-card-text>
            You have completed your first binxly project.
          </v-card-text>

        </v-row>



      </v-card>

    </v-col>

  </v-row>

</template>

<script>

import { SecondComp } from "binxly-vue-lib"

export default {

  name: 'index',

  components: {
    SecondComp
  },

  data() {
    return {

    }
  },

  computed: {},

  methods: {}

}
</script>

<style>

</style>
