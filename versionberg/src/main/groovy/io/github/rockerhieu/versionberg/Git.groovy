/*
 * Copyright (c) 2016 Hieu Rocker
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package io.github.rockerhieu.versionberg

/**
 * Created by rockerhieu on 9/12/16.
 */
class Git {
    static String projectPath = ''

    static String getCommitSha() {
        try {
            def externalPathOption = checkInnerGitRepo() ? "--git-dir=${projectPath}/.git" : ''
            return "git ${externalPathOption} rev-parse --short HEAD".execute().text.trim()
        } catch (Exception e) {
            Logger.i(e)
            return "null"
        }
    }
    static int getCommitCount() {
        try {
            def output = new StringBuilder()
            def error = new StringBuilder()
            def externalPathOption = checkInnerGitRepo() ? "--git-dir=${projectPath}/.git": ''
            def process = "git ${externalPathOption} rev-list HEAD --count".execute()
            process.waitForProcessOutput(output, error)
            if (output.isInteger()) {
                return output.toInteger()
            } else {
                Logger.i("Error: ${error}")
            }
        } catch (Exception e) {
            Logger.i(e)
        }
        return 0
    }
    private static boolean checkInnerGitRepo() {
        if(projectPath.empty) {
            return false
        }
        // check if there is a git repo at the root of the current project
        try {
            "git --git-dir=${projectPath}/../.git rev-parse --git-dir".execute()
        } catch (Exception e) {
            return false
        }
        return true
    }
}