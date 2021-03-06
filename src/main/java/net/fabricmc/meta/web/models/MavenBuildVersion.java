/*
 * Copyright (c) 2022 RewovenMC
 * Copyright (c) 2021 Legacy Fabric
 * Copyright (c) 2019 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.fabricmc.meta.web.models;

public class MavenBuildVersion extends MavenVersion {

	String name;
	String separator;
	long build;

	public MavenBuildVersion(String maven) {
		super(maven);
		name = maven.split(":")[1];
		String version = maven.split(":")[2];

		if (version.contains("+build.")) {
			separator = "+build.";
		} else {
			separator = ".";
		}
		// FIXME: if a version contains a non number, such as `+local`, it will fail and crash. try/catch maybe?
		try {
			build = Long.parseLong(version.substring(version.lastIndexOf(".") + 1));
		} catch (NumberFormatException e) {
			build = -1L;
		}

	}

	public String getName() {
		return name;
	}
	
	public String getSeparator() {
		return separator;
	}

	public long getBuild() {
		return build;
	}
}
