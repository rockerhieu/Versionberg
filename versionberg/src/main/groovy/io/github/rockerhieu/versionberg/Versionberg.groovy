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
 * Created by rockerhieu on 9/11/16.
 */
class Versionberg {
    private static final def UNDEFINED = -1;
    def major = 0
    def minor = 0
    def patch = 0
    def build = UNDEFINED
    def commitSha = Git.commitSha
    def commitCount = Git.commitCount

    public <T> void setMajor(T number) {
        major = parse(number)
    }

    public <T> void setMinor(T number) {
        minor = parse(number)
    }

    public <T> void setPatch(T number) {
        patch = parse(number)
    }

    public <T> void setBuild(T number) {
        build = parse(number)
    }

    public int getBuild() {
        if (build == UNDEFINED) {
            build = Git.commitCount
        }
        return build
    }

    public int getCode() {
        return (((major * 100) + minor) * 100) * 100000 + getBuild()
    }

    public String getName() {
        return "${major}.${minor}.${patch}.${commitSha}"
    }

    private static <T> int parse(T number) {
        def intNumber
        try {
            try {
                intNumber = number.toInteger()
            } catch (Exception e) {
                throw new InvalidVersionNumberException()
            }
            if (intNumber < 0) {
                throw new InvalidVersionNumberException()
            }
        } catch (Exception e) {
            Logger.i(e)
            return 0
        }
        return intNumber
    }

    @Override
    String toString() {
        return "major: ${getMajor()}" +
                "\nminor: ${getMinor()}" +
                "\npatch: ${getPatch()}" +
                "\nbuild: ${getBuild()}" +
                "\ncommitSha: ${getCommitSha()}" +
                "\ncommitCount: ${getCommitCount()}" +
                "\ncode: ${getCode()}" +
                "\nname: ${getName()}"
    }
}