<template>
  <div class="jumbotron vertical-center">
    <main role="main" class="inner cover">
      <h1 class="cover-heading">Store your images.</h1>
      <p class="lead">Upload and manage your images and galleries.</p>
      <p class="lead">
        <a href="/galleries" class="btn btn-lg btn-secondary">Start</a>
      </p>
    </main>
  </div>
</template>

<style>
.vertical-center {
  height:100%;
  width:100%;

  text-align: center;  /* align the inline(-block) elements horizontally */
  /*font: 0/0 a;         !* remove the gap between inline(-block) elements *!*/
}

.vertical-center:before {    /* create a full-height inline block pseudo=element */
  content: " ";
  display: inline-block;
  vertical-align: middle;    /* vertical alignment of the inline element */
  height: 100%;
}

.vertical-center > .container {
  max-width: 100%;

  display: inline-block;
  vertical-align: middle;  /* vertical alignment of the inline element */
  /* reset the font property */
  font: 16px/1 "Helvetica Neue", Helvetica, Arial, sans-serif;
}
</style>
