/*
 * fresnel_lens.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:42 by COMSOL 6.3.0.290. */
public class fresnel_lens {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");
    model.component("comp1").physics().create("ewbe", "ElectromagneticWavesBeamEnvelopes", "geom1");

    model.study().create("std1");
    model.study("std1").create("wave", "Wavelength");
    model.study("std1").feature("wave").set("ftplistmethod", "manual");
    model.study("std1").feature("wave").set("solnum", "auto");
    model.study("std1").feature("wave").set("notsolnum", "auto");
    model.study("std1").feature("wave").set("outputmap", new String[]{});
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").setSolveFor("/physics/ewfd", true);
    model.study("std1").feature("wave").setSolveFor("/physics/ewbe", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("lda0", "0.5[um]", "\u6ce2\u957f");
    model.param().set("k0", "2*pi/lda0", "\u6ce2\u6570");
    model.param().set("f", "150[um]", "\u7126\u8ddd");
    model.param().set("R", "f/2", "\u900f\u955c\u8868\u9762\u7684\u66f2\u7387\u534a\u5f84");
    model.param().set("D", "50[um]", "\u900f\u955c\u76f4\u5f84");
    model.param().set("m", "1", "\u83f2\u6d85\u5c14\u900f\u955c\u7ea7\u6570");
    model.param().set("z0", "sqrt(R^2-(D/2)^2)", "\u900f\u955c\u5728 Z \u4e0a\u7684\u57fa\u672c\u4f4d\u7f6e");
    model.param().set("sag", "R-z0", "\u672a\u6298\u53e0\u900f\u955c\u4e0b\u6c89\u91cf");
    model.param().set("n", "1.5", "\u6298\u5c04\u7387");
    model.param().set("d", "m*lda0/(n-1)", "\u7b2c m \u9636\u7684\u6298\u53e0\u900f\u955c\u4e0b\u6c89\u91cf");
    model.param().set("M", "16", "\u6570\u5b57\u5316\u7ea7\u522b");
    model.param().set("dm", "d/M", "\u6bcf\u4e2a\u7ea7\u522b\u7684\u9636\u68af\u9ad8\u5ea6");
    model.param().set("N", "floor(sag/d)+1", "\u533a\u57df\u6570");
    model.param().set("Nmesh", "5", "\u6bcf\u4e2a\u6ce2\u957f\u7684\u7f51\u683c\u5355\u5143\u6570");
    model.param().set("Nx", "256", "\u91c7\u6837\u5206\u8fa8\u7387");

    model.geom().create("part1", "Part", 2);
    model.geom("part1").label("\u83f2\u6d85\u5c14\u900f\u955c\u5e26");
    model.geom("part1").inputParam().set("zone", "1");
    model.geom("part1").inputParam().descr("zone", "\u5e26\u6307\u6570");
    model.geom("part1").lengthUnit("\u00b5m");

//    To import content from file, use:
//    model.geom("part1").localParam().loadFile("FILENAME");
    model.geom("part1").localParam()
         .set("Mi", "if(zone>1,M,floor(M*(sag/d-N+1))+1)", "\u533a\u57df\u7684\u6570\u5b57\u5316\u8ba1\u6570");
    model.geom("part1").localParam()
         .set("x0", "if(zone>1,sqrt(R^2-(R-(sag-(N+1-zone)*d))^2),0)", "\u533a\u57df\u5de6\u7aef");
    model.geom("part1").localParam()
         .set("x1", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+1)*dm)^2)", "\u7b2c 1 \u4e2a\u9636\u68af\u53f3\u7aef");
    model.geom("part1").localParam()
         .set("x2", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+2)*dm)^2)", "\u7b2c 2 \u4e2a\u9636\u68af\u53f3\u7aef");
    model.geom("part1").localParam()
         .set("x3", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+3)*dm)^2)", "\u7b2c 3 \u4e2a\u9636\u68af\u53f3\u7aef");
    model.geom("part1").localParam()
         .set("x4", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+4)*dm)^2)", "\u7b2c 4 \u4e2a\u9636\u68af\u53f3\u7aef");
    model.geom("part1").localParam()
         .set("x5", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+5)*dm)^2)", "\u7b2c 5 \u4e2a\u9636\u68af\u53f3\u7aef");
    model.geom("part1").localParam()
         .set("x6", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+6)*dm)^2)", "\u7b2c 6 \u4e2a\u9636\u68af\u53f3\u7aef");
    model.geom("part1").localParam()
         .set("x7", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+7)*dm)^2)", "\u7b2c 7 \u4e2a\u9636\u68af\u53f3\u7aef");
    model.geom("part1").localParam()
         .set("x8", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+8)*dm)^2)", "\u7b2c 8 \u4e2a\u9636\u68af\u53f3\u7aef");
    model.geom("part1").localParam()
         .set("x9", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+9)*dm)^2)", "\u7b2c 9 \u4e2a\u9636\u68af\u53f3\u7aef");
    model.geom("part1").localParam()
         .set("x10", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+10)*dm)^2)", "\u7b2c 10 \u4e2a\u9636\u68af\u53f3\u7aef");
    model.geom("part1").localParam()
         .set("x11", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+11)*dm)^2)", "\u7b2c 11 \u4e2a\u9636\u68af\u53f3\u7aef");
    model.geom("part1").localParam()
         .set("x12", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+12)*dm)^2)", "\u7b2c 12 \u4e2a\u9636\u68af\u53f3\u7aef");
    model.geom("part1").localParam()
         .set("x13", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+13)*dm)^2)", "\u7b2c 13 \u4e2a\u9636\u68af\u53f3\u7aef");
    model.geom("part1").localParam()
         .set("x14", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+14)*dm)^2)", "\u7b2c 14 \u4e2a\u9636\u68af\u53f3\u7aef");
    model.geom("part1").localParam()
         .set("x15", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+15)*dm)^2)", "\u7b2c 15 \u4e2a\u9636\u68af\u53f3\u7aef");
    model.geom("part1").localParam().set("x16", "sqrt(R^2-(R-(sag-(N-zone)*d))^2)", "\u533a\u57df\u53f3\u7aef");
    model.geom("part1").localParam().set("y0", "-dm", "\u57fa\u51c6\u9762");
    model.geom("part1").localParam().set("y1", "(Mi-1)*dm", "\u7b2c 1 \u5c42");
    model.geom("part1").localParam().set("y2", "(Mi-2)*dm", "\u7b2c 2 \u5c42");
    model.geom("part1").localParam().set("y3", "(Mi-3)*dm", "\u7b2c 3 \u5c42");
    model.geom("part1").localParam().set("y4", "(Mi-4)*dm", "\u7b2c 4 \u5c42");
    model.geom("part1").localParam().set("y5", "(Mi-5)*dm", "\u7b2c 5 \u5c42");
    model.geom("part1").localParam().set("y6", "(Mi-6)*dm", "\u7b2c 6 \u5c42");
    model.geom("part1").localParam().set("y7", "(Mi-7)*dm", "\u7b2c 7 \u5c42");
    model.geom("part1").localParam().set("y8", "(Mi-8)*dm", "\u7b2c 8 \u5c42");
    model.geom("part1").localParam().set("y9", "(Mi-9)*dm", "\u7b2c 9 \u5c42");
    model.geom("part1").localParam().set("y10", "(Mi-10)*dm", "\u7b2c 10 \u5c42");
    model.geom("part1").localParam().set("y11", "(Mi-11)*dm", "\u7b2c 11 \u5c42");
    model.geom("part1").localParam().set("y12", "(Mi-12)*dm", "\u7b2c 12 \u5c42");
    model.geom("part1").localParam().set("y13", "(Mi-13)*dm", "\u7b2c 13 \u5c42");
    model.geom("part1").localParam().set("y14", "(Mi-14)*dm", "\u7b2c 14 \u5c42");
    model.geom("part1").localParam().set("y15", "(Mi-15)*dm", "\u7b2c 15 \u5c42");
    model.geom("part1").localParam().set("y16", "(Mi-16)*dm", "\u7b2c 16 \u5c42");
    model.geom("part1").create("pol1", "Polygon");
    model.geom("part1").feature("pol1").set("source", "vectors");
    model.geom("part1").feature("pol1")
         .set("x", "x0 x0 x1 x1 x2 x2 x3 x3 x4 x4 x5 x5 x6 x6 x7 x7 x8 x8 x9 x9 x10 x10 x11 x11 x12 x12 x13 x13 x14 x14 x15 x15 x16 x16");
    model.geom("part1").feature("pol1")
         .set("y", "y0 y1 y1 y2 y2 y3 y3 y4 y4 y5 y5 y6 y6 y7 y7 y8 y8 y9 y9 y10 y10 y11 y11 y12 y12 y13 y13 y14 y14 y15 y15 y16 y16 y0");
    model.geom("part1").selection().create("csel1", "CumulativeSelection");
    model.geom("part1").selection("csel1").label("\u83f2\u6d85\u5c14\u900f\u955c\u5e26");
    model.geom("part1").feature("pol1").set("contributeto", "csel1");
    model.geom("part1").run("pol1");
    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u83f2\u6d85\u5c14\u900f\u955c");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_csel1.dom", "csel1");
    model.component("comp1").geom("geom1").feature().duplicate("pi2", "pi1");
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetoobj", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepobj", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetobnd", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepbnd", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowbnd", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetodom", new String[]{"csel1"});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "zone", 2);
    model.component("comp1").geom("geom1").feature().duplicate("pi3", "pi2");
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetoobj", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepobj", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetobnd", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepbnd", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowbnd", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi2").set("selcontributetodom", new String[]{"csel1"});
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi2").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi3").setEntry("inputexpr", "zone", 3);
    model.component("comp1").geom("geom1").feature().duplicate("pi4", "pi3");
    model.component("comp1").geom("geom1").feature("pi3").set("selcontributetoobj", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepobj", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi3").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi3").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi3").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi3").set("selcontributetobnd", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepbnd", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi3").set("selshowbnd", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi3").set("selcontributetodom", new String[]{"csel1"});
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi3").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi4").setEntry("inputexpr", "zone", 4);
    model.component("comp1").geom("geom1").feature().duplicate("pi5", "pi4");
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetoobj", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepobj", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi4").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetobnd", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepbnd", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi4").set("selshowbnd", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi4").set("selcontributetodom", new String[]{"csel1"});
    model.component("comp1").geom("geom1").feature("pi4").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi4").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi5").setEntry("inputexpr", "zone", 5);
    model.component("comp1").geom("geom1").run("pi5");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("pi1", "pi2", "pi3", "pi4", "pi5");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("mir1", "uni1");
    model.component("comp1").geom("geom1").feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"D", "d+dm"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"-D/2", "-dm"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("r1", "uni2");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"D", "f+dm"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"-D/2", "-dm"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "d+dm", 0);
    model.component("comp1").geom("geom1").feature().duplicate("r3", "r2");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"lda0", "f+dm"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"-D/2-lda0", "-dm"});
    model.component("comp1").geom("geom1").feature().duplicate("r4", "r3");
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"D/2", "-dm"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").coordSystem().create("pml1", "PML");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").coordSystem("pml1").selection().set(1, 2, 6, 7);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").label("Air");
    model.component("comp1").material("mat1").set("family", "air");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat1").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat1").materialType("nonSolid");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u73bb\u7483");
    model.component("comp1").material("mat2").selection().named("geom1_csel1_dom");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"n"});

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("sel1").all();
    model.component("comp1").selection("sel1").geom("geom1", 2, 1, new String[]{"exterior"});
    model.component("comp1").selection("sel1").all();
    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("\u8f93\u5165\u5e73\u9762");
    model.component("comp1").selection("box1").set("entitydim", 1);
    model.component("comp1").selection("box1").set("inputent", "selections");
    model.component("comp1").selection("box1").set("input", new String[]{"sel1"});
    model.component("comp1").selection("box1").set("xmin", "-D/2");
    model.component("comp1").selection("box1").set("xmax", "D/2");
    model.component("comp1").selection("box1").set("ymin", "-dm");
    model.component("comp1").selection("box1").set("ymax", "-dm/2");
    model.component("comp1").selection("box1").set("condition", "inside");
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u6563\u5c04\u8fb9\u754c");
    model.component("comp1").selection("dif1").set("entitydim", 1);
    model.component("comp1").selection("dif1").set("add", new String[]{"sel1"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"box1"});
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u51fa\u5c04\u9762");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(11);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().named("sel2");

    model.component("comp1").physics("ewfd").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewfd").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("ewfd").feature("sctr1").selection().named("box1");
    model.component("comp1").physics("ewfd").feature("sctr1").set("IncidentField", "EField");
    model.component("comp1").physics("ewfd").feature("sctr1").set("E0i", new int[]{0, 0, 1});
    model.component("comp1").physics("ewfd").feature("sctr1").set("Order", "SecondOrder");
    model.component("comp1").physics("ewfd").create("sctr2", "Scattering", 1);
    model.component("comp1").physics("ewfd").feature("sctr2").selection().named("dif1");
    model.component("comp1").physics("ewfd").feature("sctr2").set("Order", "SecondOrder");

    model.component("comp1").mesh("mesh1").label("\u7ec6\u5316\u7f51\u683c");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "lda0/Nmesh");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(3, 4);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "lda0/n/Nmesh");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(4, 301);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 8);

    model.study("std1").feature("wave").set("plist", "lda0");
    model.study("std1").feature("wave").setSolveFor("/physics/ewbe", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("frametype", "spatial");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").label("\u7535\u573a\u6a21 (ewfd)");
    model.result("pg1").set("view", "view1");

    model.component("comp1").view("view1").axis().set("viewscaletype", "manual");
    model.component("comp1").view("view1").axis().set("xscale", 2);

    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u573a\u5927\u5c0f (ewfd)");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "ewfd.Ez");
    model.result("pg2").feature("surf1").set("colortable", "WaveLight");
    model.result("pg2").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg2").run();
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("\u900f\u955c\u9644\u8fd1\u7684\u7535\u573a\u6a21 (ewfd)");

    model.component("comp1").view().duplicate("view3", "view1");
    model.component("comp1").view("view3").axis().set("xscale", 1);
    model.component("comp1").view("view3").axis().set("yscale", 10);
    model.component("comp1").view("view3").hideEntities().create("hide1");
    model.component("comp1").view("view3").hideEntities("hide1").set(1, 2, 5, 6, 7);

    model.result("pg3").run();
    model.result("pg3").set("view", "view3");
    model.result("pg3").run();
    model.result("pg2").run();
    model.result().duplicate("pg4", "pg2");
    model.result("pg4").run();
    model.result("pg4").label("\u900f\u955c\u9644\u8fd1\u7684\u7535\u573a\u5927\u5c0f (ewfd)");
    model.result("pg4").set("view", "view3");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u51fa\u5c04\u9762\u7684\u7535\u573a\u5927\u5c0f (ewfd)");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").selection().named("sel2");
    model.result("pg5").feature("lngr1").set("expr", "ewfd.Ez");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u51fa\u5c04\u9762\u7684\u7535\u573a\u76f8\u4f4d (ewfd)");
    model.result("pg6").run();
    model.result("pg6").feature("lngr1").set("expr", "arg(ewfd.Ez)");
    model.result("pg6").run();
    model.result().dataset().create("grid1", "Grid1D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").set("par1", "u");
    model.result().dataset("grid1").set("parmin1", "-D/2");
    model.result().dataset("grid1").set("parmax1", "D/2");
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", -25, 0, 0);
    model.result().dataset("cln1").setIndex("genpoints", 1, 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", 25, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", 1, 1, 1);
    model.result().dataset().create("sfft1", "SpatialFFT");
    model.result().dataset("sfft1").set("data", "cln1");
    model.result().dataset("sfft1").set("gridres", "manual");
    model.result().dataset("sfft1").set("sampresx", 256);
    model.result().dataset("sfft1").set("layout", "padding");
    model.result().dataset("sfft1").set("padx", 16384);
    model.result().dataset("sfft1").set("fxvar", "fx");
    model.result().dataset("sfft1").set("maskdc", false);
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u83f2\u6d85\u5c14\u4e0e\u4ea5\u59c6\u970d\u5179\u7684\u6bd4\u8f83");
    model.result("pg7").set("data", "grid1");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").label("\u79ef\u5206\u83f2\u6d85\u5c14\u8fd1\u4f3c");
    model.result("pg7").feature("lngr1")
         .set("expr", "1/sqrt(lda0*f)*abs(intop1(ewfd.Ez*exp(-i*k0*x^2/(2*f))*exp(i*2*pi*dest(u)*x/(lda0*f))))");
    model.result("pg7").feature("lngr1").set("descractive", true);
    model.result("pg7").feature("lngr1").set("descr", "\u884d\u5c04\u79ef\u5206");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "u");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("autoplotlabel", true);
    model.result("pg7").feature("lngr1").set("autosolution", false);
    model.result("pg7").run();
    model.result("pg7").create("lngr2", "LineGraph");
    model.result("pg7").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr2").set("linewidth", "preference");
    model.result("pg7").feature("lngr2").label("FFT \u83f2\u6d85\u5c14\u8fd1\u4f3c");
    model.result("pg7").feature("lngr2").set("data", "sfft1");
    model.result("pg7").feature("lngr2").set("expr", "1/sqrt(lda0*f)*abs(fft(ewfd.Ez*exp(-i*k0*x^2/(2*f)) ))*D/Nx");
    model.result("pg7").feature("lngr2").set("xdata", "expr");
    model.result("pg7").feature("lngr2").set("xdataexpr", "fx*lda0/(1[um])*f/(1[um])");
    model.result("pg7").feature("lngr2").set("legend", true);
    model.result("pg7").feature("lngr2").set("autoplotlabel", true);
    model.result("pg7").feature("lngr2").set("autosolution", false);
    model.result("pg7").run();
    model.result("pg7").create("lngr3", "LineGraph");
    model.result("pg7").feature("lngr3").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr3").set("linewidth", "preference");
    model.result("pg7").feature("lngr3").label("\u4ea5\u59c6\u970d\u5179\u65b9\u7a0b (ewfd)");
    model.result("pg7").feature("lngr3").set("data", "dset1");
    model.result("pg7").feature("lngr3").selection().set(12);
    model.result("pg7").feature("lngr3").set("xdata", "expr");
    model.result("pg7").feature("lngr3").set("xdataexpr", "x");
    model.result("pg7").feature("lngr3").set("legend", true);
    model.result("pg7").feature("lngr3").set("autoplotlabel", true);
    model.result("pg7").feature("lngr3").set("autosolution", false);
    model.result("pg7").run();
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "x \u5750\u6807 (\u00b5m)");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u7535\u573a\u5927\u5c0f (a.u.)");
    model.result("pg7").set("titletype", "manual");
    model.result("pg7")
         .set("title", "\u83f2\u6d85\u5c14\u8fd1\u4f3c\u4e0e\u4ea5\u59c6\u970d\u5179\u65b9\u7a0b\u7684\u6bd4\u8f83");
    model.result("pg7").set("axislimits", true);
    model.result("pg7").set("xmin", -25);
    model.result("pg7").set("xmax", 25);
    model.result("pg7").run();

    model.component("comp1").physics("ewbe").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("dirCount", "UniDirectionality");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("k1", new String[]{"0", "ewbe.k0", "0"});
    model.component("comp1").physics("ewbe").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("ewbe").feature("sctr1").selection().named("box1");
    model.component("comp1").physics("ewbe").feature("sctr1").set("IncidentField", "EField");
    model.component("comp1").physics("ewbe").feature("sctr1").set("E0i", new int[]{0, 0, 1});
    model.component("comp1").physics("ewbe").create("sctr2", "Scattering", 1);
    model.component("comp1").physics("ewbe").feature("sctr2").selection().named("dif1");

    model.component("comp1").mesh().duplicate("mesh2", "mesh1");
    model.component("comp1").mesh("mesh2").label("\u7c97\u5316\u7f51\u683c");
    model.component("comp1").mesh("mesh2").feature("map1").create("size1", "Size");
    model.component("comp1").mesh("mesh2").feature("map1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("map1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh2").feature("map1").feature("size1").set("hmax", "2*lda0");

    model.study().create("std2");
    model.study("std2").create("wave", "Wavelength");
    model.study("std2").feature("wave").set("plotgroup", "Default");
    model.study("std2").feature("wave").set("ftplistmethod", "manual");
    model.study("std2").feature("wave").set("solnum", "auto");
    model.study("std2").feature("wave").set("notsolnum", "auto");
    model.study("std2").feature("wave").set("outputmap", new String[]{});
    model.study("std2").feature("wave").set("ngenAUX", "1");
    model.study("std2").feature("wave").set("goalngenAUX", "1");
    model.study("std2").feature("wave").set("ngenAUX", "1");
    model.study("std2").feature("wave").set("goalngenAUX", "1");
    model.study("std2").feature("wave").setSolveFor("/physics/ewfd", false);
    model.study("std2").feature("wave").setSolveFor("/physics/ewbe", true);
    model.study("std2").feature("wave").set("plist", "lda0");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").label("\u7535\u573a (ewbe)");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").setIndex("looplevel", 1, 0);
    model.result("pg8").feature().create("surf1", "Surface");
    model.result("pg8").feature("surf1").label("\u7535\u573a");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("expr", "ewbe.normE");
    model.result("pg8").feature("surf1").set("smooth", "internal");
    model.result("pg8").feature("surf1").set("showsolutionparams", "on");
    model.result("pg8").feature("surf1").set("data", "parent");
    model.result("pg8").run();
    model.result("pg8").set("view", "view1");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("lngr4", "lngr3");
    model.result("pg7").run();
    model.result("pg7").feature("lngr4").label("\u4ea5\u59c6\u970d\u5179\u65b9\u7a0b (ewbe)");
    model.result("pg7").feature("lngr4").set("data", "dset2");
    model.result("pg7").feature("lngr4").set("expr", "ewbe.normE");
    model.result("pg7").run();

    model.param().set("M", "8");
    model.param().descr("M", "\u6570\u5b57\u5316\u6c34\u5e73");

