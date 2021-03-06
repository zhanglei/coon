package cn.ms.coon.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.ms.coon.Mconf;
import cn.ms.coon.Mlock;
import cn.ms.coon.Mreg;
import cn.ms.coon.support.AbstractCoonFactory;
import cn.ms.neural.NURL;
import cn.ms.neural.extension.Extension;
import cn.ms.neural.extension.ExtensionLoader;

@Extension("redis")
public class RedisCoonFactory extends AbstractCoonFactory {

	private final static Logger logger = LoggerFactory.getLogger(RedisCoonFactory.class);
	
	@Override
	public Mreg createMreg(NURL nurl) {
		String key = this.getClass().getAnnotation(Extension.class).value();
		return ExtensionLoader.getLoader(Mreg.class).getExtension(key);
	}

	@Override
	public Mconf createMconf(NURL nurl) {
		logger.info("Is loading conf and mconf center...");

		String key = this.getClass().getAnnotation(Extension.class).value();
		Mconf mconf = ExtensionLoader.getLoader(Mconf.class).getExtension(key);
		mconf.connect(nurl);
		if (!mconf.available()) {
			throw new IllegalStateException("No mconf center available: " + nurl);
		} else {
			logger.info("The mconf center started successed!");
		}
		
		return mconf;
	}
	
	@Override
	public Mlock createMlock(NURL nurl) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
