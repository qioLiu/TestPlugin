package com.groovy.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project

class ApkRenamePlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        project.extensions.create("apkRenameConf", ApkRenameConf.class)
        project.afterEvaluate {
            if (!project.apkRenameConf.nameMap || !project.apkRenameConf.dirString){
                project.logger.info('apkrenameconf is null')
                return
            }
            def changeNameClosure = project['apkRenameConf'].nameMap
            def dirString = project['apkRenameConf'].dirString

            project.android.applicationVariants.all{ variant -> variant.outputs.all{
                outputFileName = changeNameClosure('plugin') + "-${variant.name}.apk"
            }
            }
        }

    }

}