//    Started to run method rebuildGeometry

    model.geom("part1").localParam().remove("x0");
    model.geom("part1").localParam().remove("x1");
    model.geom("part1").localParam().remove("x2");
    model.geom("part1").localParam().remove("x3");
    model.geom("part1").localParam().remove("x4");
    model.geom("part1").localParam().remove("x5");
    model.geom("part1").localParam().remove("x6");
    model.geom("part1").localParam().remove("x7");
    model.geom("part1").localParam().remove("x8");
    model.geom("part1").localParam().remove("x9");
    model.geom("part1").localParam().remove("x10");
    model.geom("part1").localParam().remove("x11");
    model.geom("part1").localParam().remove("x12");
    model.geom("part1").localParam().remove("x13");
    model.geom("part1").localParam().remove("x14");
    model.geom("part1").localParam().remove("x15");
    model.geom("part1").localParam().remove("x16");
    model.geom("part1").localParam().remove("y0");
    model.geom("part1").localParam().remove("y1");
    model.geom("part1").localParam().remove("y2");
    model.geom("part1").localParam().remove("y3");
    model.geom("part1").localParam().remove("y4");
    model.geom("part1").localParam().remove("y5");
    model.geom("part1").localParam().remove("y6");
    model.geom("part1").localParam().remove("y7");
    model.geom("part1").localParam().remove("y8");
    model.geom("part1").localParam().remove("y9");
    model.geom("part1").localParam().remove("y10");
    model.geom("part1").localParam().remove("y11");
    model.geom("part1").localParam().remove("y12");
    model.geom("part1").localParam().remove("y13");
    model.geom("part1").localParam().remove("y14");
    model.geom("part1").localParam().remove("y15");
    model.geom("part1").localParam().remove("y16");
    model.geom("part1").localParam()
         .set("x0", "if(zone > 1,sqrt(R^2-(R-(sag-(N+1-zone)*d))^2),0)", "Left end of zone");
    model.geom("part1").localParam().set("x1", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+1)*dm)^2)", "1st step right end");
    model.geom("part1").localParam().set("x2", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+2)*dm)^2)", "2nd step right end");
    model.geom("part1").localParam().set("x3", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+3)*dm)^2)", "3rd step right end");
    model.geom("part1").localParam().set("x4", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+4)*dm)^2)", "4th step right end");
    model.geom("part1").localParam().set("x5", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+5)*dm)^2)", "5th step right end");
    model.geom("part1").localParam().set("x6", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+6)*dm)^2)", "6th step right end");
    model.geom("part1").localParam().set("x7", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+7)*dm)^2)", "7th step right end");
    model.geom("part1").localParam().set("x8", "sqrt(R^2-(R-(sag-(N-zone)*d))^2)", "Right end of zone");
    model.geom("part1").localParam().set("y0", "-dm", "Base level");
    model.geom("part1").localParam().set("y1", "(Mi-1)*dm", "1st level");
    model.geom("part1").localParam().set("y2", "(Mi-2)*dm", "2nd level");
    model.geom("part1").localParam().set("y3", "(Mi-3)*dm", "3rd level");
    model.geom("part1").localParam().set("y4", "(Mi-4)*dm", "4th level");
    model.geom("part1").localParam().set("y5", "(Mi-5)*dm", "5th level");
    model.geom("part1").localParam().set("y6", "(Mi-6)*dm", "6th level");
    model.geom("part1").localParam().set("y7", "(Mi-7)*dm", "7th level");
    model.geom("part1").localParam().set("y8", "(Mi-8)*dm", "8th level");
    model.geom("part1").feature("pol1").set("x", "x0 x0 x1 x1 x2 x2 x3 x3 x4 x4 x5 x5 x6 x6 x7 x7 x8 x8");
    model.geom("part1").feature("pol1").set("y", "y0 y1 y1 y2 y2 y3 y3 y4 y4 y5 y5 y6 y6 y7 y7 y8 y8 y0");
    model.component("comp1").geom("geom1").run();

