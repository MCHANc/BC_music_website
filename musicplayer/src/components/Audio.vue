<template>
    <div id="myaudio">
        <div class="myaudio">
            <audio autoplay="autoplay" controls="controls" :src="src" preload="none" id="music" ref="audio">您的浏览器不支持 audio 标签。</audio>
        </div>
    </div>
</template>
<script>
export default {
    data(){
        return{
            src:null
        }
    },
    methods:{
        getSrc(src){
            this.src=src
            this.$refs.audio.play()  
        },
        pause(){
            this.$refs.audio.pause()
        }
    }
}
</script>
<style scoped>

.myaudio{
   
    height: 53px;
    
}
audio{
    
    height: 100%;
    width: 100%;
    background-color: #242424;
}
progressBar{
    color: #c20c0c;
}
</style>