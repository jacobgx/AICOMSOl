/*
 * vented_loudspeaker_enclosure.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class vented_loudspeaker_enclosure {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Electroacoustic_Transducers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics().create("pabe", "PressureAcousticsBoundaryElements", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("shell", "Shell", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("freq").setSolveFor("/physics/pabe", true);
    model.study("std1").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std1").feature("freq").setSolveFor("/physics/shell", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "vented_loudspeaker_enclosure.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("imp1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 180);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view().create("view2", "geom1");
    model.component("comp1").view("view2").set("locked", true);
    model.component("comp1").view("view2").set("rotcenlocked", true);
    model.component("comp1").view("view2").camera().setIndex("position", 1505.2598876953125, 0);
    model.component("comp1").view("view2").camera().setIndex("position", 2077.179931640625, 1);
    model.component("comp1").view("view2").camera().set("target", new double[]{-116.375, 85, -100});
    model.component("comp1").view("view2").camera().setIndex("target", -85, 1);
    model.component("comp1").view("view2").camera().setIndex("up", -0.3086974024772644, 0);
    model.component("comp1").view("view2").camera().setIndex("up", -0.4115965962409973, 1);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u6240\u6709\u57df");
    model.component("comp1").selection("sel1").all();
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u97f3\u5708");

    model.component("comp1").view("view2").set("renderwireframe", true);

    model.component("comp1").selection("sel2").set(26);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u72ed\u7a84\u533a\u57df\u5185\u90e8");
    model.component("comp1").selection("sel3").set(29);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u72ed\u7a84\u533a\u57df\u5916\u90e8");
    model.component("comp1").selection("sel4").set(28);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u8f6f\u94c1");
    model.component("comp1").selection("sel5").set(23, 27);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u94c1\u6c27\u4f53");
    model.component("comp1").selection("sel6").set(24);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("\u7bb1\u4f53");
    model.component("comp1").selection("sel7")
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 18, 19, 20, 21, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42);
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u7a7a\u6c14\u57df");
    model.component("comp1").selection("dif1").set("add", new String[]{"sel1"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"sel2", "sel5", "sel6", "sel7"});
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u7ed3\u6784\u57df");
    model.component("comp1").selection("uni1").set("input", new String[]{"sel2", "sel5", "sel6", "sel7"});
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u626b\u63a0\u57df");
    model.component("comp1").selection("uni2").set("input", new String[]{"sel2", "sel3", "sel4", "sel7"});
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").label("\u6240\u6709\u8fb9\u754c");
    model.component("comp1").selection("sel8").geom(2);
    model.component("comp1").selection("sel8").all();
    model.component("comp1").selection().create("sel9", "Explicit");
    model.component("comp1").selection("sel9").label("\u590d\u5408\u6750\u6599");
    model.component("comp1").selection("sel9").geom(2);
    model.component("comp1").selection("sel9").set(199, 200, 201, 202);
    model.component("comp1").selection().create("sel10", "Explicit");
    model.component("comp1").selection("sel10").label("\u7ec7\u7269");
    model.component("comp1").selection("sel10").geom(2);
    model.component("comp1").selection("sel10")
         .set(173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 187, 188, 191, 192, 193, 194);
    model.component("comp1").selection().create("sel11", "Explicit");
    model.component("comp1").selection("sel11").label("\u6ce1\u6cab\u68c9");
    model.component("comp1").selection("sel11").geom(2);
    model.component("comp1").selection("sel11").set(271, 272, 273, 274);
    model.component("comp1").selection().create("sel12", "Explicit");
    model.component("comp1").selection("sel12").label("\u73bb\u7483\u7ea4\u7ef4");
    model.component("comp1").selection("sel12").geom(2);
    model.component("comp1").selection("sel12").set(115, 116, 121, 122, 141, 142, 163, 164, 171, 172, 195, 196);
    model.component("comp1").selection().create("sel13", "Explicit");
    model.component("comp1").selection("sel13").label("\u7bee\u6846");
    model.component("comp1").selection("sel13").geom(2);
    model.component("comp1").selection("sel13").set(129, 130, 155, 156, 189, 190, 239, 240, 269, 270);
    model.component("comp1").selection().create("sel14", "Explicit");
    model.component("comp1").selection("sel14").label("\u5176\u4ed6\u6620\u5c04\u8fb9\u754c");
    model.component("comp1").selection("sel14").geom(2);
    model.component("comp1").selection("sel14")
         .set(124, 150, 151, 239, 240, 259, 260, 261, 262, 263, 264, 266, 268, 269, 270, 275, 276);
    model.component("comp1").selection().create("sel15", "Explicit");
    model.component("comp1").selection("sel15").label("\u975e\u6620\u5c04\u8fb9\u754c");
    model.component("comp1").selection("sel15").geom(2);
    model.component("comp1").selection("sel15").set(195, 196);
    model.component("comp1").selection().create("uni3", "Union");
    model.component("comp1").selection("uni3").label("\u58f3\u57df");
    model.component("comp1").selection("uni3").set("entitydim", 2);
    model.component("comp1").selection("uni3")
         .set("input", new String[]{"sel9", "sel10", "sel11", "sel12", "sel13"});
    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("\u5bf9\u79f0\u8fb9\u754c");
    model.component("comp1").selection("box1").set("entitydim", 2);
    model.component("comp1").selection("box1").set("ymin", 0);
    model.component("comp1").selection("box1").set("condition", "inside");
    model.component("comp1").selection().create("dif2", "Difference");
    model.component("comp1").selection("dif2").label("\u5bf9\u79f0\u9762\u4e4b\u5916\u7684\u8fb9\u754c");
    model.component("comp1").selection("dif2").set("entitydim", 2);
    model.component("comp1").selection("dif2").set("add", new String[]{"sel8"});
    model.component("comp1").selection("dif2").set("subtract", new String[]{"box1"});
    model.component("comp1").selection().create("dif3", "Difference");
    model.component("comp1").selection("dif3").label("\u6620\u5c04\u8fb9\u754c");
    model.component("comp1").selection("dif3").set("entitydim", 2);
    model.component("comp1").selection("dif3").set("add", new String[]{"sel10", "sel11", "sel12", "sel14"});
    model.component("comp1").selection("dif3").set("subtract", new String[]{"sel15"});
    model.component("comp1").selection().create("box2", "Box");
    model.component("comp1").selection("box2").label("\u5bf9\u79f0\u8fb9");
    model.component("comp1").selection("box2").set("entitydim", 1);
    model.component("comp1").selection("box2").set("ymin", 0);
    model.component("comp1").selection("box2").set("condition", "inside");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("source", "file");
    model.component("comp1").func("int1").set("filename", "vented_loudspeaker_enclosure_Rb.txt");
    model.component("comp1").func("int1").importData();
    model.component("comp1").func("int1").set("funcname", "Rb");
    model.component("comp1").func("int1").setIndex("argunit", "Hz", 0);
    model.component("comp1").func("int1").setIndex("fununit", "ohm", 0);
    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").set("source", "file");
    model.component("comp1").func("int2").set("filename", "vented_loudspeaker_enclosure_Lb.txt");
    model.component("comp1").func("int2").importData();
    model.component("comp1").func("int2").set("funcname", "Lb");
    model.component("comp1").func("int2").setIndex("argunit", "Hz", 0);
    model.component("comp1").func("int2").setIndex("fununit", "H", 0);

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").set("opname", "av_coil");
    model.component("comp1").cpl("aveop1").selection().named("sel2");

    model.param().set("V0", "3.55[V]");
    model.param().descr("V0", "\u9a71\u52a8\u7535\u538b");
    model.param().set("BL", "10.48[N/A]");
    model.param().descr("BL", "\u626c\u58f0\u5668\u9a71\u52a8\u5668\u6a21\u578b\u7684\u529b\u56e0\u5b50");
    model.param().set("f_loss", "40[Hz]");
    model.param().descr("f_loss", "\u7ed9\u5b9a\u635f\u8017\u56e0\u5b50\u65f6\u7684\u9891\u7387");
    model.param().set("omega_loss", "2*pi*f_loss");
    model.param().descr("omega_loss", "\u7ed9\u5b9a\u635f\u8017\u56e0\u5b50\u65f6\u7684\u89d2\u9891\u7387");
    model.param().set("fmax", "3.55[kHz]");
    model.param().descr("fmax", "\u6700\u5927\u7814\u7a76\u9891\u7387");
    model.param().set("c0", "343[m/s]");
    model.param().descr("c0", "\u7a7a\u6c14\u4e2d\u7684\u58f0\u901f");
    model.param().set("lam0", "c0/fmax");
    model.param().descr("lam0", "\u6700\u5c0f\u6ce2\u957f");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("v0", "av_coil(solid.u_tX)");
    model.component("comp1").variable("var1").descr("v0", "\u97f3\u5708\u901f\u5ea6");
    model.component("comp1").variable("var1").set("Zb", "Rb(freq)+acpr.iomega*Lb(freq)");
    model.component("comp1").variable("var1").descr("Zb", "\u5c4f\u853d\u97f3\u5708\u963b\u6297");
    model.component("comp1").variable("var1").set("Fe", "BL*V0/Zb-v0*BL^2/Zb");
    model.component("comp1").variable("var1").descr("Fe", "\u7535\u9a71\u52a8\u529b");

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
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat2").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat2").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat2").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat2").label("Structural steel");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("fresnel", 0.9);
    model.component("comp1").material("mat2").set("roughness", 0.3);
    model.component("comp1").material("mat2").set("diffusewrap", 0);
    model.component("comp1").material("mat2").set("reflectance", 0);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat2").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").label("Interpolation 1");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat2").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat2").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat2").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("Norton").label("Norton");
    model.component("comp1").material("mat2").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat2").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat2").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat3").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat3").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat3").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat3").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat3").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat3").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat3").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat3").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat3").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat3").label("Structural steel 1");
    model.component("comp1").material("mat3").set("family", "custom");
    model.component("comp1").material("mat3")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat3").set("diffuse", "custom");
    model.component("comp1").material("mat3")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat3").set("ambient", "custom");
    model.component("comp1").material("mat3")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat3").set("noise", true);
    model.component("comp1").material("mat3").set("fresnel", 0.9);
    model.component("comp1").material("mat3").set("roughness", 0.3);
    model.component("comp1").material("mat3").set("diffusewrap", 0);
    model.component("comp1").material("mat3").set("reflectance", 0);
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat3").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat3").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat3").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat3").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat3").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat3").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat3").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat3").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat3").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat3").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat3").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp1").material("mat3").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat3").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat3").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat3").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat3").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat3").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat3").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat3").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat3").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat3").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat3").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat3").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat3").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat3").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat3").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat3").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat3").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat3").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat3").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat3").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat3").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat3").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat3").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat3").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat3").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat3").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat3").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat3").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat3").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat3").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat3").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat3").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat3").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat3").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("Norton").label("Norton");
    model.component("comp1").material("mat3").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat3").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat3").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat3").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat3").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat3").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat3").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat3").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp1").material("mat3").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat3").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat3").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.component("comp1").material().create("mat4", "Common");
    model.component("comp1").material("mat4").label("Composite");
    model.component("comp1").material("mat4").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat4").propertyGroup("def").set("youngsmodulus", "2[GPa]");
    model.component("comp1").material("mat4").propertyGroup("def").set("poissonsratio", "0.42");
    model.component("comp1").material("mat4").propertyGroup("def").set("density", "1200[kg/m^3]");
    model.component("comp1").material("mat4").propertyGroup("def").set("lossfactor", "0.04");
    model.component("comp1").material().create("mat5", "Common");
    model.component("comp1").material("mat5").label("Cloth");
    model.component("comp1").material("mat5").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat5").propertyGroup("def").set("youngsmodulus", "0.58[GPa]");
    model.component("comp1").material("mat5").propertyGroup("def").set("poissonsratio", "0.3");
    model.component("comp1").material("mat5").propertyGroup("def").set("density", "650[kg/m^3]");
    model.component("comp1").material().create("mat6", "Common");
    model.component("comp1").material("mat6").label("Foam");
    model.component("comp1").material("mat6").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat6").propertyGroup("def").set("youngsmodulus", "5[MPa]");
    model.component("comp1").material("mat6").propertyGroup("def").set("poissonsratio", "0.4");
    model.component("comp1").material("mat6").propertyGroup("def").set("density", "67[kg/m^3]");
    model.component("comp1").material().create("mat7", "Common");
    model.component("comp1").material("mat7").label("Coil");
    model.component("comp1").material("mat7").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat7").propertyGroup("def").set("lossfactor", "0.05");
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat7").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat7").propertyGroup("def").set("youngsmodulus", "110[GPa]");
    model.component("comp1").material("mat7").propertyGroup("def").set("poissonsratio", "0.35");
    model.component("comp1").material("mat7").propertyGroup("def").set("density", "4500[kg/m^3]");
    model.component("comp1").material().create("mat8", "Common");
    model.component("comp1").material("mat8").label("Glass Fiber");
    model.component("comp1").material("mat8").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("electricconductivity", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat8").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat8").propertyGroup("def").set("youngsmodulus", "70[GPa]");
    model.component("comp1").material("mat8").propertyGroup("def").set("poissonsratio", "0.33");
    model.component("comp1").material("mat8").propertyGroup("def").set("density", "2000[kg/m^3]");
    model.component("comp1").material("mat8").propertyGroup("def").set("lossfactor", "0.04");
    model.component("comp1").material().create("mat9", "Common");
    model.component("comp1").material("mat9").propertyGroup()
         .create("RemanentFluxDensity", "RemanentFluxDensity", "Remanent flux density");
    model.component("comp1").material("mat9").label("Generic Ferrite");
    model.component("comp1").material("mat9").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat9").propertyGroup("def").set("lossfactor", "0.01");
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("electricconductivity", new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").material("mat9").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat9").propertyGroup("def").set("poissonsratio", "0.3");
    model.component("comp1").material("mat9").propertyGroup("def").set("youngsmodulus", "200[GPa]");
    model.component("comp1").material("mat9").propertyGroup("def").set("density", "5000[kg/m^3]");
    model.component("comp1").material("mat9").propertyGroup("RemanentFluxDensity").label("Remanent flux density");
    model.component("comp1").material("mat9").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat9").propertyGroup("RemanentFluxDensity").set("normBr", "0.4[T]");
    model.component("comp1").material().create("mat10", "Common");
    model.component("comp1").material("mat10").label("Fiberboard");
    model.component("comp1").material("mat10").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat10").propertyGroup("def").set("density", "");
    model.component("comp1").material("mat10").propertyGroup("def").set("poissonsratio", "");
    model.component("comp1").material("mat10").propertyGroup("def").set("youngsmodulus", "");
    model.component("comp1").material("mat10").propertyGroup("def").set("lossfactor", "");
    model.component("comp1").material("mat10").propertyGroup("def").set("density", "900[kg/m^3]");
    model.component("comp1").material("mat10").propertyGroup("def").set("poissonsratio", "0.3");
    model.component("comp1").material("mat10").propertyGroup("def").set("youngsmodulus", "4[GPa]");
    model.component("comp1").material("mat10").propertyGroup("def").set("lossfactor", "0.07");
    model.component("comp1").material("mat1").selection().all();
    model.component("comp1").material("mat1").selection().allVoids();
    model.component("comp1").material("mat2").selection().named("sel5");
    model.component("comp1").material("mat3").selection().geom("geom1", 2);
    model.component("comp1").material("mat3").selection().named("sel13");
    model.component("comp1").material("mat4").selection().geom("geom1", 2);
    model.component("comp1").material("mat4").selection().named("sel9");
    model.component("comp1").material("mat5").selection().geom("geom1", 2);
    model.component("comp1").material("mat5").selection().named("sel10");
    model.component("comp1").material("mat6").selection().geom("geom1", 2);
    model.component("comp1").material("mat6").selection().named("sel11");
    model.component("comp1").material("mat7").selection().named("sel2");
    model.component("comp1").material("mat8").selection().geom("geom1", 2);
    model.component("comp1").material("mat8").selection().named("sel12");
    model.component("comp1").material("mat9").selection().named("sel6");
    model.component("comp1").material("mat10").selection().named("sel7");

    model.component("comp1").physics("acpr").selection().named("dif1");
    model.component("comp1").physics("acpr").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("acpr").feature("sym1").selection().named("box1");
    model.component("comp1").physics("acpr").create("nra1", "NarrowRegionAcousticsModel", 3);
    model.component("comp1").physics("acpr").feature("nra1").selection().named("sel3");
    model.component("comp1").physics("acpr").feature("nra1").set("DuctType", "Slit");
    model.component("comp1").physics("acpr").feature("nra1").set("h", "0.4[mm]");
    model.component("comp1").physics("acpr").create("nra2", "NarrowRegionAcousticsModel", 3);
    model.component("comp1").physics("acpr").feature("nra2").selection().named("sel4");
    model.component("comp1").physics("acpr").feature("nra2").set("DuctType", "Slit");
    model.component("comp1").physics("acpr").feature("nra2").set("h", "0.2[mm]");
    model.component("comp1").physics("pabe").selection().set();
    model.component("comp1").physics("pabe").selection().allVoids();
    model.component("comp1").physics("pabe").prop("Symmetry").setIndex("sym2", "even", 0);
    model.component("comp1").physics("solid").selection().named("uni1");
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().named("box1");
    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp1").physics("solid").create("bl1", "BodyLoad", 3);
    model.component("comp1").physics("solid").feature("bl1").selection().named("sel5");
    model.component("comp1").physics("solid").feature("bl1").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bl1").set("force", new String[]{"-0.5*Fe", "0", "0"});
    model.component("comp1").physics("solid").create("bl2", "BodyLoad", 3);
    model.component("comp1").physics("solid").feature("bl2").selection().named("sel2");
    model.component("comp1").physics("solid").feature("bl2").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bl2").set("force", new String[]{"0.5*Fe", "0", "0"});
    model.component("comp1").physics("shell").selection().named("uni3");
    model.component("comp1").physics("shell").feature("emm1").create("dmp1", "Damping", 2);
    model.component("comp1").physics("shell").feature("emm1").feature("dmp1").selection().named("uni3");
    model.component("comp1").physics("shell").feature("emm1").feature("dmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp1").physics("shell").feature("emm1").create("dmp2", "Damping", 2);
    model.component("comp1").physics("shell").feature("emm1").feature("dmp2").selection().named("sel10");
    model.component("comp1").physics("shell").feature("emm1").feature("dmp2").set("beta_dK", "0.14/omega_loss");
    model.component("comp1").physics("shell").feature("emm1").create("dmp3", "Damping", 2);
    model.component("comp1").physics("shell").feature("emm1").feature("dmp3").selection().named("sel11");
    model.component("comp1").physics("shell").feature("emm1").feature("dmp3").set("beta_dK", "0.46/omega_loss");
    model.component("comp1").physics("shell").feature("to1").set("d", "1[mm]");
    model.component("comp1").physics("shell").create("to2", "ThicknessOffset", 2);
    model.component("comp1").physics("shell").feature("to2").selection().named("sel10");
    model.component("comp1").physics("shell").feature("to2").set("d", "0.4[mm]");
    model.component("comp1").physics("shell").create("to3", "ThicknessOffset", 2);
    model.component("comp1").physics("shell").feature("to3").selection().named("sel11");
    model.component("comp1").physics("shell").feature("to3").set("d", "1.5[mm]");
    model.component("comp1").physics("shell").create("to4", "ThicknessOffset", 2);

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp1").physics("shell").feature("to4").selection().named("sel12");
    model.component("comp1").physics("shell").feature("to4").set("d", "0.2[mm]");
    model.component("comp1").physics("shell").create("to5", "ThicknessOffset", 2);
    model.component("comp1").physics("shell").feature("to5").selection().named("sel13");
    model.component("comp1").physics("shell").feature("to5").set("d", "0.8[mm]");
    model.component("comp1").physics("shell").create("sym1", "SymmetrySolid1", 1);
    model.component("comp1").physics("shell").feature("sym1").selection().named("box2");

    model.component("comp1").material("mat2").propertyGroup("def").set("lossfactor", new String[]{"0.01"});
    model.component("comp1").material("mat3").propertyGroup("def").set("lossfactor", new String[]{"0.01"});

    model.component("comp1").multiphysics().create("asb1", "AcousticStructureBoundary", 2);
    model.component("comp1").multiphysics("asb1").selection().all();
    model.component("comp1").multiphysics().create("asb2", "AcousticStructureBoundary", 2);
    model.component("comp1").multiphysics("asb2").selection().all();
    model.component("comp1").multiphysics("asb2").set("Structure_physics", "shell");
    model.component("comp1").multiphysics().create("asb3", "AcousticStructureBoundary", 2);
    model.component("comp1").multiphysics("asb3").selection().named("dif2");
    model.component("comp1").multiphysics("asb3").set("Acoustics_physics", "pabe");
    model.component("comp1").multiphysics().create("asb4", "AcousticStructureBoundary", 2);
    model.component("comp1").multiphysics("asb4").selection().named("dif2");
    model.component("comp1").multiphysics("asb4").set("Acoustics_physics", "pabe");
    model.component("comp1").multiphysics("asb4").set("Structure_physics", "shell");
    model.component("comp1").multiphysics().create("aab1", "AcousticAcousticBoundary", 2);
    model.component("comp1").multiphysics("aab1").selection().set(277);
    model.component("comp1").multiphysics().create("sshc1", "SolidShellConnection", -1);
    model.component("comp1").multiphysics("sshc1").set("connectionSettings", "sharedBnd");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("dif3");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection()
         .set(165, 270, 309, 310, 311, 312, 314, 340, 346, 348, 462, 479, 481, 485, 489, 510);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(328, 329, 498, 499);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 10);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection()
         .set(219, 220, 222, 224, 270, 504, 505);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").selection().set(517, 519);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("numelem", 3);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "lam0/4");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "2[mm]");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("uni2");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection()
         .set(1, 2, 3, 4, 5, 6, 8, 10, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").set("optlevel", "high");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().named("sel9");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "8[mm]");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u5b8c\u6574\u7814\u7a76");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("freq")
         .set("plist", "{20, 22.4, 25, 28, 31.5, 35.5, 40, 45, 50, 56, 63, 71, 80, 90, 100, 112, 125, 140, 160, 180, 200, 224, 250, 280, 315, 355, 400, 450, 500, 560, 630, 710, 800, 900, 1e3, 1.12e3, 1.25e3, 1.4e3, 1.6e3, 1.8e3, 2e3, 2.24e3, 2.5e3, 2.8e3, 3.15e3, 3.55e3}");
    model.study("std1").createAutoSequences("sol");

    model.sol("sol1").runFromTo("st1", "v1");
    model.sol("sol1").feature("s1").feature("d1").active(true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("grid1", "Grid3D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").set("parmin1", "-500[mm]");
    model.result().dataset("grid1").set("parmax1", "500[mm]");
    model.result().dataset("grid1").set("parmin2", "-500[mm]");
    model.result().dataset("grid1").set("parmax2", "-0.1[mm]");
    model.result().dataset("grid1").set("parmin3", "-400[mm]");
    model.result().dataset("grid1").set("parmax3", "300[mm]");
    model.result().dataset("grid1").set("res1", 100);
    model.result().dataset("grid1").set("res2", 100);
    model.result().dataset("grid1").set("res3", 100);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 46, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result("pg1").run();
    model.result("pg1").label("\u58f0\u538b");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "if(isnan(acpr.p_t),pabe.p_t_bnd,acpr.p_t)");
    model.result("pg1").feature("surf1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def1")
         .set("expr", new String[]{"if(isnan(shell.disp),u,u2)", "v", "w"});
    model.result("pg1").feature("surf1").feature("def1").setIndex("expr", "if(isnan(shell.disp),v,v2)", 1);
    model.result("pg1").feature("surf1").feature("def1").setIndex("expr", "if(isnan(shell.disp),w,w2)", 2);
    model.result("pg1").run();
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", "0");
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "black");
    model.result("pg1").feature("line1").set("inheritplot", "surf1");
    model.result("pg1").feature("line1").set("inheritcolor", false);
    model.result("pg1").feature("line1").set("inheritrange", false);
    model.result("pg1").feature("line1").set("inherittubescale", false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("line1").feature().copy("def1", "pg1/surf1/def1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("data", "grid1");
    model.result("pg1").feature("mslc1").set("expr", "pabe.p_t");
    model.result("pg1").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("mslc1").set("xcoord", "-125[mm]");
    model.result("pg1").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("mslc1").set("zcoord", 0);
    model.result("pg1").feature("mslc1").set("inheritplot", "surf1");

    model.component("comp1").view("view2").set("showgrid", false);

    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u58f0\u538b\u7ea7");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "if(isnan(acpr.Lp),pabe.Lp_bnd,acpr.Lp)");
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("resolution", "extrafine");
    model.result("pg2").run();
    model.result("pg2").feature("mslc1").set("expr", "pabe.Lp");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u4f4d\u79fb");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "if(isnan(shell.disp),solid.disp,shell.disp)");
    model.result("pg3").run();
    model.result("pg3").feature().remove("mslc1");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 9, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 38, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 39, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 40, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 46, 0);
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u5e94\u529b");
    model.result("pg4").setIndex("looplevel", 18, 0);
    model.result("pg4").selection().geom("geom1", 2);
    model.result("pg4").selection().named("sel13");
    model.result("pg4").set("applyselectiontodatasetedges", true);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "if(isnan(shell.mises),solid.mises,shell.mises)");
    model.result("pg4").feature("surf1").set("unit", "MPa");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("def1").set("scale", 350);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("def1").set("scaleactive", false);
    model.result("pg2").set("applyselectiontodatasetedges", false);
    model.result("pg2").run();
    model.result().duplicate("pg5", "pg2");
    model.result("pg5").run();
    model.result("pg5").label("\u8f90\u5c04\u65b9\u5411\u56fe");
    model.result("pg5").run();
    model.result("pg5").feature().remove("mslc1");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").create("rp1", "RadiationPattern");
    model.result("pg5").feature("rp1").set("expr", "1000[mm]");
    model.result("pg5").feature("rp1").set("useradiusascolor", false);
    model.result("pg5").feature("rp1").set("colorexpr", "pabe.Lp");
    model.result("pg5").feature("rp1").set("thetadisc", 160);
    model.result("pg5").feature("rp1").set("phidisc", 320);
    model.result("pg5").feature("rp1").set("anglerestr", "manual");
    model.result("pg5").feature("rp1").set("phirange", -180);
    model.result("pg5").feature("rp1").set("sphere", "manual");
    model.result("pg5").feature("rp1").set("radius", "1000[mm]");
    model.result("pg5").feature("rp1").set("grid", "fine");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("colorlegend", false);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 17, 0);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 18, 0);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 46, 0);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("1 m \u5904\u7684\u7075\u654f\u5ea6\u3002");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").create("oct1", "OctaveBand");
    model.result("pg6").feature("oct1").set("quantity", "bandpower");
    model.result("pg6").feature("oct1").set("markerpos", "datapoints");
    model.result("pg6").feature("oct1").set("linewidth", "preference");
    model.result("pg6").feature("oct1").selection().geom("geom1");
    model.result("pg6").feature("oct1").set("expr", "at3_spatial(1[m],0,0,pabe.p_t,'minc')");
    model.result("pg6").feature("oct1").set("quantity", "continuous");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u65b9\u5411\u6027\uff0cxy \u5e73\u9762");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").create("dir1", "Directivity");
    model.result("pg7").feature("dir1").set("linewidth", "preference");
    model.result("pg7").feature("dir1").set("expr", "pabe.Lp_t");
    model.result("pg7").feature("dir1").set("phidisc", 180);
    model.result("pg7").feature("dir1").set("anglerestr", "manual");
    model.result("pg7").feature("dir1").set("phimin", -180);
    model.result("pg7").feature("dir1").set("radius", "1000[mm]");
    model.result("pg7").feature("dir1").set("levelmethod", "levels");
    model.result("pg7").feature("dir1").set("levels", "9 6 3 1.5 -1.5 -3 -6 -9 -12 -18 -24");
    model.result("pg7").run();
    model.result("pg7").set("xlog", true);
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u65b9\u5411\u6027\uff0cxz \u5e73\u9762");
    model.result("pg8").run();
    model.result("pg8").feature("dir1").set("normal", new int[]{0, -1, 0});
    model.result("pg8").run();
    model.result().export().create("radpt1", "RadiationPattern");
    model.result().export("radpt1").setIndex("expr", "real(pabe.p)", 0);
    model.result().export("radpt1").setIndex("expr", "imag(pabe.p)", 1);
    model.result().export("radpt1").set("sphere", "manual");
    model.result().export("radpt1").set("radius", 1000);
    model.result().export("radpt1").set("filename", "export_balloon_data.txt");

    model.study().create("std2");
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("plotgroup", "Default");
    model.study("std2").feature("eig").set("storefact", false);
    model.study("std2").feature("eig").set("solnum", "auto");
    model.study("std2").feature("eig").set("notsolnum", "auto");
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/acpr", false);
    model.study("std2").feature("eig").setSolveFor("/physics/pabe", false);
    model.study("std2").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std2").feature("eig").setSolveFor("/physics/shell", true);
    model.study("std2").feature("eig").setSolveFor("/multiphysics/asb1", false);
    model.study("std2").feature("eig").setSolveFor("/multiphysics/asb2", false);
    model.study("std2").feature("eig").setSolveFor("/multiphysics/asb3", false);
    model.study("std2").feature("eig").setSolveFor("/multiphysics/asb4", false);
    model.study("std2").feature("eig").setSolveFor("/multiphysics/aab1", false);
    model.study("std2").feature("eig").setSolveFor("/multiphysics/sshc1", true);
    model.study("std2").feature("eig").set("neigsactive", true);
    model.study("std2").feature("eig").set("neigs", 20);
    model.study("std2").feature("eig").set("eigwhich", "lr");
    model.study("std2").label("\u7814\u7a76 2 - \u7279\u5f81\u9891\u7387");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg3").run();
    model.result().duplicate("pg9", "pg3");
    model.result("pg9").run();
    model.result("pg9").label("\u632f\u578b");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").run();
    model.result("pg9").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").set("looplevel", new int[]{2});
    model.result("pg9").run();
    model.result("pg9").set("looplevel", new int[]{6});
    model.result("pg9").run();
    model.result("pg9").set("looplevel", new int[]{9});
    model.result("pg9").run();
    model.result("pg5").run();

    model.title("\u655e\u5f00\u5f0f\u97f3\u7bb1\u4e2d\u7684\u626c\u58f0\u5668\u9a71\u52a8\u5668");

    model
         .description("\u8fd9\u4e00\u7bb1\u5f0f\u626c\u58f0\u5668\u793a\u4f8b\u7528\u4e8e\u65bd\u52a0\u6807\u79f0\u9a71\u52a8\u7535\u538b\u5e76\u63d0\u53d6\u623f\u95f4\u5916\u4ea7\u751f\u7684\u968f\u9891\u7387\u53d8\u5316\u7684\u58f0\u538b\u7ea7\u3002\u9a71\u52a8\u5668\u7684\u7535\u78c1\u5c5e\u6027\u7531\u201c\u626c\u58f0\u5668\u9a71\u52a8\u5668\u201d\u6a21\u578b\u63d0\u4f9b\uff08\u968f\u201cAC/DC \u6a21\u5757\u201d\u63d0\u4f9b\uff09\u3002\u672c\u4f8b\u4f7f\u7528\u201c\u58f0-\u58f3\u76f8\u4e92\u4f5c\u7528\uff0c\u9891\u57df\u201d\u591a\u7269\u7406\u573a\u63a5\u53e3\uff0c\u56e0\u6b64\u9700\u8981\u201c\u7ed3\u6784\u529b\u5b66\u6a21\u5757\u201d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("vented_loudspeaker_enclosure.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
