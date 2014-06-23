package com.sunan.van.regedit.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class RegeditCenterInitializer extends ChannelInitializer<SocketChannel> {
	private static final StringDecoder DECODER = new StringDecoder();
    private static final StringEncoder ENCODER = new StringEncoder();
    private static RegeditCenterHandler CLIENTHANDLER ;

    
    public RegeditCenterInitializer(SubCallback callback){
    	CLIENTHANDLER = new RegeditCenterHandler(callback);
    }
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();

        // Add the text line codec combination first,
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(
                8192, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", DECODER);
        pipeline.addLast("encoder", ENCODER);

        // and then business logic.
        pipeline.addLast("handler", CLIENTHANDLER);
    }
}