//    Finished running method rebuildGeometry

    model.param().set("M", "16");

//    Started to run method rebuildGeometry

    model.geom("part1").localParam().remove("x0");
    model.geom("part1").localParam().remove("x1");
    model.geom("part1").localParam().remove("x2");
    model.geom("part1").localParam().remove("x3");
    model.geom("part1").localParam().remove("x4");
    model.geom("part1").localParam().remove("x5");
    model.geom("part1").localParam().remove("x6");
    model.geom("part1").localParam().remove("x7");
    model.geom("part1").localParam().remove("x8");
    model.geom("part1").localParam().remove("y0");
    model.geom("part1").localParam().remove("y1");
    model.geom("part1").localParam().remove("y2");
    model.geom("part1").localParam().remove("y3");
    model.geom("part1").localParam().remove("y4");
    model.geom("part1").localParam().remove("y5");
    model.geom("part1").localParam().remove("y6");
    model.geom("part1").localParam().remove("y7");
    model.geom("part1").localParam().remove("y8");
    model.geom("part1").localParam()
         .set("x0", "if(zone > 1,sqrt(R^2-(R-(sag-(N+1-zone)*d))^2),0)", "Left end of zone");
    model.geom("part1").localParam().set("x1", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+1)*dm)^2)", "1st step right end");
    model.geom("part1").localParam().set("x2", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+2)*dm)^2)", "2nd step right end");
    model.geom("part1").localParam().set("x3", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+3)*dm)^2)", "3rd step right end");
    model.geom("part1").localParam().set("x4", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+4)*dm)^2)", "4th step right end");
    model.geom("part1").localParam().set("x5", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+5)*dm)^2)", "5th step right end");
    model.geom("part1").localParam().set("x6", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+6)*dm)^2)", "6th step right end");
    model.geom("part1").localParam().set("x7", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+7)*dm)^2)", "7th step right end");
    model.geom("part1").localParam().set("x8", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+8)*dm)^2)", "8th step right end");
    model.geom("part1").localParam().set("x9", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+9)*dm)^2)", "9th step right end");
    model.geom("part1").localParam().set("x10", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+10)*dm)^2)", "10th step right end");
    model.geom("part1").localParam().set("x11", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+11)*dm)^2)", "11th step right end");
    model.geom("part1").localParam().set("x12", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+12)*dm)^2)", "12th step right end");
    model.geom("part1").localParam().set("x13", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+13)*dm)^2)", "13th step right end");
    model.geom("part1").localParam().set("x14", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+14)*dm)^2)", "14th step right end");
    model.geom("part1").localParam().set("x15", "sqrt(R^2-(z0+(N-zone+1)*d-(M-Mi+15)*dm)^2)", "15th step right end");
    model.geom("part1").localParam().set("x16", "sqrt(R^2-(R-(sag-(N-zone)*d))^2)", "Right end of zone");
    model.geom("part1").localParam().set("y0", "-dm", "Base level");
    model.geom("part1").localParam().set("y1", "(Mi-1)*dm", "1st level");
    model.geom("part1").localParam().set("y2", "(Mi-2)*dm", "2nd level");
    model.geom("part1").localParam().set("y3", "(Mi-3)*dm", "3rd level");
    model.geom("part1").localParam().set("y4", "(Mi-4)*dm", "4th level");
    model.geom("part1").localParam().set("y5", "(Mi-5)*dm", "5th level");
    model.geom("part1").localParam().set("y6", "(Mi-6)*dm", "6th level");
    model.geom("part1").localParam().set("y7", "(Mi-7)*dm", "7th level");
    model.geom("part1").localParam().set("y8", "(Mi-8)*dm", "8th level");
    model.geom("part1").localParam().set("y9", "(Mi-9)*dm", "9th level");
    model.geom("part1").localParam().set("y10", "(Mi-10)*dm", "10th level");
    model.geom("part1").localParam().set("y11", "(Mi-11)*dm", "11th level");
    model.geom("part1").localParam().set("y12", "(Mi-12)*dm", "12th level");
    model.geom("part1").localParam().set("y13", "(Mi-13)*dm", "13th level");
    model.geom("part1").localParam().set("y14", "(Mi-14)*dm", "14th level");
    model.geom("part1").localParam().set("y15", "(Mi-15)*dm", "15th level");
    model.geom("part1").localParam().set("y16", "(Mi-16)*dm", "16th level");
    model.geom("part1").feature("pol1")
         .set("x", "x0 x0 x1 x1 x2 x2 x3 x3 x4 x4 x5 x5 x6 x6 x7 x7 x8 x8 x9 x9 x10 x10 x11 x11 x12 x12 x13 x13 x14 x14 x15 x15 x16 x16");
    model.geom("part1").feature("pol1")
         .set("y", "y0 y1 y1 y2 y2 y3 y3 y4 y4 y5 y5 y6 y6 y7 y7 y8 y8 y9 y9 y10 y10 y11 y11 y12 y12 y13 y13 y14 y14 y15 y15 y16 y16 y0");
    model.component("comp1").geom("geom1").run();

