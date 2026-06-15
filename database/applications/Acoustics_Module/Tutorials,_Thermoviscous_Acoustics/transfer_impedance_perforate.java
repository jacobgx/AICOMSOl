/*
 * transfer_impedance_perforate.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class transfer_impedance_perforate {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Tutorials,_Thermoviscous_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ta", "ThermoacousticsSinglePhysics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/ta", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("a", "0.3[mm]", "\u5b54\u534a\u5f84");
    model.param().set("tp", "1[mm]", "\u677f\u539a");
    model.param().set("Lx", "1.4[mm]", "\u5b54\u95f4\u8ddd x \u8ddd\u79bb");
    model.param().set("Ly", "2[mm]", "\u5b54\u95f4\u8ddd y \u8ddd\u79bb");
    model.param().set("Lz", "1[mm]", "\u7a7a\u6c14\u5c42\u539a\u5ea6");
    model.param().set("fmin", "20[Hz]", "\u6700\u5c0f\u7814\u7a76\u9891\u7387");
    model.param().set("fmax", "20000[Hz]", "\u6700\u5927\u7814\u7a76\u9891\u7387");
    model.param()
         .set("dvisc", "220[um]*sqrt(100[Hz]/fmax)", "\u6700\u5c0f\u9ecf\u6ede\u8fb9\u754c\u5c42\u539a\u5ea6");
    model.param().set("sigma", "a^2*pi/(Lx*Ly)", "\u5b54\u9699\u7387");
    model.param().set("c0", "343[m/s]", "\u58f0\u901f");
    model.param().set("rho0", "1.2[kg/m^3]", "\u5bc6\u5ea6");
    model.param().set("p0", "1[Pa]", "\u5165\u5c04\u6a21\u5f0f");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "a");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "tp");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "0", "-tp/2"});
    model.component("comp1").geom("geom1").feature("cyl1").set("layerside", false);
    model.component("comp1").geom("geom1").feature("cyl1").set("layerbottom", true);
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("layer", "tp/2", 0);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"Lx/2", "Ly/2", "2*Lz+tp"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "0", "-Lz-tp/2"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", "Lz", 0);
    model.component("comp1").geom("geom1").feature("blk1").set("layertop", true);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("blk1", "cyl1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("uni1", 1, 2, 7);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("Ztrans", "(intop_in(ta.p_t)-intop_out(ta.p_t))/intop_mid(w)/(rho0*c0)", "\u8f6c\u79fb\u963b\u6297\uff08COMSOL\uff0c\u5f52\u4e00\u5316\uff09");
    model.component("comp1").variable("var1")
         .set("Zn", "(intop_in(ta.p_t)/intop_in(ta.u_tz))/(c0*rho0)", "\u9762\u6cd5\u5411\u963b\u6297\uff08\u5f52\u4e00\u5316\uff09");
    model.component("comp1").variable("var1").set("R", "ta.port1.P_out/ta.port1.P_in", "\u53cd\u5c04\u7cfb\u6570");
    model.component("comp1").variable("var1").set("alpha", "1-abs(R)^2", "\u5438\u6536\u7cfb\u6570");
    model.component("comp1").variable("var1")
         .set("ks", "sqrt(-ta.rho0*ta.iomega/ta.mu)", "\u526a\u5207\u6ce2\u6570\uff08\u9ecf\u6ede\uff09");
    model.component("comp1").variable("var1")
         .set("Z1", "ta.iomega/(c0*sigma*(1-2/(ks*a)*besselj(1,ks*a)/besselj(0,ks*a)))", "\u5706\u67f1\u4f53\u963b\u6297\u8868\u8fbe\u5f0f\uff08\u7ebf\u6027\u8d21\u732e\uff09");
    model.component("comp1").variable("var1")
         .set("EC", "16*a/(3*pi)*(1-1.4092*sigma^(1/2)+0.33818*sigma^(3/2)+0.06793*sigma^(5/2)-0.02287*sigma^(6/2)+0.03015*sigma^(7/2)-0.01641*sigma^(8/2))", "\u7aef\u90e8\u4fee\u6b63\u548c\u5b54-\u5b54\u76f8\u4e92\u4f5c\u7528\uff08Fok \u51fd\u6570\uff09");
    model.component("comp1").variable("var1").set("Cd", "0.76", "\u6d41\u91cf\u7cfb\u6570");
    model.component("comp1").variable("var1")
         .set("U", "intop_mid(w)/intop_mid(1)", "\u5b54\u4e2d\u7684\u5e73\u5747\u901f\u5ea6");
    model.component("comp1").variable("var1")
         .set("Z2", "(1.2*(1-sigma^2))/(2*c0*(sigma*Cd)^2)*sqrt(0.5*U*conj(U))", "\u5bf9\u963b\u6297\u7684\u975e\u7ebf\u6027\u8d21\u732e");
    model.component("comp1").variable("var1")
         .set("Ztrans_ana", "((tp+EC)*Z1+Z2)", "\u8f6c\u79fb\u963b\u6297\uff08\u89e3\u6790\uff0c\u5f52\u4e00\u5316\uff09");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().set(6, 15);
    model.component("comp1").cpl("intop1").set("opname", "intop_in");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop2").selection().set(12, 17);
    model.component("comp1").cpl("intop2").set("opname", "intop_out");
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop3").selection().set(9);
    model.component("comp1").cpl("intop3").set("opname", "intop_mid");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u5bf9\u79f0");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set("groupcontang", true);
    model.component("comp1").selection("sel1").add(1, 2, 4, 5, 7, 8, 10, 11, 18, 19, 20, 21);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u58c1");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(14, 15, 16, 17);

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

    model.component("comp1").physics("ta").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ta").feature("sym1").selection().named("sel1");
    model.component("comp1").physics("ta").create("port1", "Port", 2);
    model.component("comp1").physics("ta").feature("port1").selection().set(3);
    model.component("comp1").physics("ta").feature("port1").set("PortType", "PlaneWave");
    model.component("comp1").physics("ta").feature("port1").set("pamp", "p0");
    model.component("comp1").physics("ta").create("port2", "Port", 2);
    model.component("comp1").physics("ta").feature("port2").selection().set(13);
    model.component("comp1").physics("ta").feature("port2").set("PortType", "PlaneWave");

    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmax", "a/6");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "a");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "dvisc/2");
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.3);
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 4);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(14, 16);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 4);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "0.4*dvisc");
    model.component("comp1").mesh("mesh1").run("bl1");
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("elemcount", 8);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("elemratio", 2);
    model.component("comp1").mesh("mesh1").feature("swe2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("size1").selection().set(17, 21);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("size1").set("hmax", "dvisc");
    model.component("comp1").mesh("mesh1").run("swe2");
    model.component("comp1").mesh("mesh1").create("bl2", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl2").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl2").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").selection().set(6, 12, 15, 17);
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("blnlayers", 4);
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl2").feature("blp").set("blhmin", "0.4*dvisc");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq")
         .set("plist", "{20, 25, 31.5, 40, 50, 63, 80, 100, 125, 160, 200, 250, 315, 400, 500, 630, 800, 1e3, 1.25e3, 1.6e3, 2e3, 2.5e3, 3.15e3, 4e3, 5e3, 6.3e3, 8e3, 1e4, 1.25e4, 1.6e4, 2e4}");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("i1").active(true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u58f0\u538b (ta)");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("colortable", "Wave");
    model.result("pg1").feature("mslc1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u58f0\u901f (ta)");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").feature().create("slc1", "Slice");
    model.result("pg2").feature("slc1").label("\u5207\u9762");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("expr", "ta.v_inst");
    model.result("pg2").feature("slc1").set("smooth", "internal");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("data", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6\u53d8\u5316 (ta)");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").feature().create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("expr", "ta.T_t");
    model.result("pg3").feature("mslc1").set("colortable", "ThermalWave");
    model.result("pg3").feature("mslc1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").feature("mslc1").set("smooth", "internal");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("xnumber", "2");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 14, 0);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").feature("mslc1").set("xnumber", "2");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 14, 0);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u8f6c\u79fb\u963b\u6297");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "f (Hz)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u8f6c\u79fb\u963b\u6297 (1)");
    model.result("pg4").set("xlog", true);
    model.result("pg4").set("ylog", true);
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "real(Ztrans)", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "COMSOL \u6a21\u578b (real)", 0);
    model.result("pg4").feature("glob1").setIndex("expr", "imag(Ztrans)", 1);
    model.result("pg4").feature("glob1").setIndex("descr", "COMSOL \u6a21\u578b (imag)", 1);
    model.result("pg4").feature("glob1").setIndex("expr", "abs(Ztrans)", 2);
    model.result("pg4").feature("glob1").setIndex("descr", "COMSOL \u6a21\u578b (abs)", 2);
    model.result("pg4").feature("glob1").set("linestyle", "cycle");
    model.result("pg4").feature("glob1").set("linecolor", "blue");
    model.result("pg4").run();
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").selection().set(5);
    model.result("pg4").feature("ptgr1").set("expr", "real(Ztrans_ana)");
    model.result("pg4").feature("ptgr1").set("linecolor", "red");
    model.result("pg4").feature("ptgr1").set("legend", true);
    model.result("pg4").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg4").feature("ptgr1").setIndex("legends", "\u534a\u89e3\u6790\u6a21\u578b (real)", 0);
    model.result("pg4").run();
    model.result("pg4").create("ptgr2", "PointGraph");
    model.result("pg4").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr2").set("linewidth", "preference");
    model.result("pg4").feature("ptgr2").selection().set(5);
    model.result("pg4").feature("ptgr2").set("expr", "imag(Ztrans_ana)");
    model.result("pg4").feature("ptgr2").set("linestyle", "dotted");
    model.result("pg4").feature("ptgr2").set("linecolor", "red");
    model.result("pg4").feature("ptgr2").set("legend", true);
    model.result("pg4").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg4").feature("ptgr2").setIndex("legends", "\u534a\u89e3\u6790\u6a21\u578b (imag)", 0);
    model.result("pg4").run();
    model.result("pg4").create("ptgr3", "PointGraph");
    model.result("pg4").feature("ptgr3").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr3").set("linewidth", "preference");
    model.result("pg4").feature("ptgr3").selection().set(5);
    model.result("pg4").feature("ptgr3").set("expr", "abs(Ztrans_ana)");
    model.result("pg4").feature("ptgr3").set("linestyle", "dashed");
    model.result("pg4").feature("ptgr3").set("linecolor", "red");
    model.result("pg4").feature("ptgr3").set("legend", true);
    model.result("pg4").feature("ptgr3").set("legendmethod", "manual");
    model.result("pg4").feature("ptgr3").setIndex("legends", "\u534a\u89e3\u6790\u6a21\u578b (abs)", 0);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u9762\u6cd5\u5411\u963b\u6297");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "f (Hz)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u9762\u6cd5\u5411\u963b\u6297 (1)");
    model.result("pg5").set("xlog", true);
    model.result("pg5").set("showlegends", false);
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "abs(Zn)", 0);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u5438\u58f0\u7cfb\u6570");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "f (Hz)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u5438\u58f0\u7cfb\u6570 (1)");
    model.result("pg6").set("xlog", true);
    model.result("pg6").set("showlegends", false);
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "alpha", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "", 0);
    model.result("pg6").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset().create("mir2", "Mirror3D");
    model.result().dataset("mir2").set("data", "mir1");
    model.result().dataset("mir2").set("quickplane", "xz");
    model.result().dataset().create("mir3", "Mirror3D");
    model.result().dataset("mir3").set("data", "mir2");
    model.result().dataset("mir3").set("planetype", "general");
    model.result().dataset("mir3").set("genmethod", "pointnormal");
    model.result().dataset("mir3").set("genpnpoint", new String[]{"Lx/2", "0", "0"});

    return model;
  }

  public static Model run2(Model model) {
    model.result().dataset("mir3").set("genpnvec", new int[]{1, 0, 0});
    model.result().dataset().create("mir4", "Mirror3D");
    model.result().dataset("mir4").set("data", "mir3");
    model.result().dataset("mir4").set("planetype", "general");
    model.result().dataset("mir4").set("genmethod", "pointnormal");
    model.result().dataset("mir4").set("genpnpoint", new String[]{"0", "Ly/2", "0"});
    model.result().dataset("mir4").set("genpnvec", new int[]{0, 1, 0});
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").label("\u955c\u50cf\u56fe\uff1a\u901f\u5ea6");
    model.result("pg7").set("data", "mir4");
    model.result("pg7").setIndex("looplevel", 14, 0);
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").create("slc1", "Slice");
    model.result("pg7").feature("slc1").set("expr", "ta.v_inst");
    model.result("pg7").feature("slc1").set("quickxnumber", 2);
    model.result("pg7").run();

    model.title("\u7a7f\u5b54\u677f\u7684\u8f6c\u79fb\u963b\u6297");

    model
         .description("\u7a7f\u5b54\u677f\u662f\u5206\u5e03\u6709\u5c0f\u578b\u7a7f\u5b54\u6216\u5b54\u7684\u677f\uff0c\u7528\u4e8e\u6d88\u58f0\u5668\u7cfb\u7edf\u3001\u5438\u97f3\u9762\u677f\u4ee5\u53ca\u8bf8\u5982\u886c\u57ab\u7b49\u5176\u4ed6\u5404\u5904\u6765\u7cbe\u786e\u63a7\u5236\u8870\u51cf\u3002\u7531\u4e8e\u7a7f\u5b54\u53d8\u5f97\u8d8a\u6765\u8d8a\u5c0f\uff0c\u9ecf\u6ede\u635f\u8017\u548c\u70ed\u635f\u8017\u5c31\u53d8\u5f97\u66f4\u91cd\u8981\u3002\u8870\u51cf\u7279\u6027\u4e5f\u4e0e\u9891\u7387\u76f8\u5173\uff0c\u53ef\u4ee5\u901a\u8fc7\u9009\u62e9\u7a7f\u5b54\u677f\u5c3a\u5bf8\u53ca\u5176\u5728\u677f\u4e0a\u7684\u5206\u5e03\u6765\u63a7\u5236\u3002\n\n\u6709\u5173\u7a7f\u5b54\u677f\u7684\u7406\u8bba\u7814\u7a76\u5df2\u7ecf\u8fdb\u884c\u4e86\u8bb8\u591a\u5e74\uff0c\u4f46\u89e3\u6790\u6a21\u578b\u6216\u534a\u89e3\u6790\u6a21\u578b\u53ea\u80fd\u7528\u4e8e\u7b80\u5355\u7684\u51e0\u4f55\u3002\u5728\u5b54\u7684\u6a2a\u622a\u9762\u5f62\u72b6\u5404\u5f02\u7684\u7cfb\u7edf\u4e2d\uff0c\u5982\u679c\u7a7f\u5b54\u5448\u9525\u5f62\u6216\u8005\u5b54\u7684\u5206\u5e03\u4e0d\u5747\u5300\uff0c\u5219\u9700\u8981\u91c7\u7528\u6570\u503c\u65b9\u6cd5\u3002\n\n\u672c\u6559\u7a0b\u4f7f\u7528\u201c\u70ed\u9ecf\u6027\u58f0\u5b66\uff0c\u9891\u57df\u201d\u63a5\u53e3\u6a21\u62df\u4e86\u4e0d\u540c\u7684\u6548\u5e94\u3002\u5c3d\u7ba1\u5728\u9ad8\u58f0\u538b\u7ea7\u6216\u5b58\u5728\u6d41\u52a8\uff08\u6d41\u7ecf\u6216\u6d41\u8fc7\u7a7f\u5b54\u677f\uff09\u7684\u60c5\u51b5\u4e0b\u4f1a\u53d1\u751f\u975e\u7ebf\u6027\u635f\u8017\u673a\u5236\uff0c\u672c\u6559\u7a0b\u4ec5\u7814\u7a76\u7531\u9ecf\u6027\u548c\u70ed\u4f20\u5bfc\u5f15\u8d77\u7684\u7ebf\u6027\u6548\u5e94\u3002\u786e\u5b9a\u4e86\u7cfb\u7edf\u7684\u8f6c\u79fb\u963b\u6297\u3001\u8868\u9762\u6cd5\u5411\u963b\u6297\u548c\u8870\u51cf\u7cfb\u6570\u3002\n\n\u6bd4\u8f83\u4e86\u8f6c\u79fb\u963b\u6297\u4e0e\u534a\u89e3\u6790\u6a21\u578b\u4e2d\u7684\u503c\u3002\u4f7f\u7528\u201c\u538b\u529b\u58f0\u5b66\uff0c\u9891\u57df\u201d\u63a5\u53e3\u4e2d\u7684\u5185\u90e8\u963b\u6297\u6761\u4ef6\uff0c\u53ef\u4ee5\u5c06\u6b64\u8be6\u7ec6\u6a21\u578b\u4e2d\u8ba1\u7b97\u5f97\u5230\u7684\u8f6c\u79fb\u963b\u6297\u5e94\u7528\u5230\u66f4\u5927\u578b\u7684\u7cfb\u7edf\u4eff\u771f\u4e2d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("transfer_impedance_perforate.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