//    Finished running method rebuildGeometry

    model.result("pg1").run();

    model.title("\u83f2\u6d85\u5c14\u900f\u955c");

    model
         .description("\u672c\u6a21\u578b\u6a21\u62df\u76f4\u5f84\u4e3a 50 \u00b5m\u3001\u7126\u8ddd\u4e3a 150 \u00b5m \u7684 16 \u7ea7\u4e00\u9636\u805a\u7126\u83f2\u6d85\u5c14\u900f\u955c\u3002\u5728\u7b2c\u4e00\u4e2a\u4eff\u771f\u4e2d\uff0c\u901a\u8fc7\u201c\u7535\u78c1\u6ce2\uff0c\u9891\u57df\u201d\u63a5\u53e3\u8ba1\u7b97\u83f2\u6d85\u5c14\u900f\u955c\u4ee5\u53ca\u5ef6\u4f38\u5230\u7126\u5e73\u9762\u7684\u5468\u56f4\u7a7a\u6c14\u57df\u4e2d\u7684\u7535\u573a\u3002\u5728\u7b2c\u4e8c\u4e2a\u4eff\u771f\u4e2d\uff0c\u83f2\u6d85\u5c14\u900f\u955c\u6b63\u4e0a\u65b9\u51fa\u5c04\u9762\u7684\u7535\u573a\u901a\u8fc7\u83f2\u6d85\u5c14\u8fd1\u4f3c\u4f20\u64ad\u5230\u7126\u5e73\u9762\u3002\u6700\u540e\uff0c\u901a\u8fc7\u201c\u7535\u78c1\u6ce2\uff0c\u6ce2\u675f\u5305\u7edc\u201d\u63a5\u53e3\u6267\u884c\u4eff\u771f\uff0c\u5f97\u5230\u8fd9\u4e09\u79cd\u65b9\u6cd5\u8ba1\u7b97\u7684\u7126\u5e73\u9762\u7535\u573a\u7684\u6bd4\u8f83\u6570\u636e\uff0c\u4e09\u79cd\u7ed3\u679c\u5448\u73b0\u9ad8\u5ea6\u7684\u4e00\u81f4\u6027\u3002\n\n\u6b64\u5916\uff0c\u6a21\u578b\u8fd8\u5448\u73b0\u4e86\u201c\u7535\u78c1\u6ce2\uff0c\u6ce2\u675f\u5305\u7edc\u201d\u63a5\u53e3\u5728\u7f29\u77ed\u4eff\u771f\u65f6\u95f4\u65b9\u9762\u7684\u4f18\u52bf\uff0c\u8fd9\u662f\u56e0\u4e3a\u8be5\u63a5\u53e3\u652f\u6301\u4f7f\u7528\u66f4\u7c97\u7684\u7f51\u683c\u8fdb\u884c\u4eff\u771f\u3002\n\n\u8be5\u6a21\u578b\u8fd8\u6f14\u793a\u5982\u4f55\u6784\u5efa\u548c\u8fd0\u884c\u6a21\u578b\u65b9\u6cd5\u3002\u5f53\u8bbe\u8ba1\u53c2\u6570\u53d1\u751f\u53d8\u5316\u65f6\uff0c\u6a21\u578b\u65b9\u6cd5\u53ef\u81ea\u52a8\u91cd\u5efa\u590d\u6742\u7684\u51e0\u4f55\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("fresnel_lens.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
