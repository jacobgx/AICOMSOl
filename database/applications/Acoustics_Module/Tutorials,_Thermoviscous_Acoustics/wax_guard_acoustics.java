/*
 * wax_guard_acoustics.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class wax_guard_acoustics {

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

    model.param().label("\u53c2\u6570\uff1a\u6a21\u578b");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T0", "20[degC]", "\u73af\u5883\u6e29\u5ea6");
    model.param().set("pA", "1[atm]", "\u73af\u5883\u5927\u6c14\u538b");
    model.param().set("f0", "10000[Hz]", "\u6700\u5927\u9891\u7387");
    model.param().set("dvisc", "0.22[mm]*sqrt(100[Hz]/f0)", "f0 \u7684\u9ecf\u6ede\u8fb9\u754c\u5c42\u539a\u5ea6");
    model.param().set("Vrec", "0.1[V]", "\u63a5\u6536\u5668\u7535\u538b\uff08\u5cf0\u503c\uff09");
    model.param().set("PortName", "1", "\u7aef\u53e3\u540d\u79f0");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570\uff1a\u7ba1\u6bb5");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("L_in", "1[mm]", "\u5165\u53e3\u7ba1\u957f\u5ea6");
    model.param("par2").set("a_in", "0.5[mm]", "\u5165\u53e3\u7ba1\u534a\u5f84");
    model.param("par2").set("S_in", "pi*a_in^2", "\u5165\u53e3\u7ba1\u6a2a\u622a\u9762\u79ef");
    model.param("par2").set("L_rt", "1.19[mm]", "\u63a5\u6536\u5668\u7ba1\u957f\u5ea6");
    model.param("par2").set("a_rt", "0.725[mm]", "\u63a5\u6536\u5668\u7ba1\u5f84");
    model.param("par2").set("S_rt", "pi*a_rt^2", "\u63a5\u6536\u5668\u7ba1\u6a2a\u622a\u9762\u79ef");

    model.variable().create("var1");
    model.variable("var1").label("\u53d8\u91cf\uff1a\u7a84\u7ba1\u4f20\u9012\u77e9\u9635");

//    To import content from file, use:
//    model.variable("var1").loadFile("FILENAME");
    model.variable("var1").set("Zmic", "Zmic_real(freq)+i*Zmic_imag(freq)", "\u9ea6\u514b\u98ce\u963b\u6297");
    model.variable("var1").set("omega", "2*pi*freq", "\u89d2\u9891\u7387");
    model.variable("var1").set("mu", "mat1.def.eta(T0)", "\u52a8\u529b\u9ecf\u5ea6");
    model.variable("var1").set("gamma", "mat1.def.gamma", "\u6bd4\u70ed\u7387");
    model.variable("var1").set("Cp", "mat1.def.Cp(T0)", "\u6052\u538b\u70ed\u5bb9");
    model.variable("var1").set("rho", "mat1.def.rho(pA,T0)", "\u5bc6\u5ea6");
    model.variable("var1").set("kcond", "mat1.def.k(T0)", "\u5bfc\u70ed\u7cfb\u6570");
    model.variable("var1").set("c", "mat1.def.cs(T0)", "\u58f0\u901f");
    model.variable("var1").set("k0", "omega/c", "\u7edd\u70ed\u6ce2\u6570");
    model.variable("var1").set("Z0", "rho*c", "\u7edd\u70ed\u7279\u6027\u963b\u6297");
    model.variable("var1").set("kv", "sqrt(-i*omega*rho/mu)", "\u9ecf\u6027\u6ce2\u6570");
    model.variable("var1").set("kth", "sqrt(-i*omega*rho*Cp/kcond)", "\u70ed\u6ce2\u6570");
    model.variable("var1")
         .set("Yv_in", "-besselj(2,kv*a_in)/besselj(0,kv*a_in)", "\u6807\u91cf\u70ed\u573a\u7684\u5e73\u5747\u503c");
    model.variable("var1")
         .set("Yth_in", "-besselj(2,kth*a_in)/besselj(0,kth*a_in)", "\u6807\u91cf\u9ecf\u6027\u573a\u7684\u5e73\u5747\u503c");
    model.variable("var1")
         .set("Zc_in", "Z0/sqrt(Yv_in*(gamma-(gamma-1)*Yth_in))", "\u6a21\u5f0f\u7279\u6027\u963b\u6297");
    model.variable("var1")
         .set("kc_sq_in", "k0^2*(gamma-(gamma-1)*Yth_in)/Yv_in", "\u6a21\u5f0f\u6ce2\u6570\u5e73\u65b9");
    model.variable("var1").set("kc_in", "sqrt(kc_sq_in)", "\u6a21\u5f0f\u6ce2\u6570");
    model.variable("var1")
         .set("Yv_rt", "-besselj(2,kv*a_rt)/besselj(0,kv*a_rt)", "\u6807\u91cf\u9ecf\u6027\u573a\u7684\u5e73\u5747\u503c");
    model.variable("var1")
         .set("Yth_rt", "-besselj(2,kth*a_rt)/besselj(0,kth*a_rt)", "\u6807\u91cf\u70ed\u573a\u7684\u5e73\u5747\u503c");
    model.variable("var1")
         .set("Zc_rt", "Z0/sqrt(Yv_rt*(gamma-(gamma-1)*Yth_rt))", "\u6a21\u5f0f\u7279\u6027\u963b\u6297");
    model.variable("var1")
         .set("kc_sq_rt", "k0^2*(gamma-(gamma-1)*Yth_rt)/Yv_rt", "\u6a21\u5f0f\u6ce2\u6570\u5e73\u65b9");
    model.variable("var1").set("kc_rt", "sqrt(kc_sq_rt)", "\u6a21\u5f0f\u6ce2\u6570");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.material("mat1").propertyGroup().create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.material("mat1").propertyGroup().create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.material("mat1").label("Air");
    model.material("mat1").set("family", "air");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.material("mat1").propertyGroup("def").func("rho").set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.material("mat1").propertyGroup("def").func("rho").set("argunit", new String[]{"Pa", "K"});
    model.material("mat1").propertyGroup("def").func("rho").set("plotaxis", new String[]{"off", "on"});
    model.material("mat1").propertyGroup("def").func("rho").set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.material("mat1").propertyGroup("def").func("cs").set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("cs").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"Pa", "K"});
    model.material("mat1").propertyGroup("def").func("an1").set("plotaxis", new String[]{"off", "on"});
    model.material("mat1").propertyGroup("def").func("an1").set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an2").set("plotfixedvalue", new String[]{"200"});
    model.material("mat1").propertyGroup("def").func("an2").set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.material("mat1").propertyGroup("def").set("molarmass", "");
    model.material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.material("mat1").propertyGroup("def").addInput("temperature");
    model.material("mat1").propertyGroup("def").addInput("pressure");
    model.material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.material("mat1").propertyGroup("idealGas").label("Ideal gas");
    model.material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.material("mat1").materialType("nonSolid");

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").insertFile("wax_guard_acoustics_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("ige2");

    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material("matlnk1").label("\u6750\u6599\u94fe\u63a5\uff1a\u7a7a\u6c14");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u6240\u6709\u57df");
    model.component("comp1").selection("sel1").all();
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u5165\u53e3\uff08\u7aef\u53e3 1\uff09");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(3);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u51fa\u53e3\uff08\u7aef\u53e3 2\uff09");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(14);
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u6240\u6709\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("adj1").set("input", new String[]{"sel1"});
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u58c1\uff08\u8fb9\u754c\u5c42\u7f51\u683c\uff09");
    model.component("comp1").selection("dif1").set("entitydim", 2);
    model.component("comp1").selection("dif1").set("add", new String[]{"adj1"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"sel2", "sel3"});

    model.component("comp1").physics("ta").feature("tam1").set("minput_pressure", "pA");
    model.component("comp1").physics("ta").feature("tam1").set("minput_temperature", "T0");
    model.component("comp1").physics("ta").create("port1", "Port", 2);
    model.component("comp1").physics("ta").feature("port1").selection().named("sel2");
    model.component("comp1").physics("ta").feature("port1").set("PortType", "Circular");
    model.component("comp1").physics("ta").feature("port1").set("pamp", 1);
    model.component("comp1").physics("ta").create("port2", "Port", 2);
    model.component("comp1").physics("ta").feature("port2").selection().named("sel3");
    model.component("comp1").physics("ta").feature("port2").set("PortType", "Circular");
    model.component("comp1").physics("ta").prop("PortSweepSettings").set("PortSweep", "StandardSweep");

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "100[um]");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "40[um]");
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.5);
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 1);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(2);
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh1").feature("bl1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("bl1").set("sharpcorners", "trim");
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("dif1");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 5);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "0.15*dvisc");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq")
         .set("plist", "{100, 125, 160, 200, 250, 315, 400, 500, 630, 800, 1e3, 1.25e3, 1.6e3, 2e3, 2.5e3, 3.15e3, 4e3, 5e3, 6.3e3, 8e3, 1e4}");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "a_in", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "a_in", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "PortName", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1 2", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("i1").active(true);

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u58f0\u538b (ta)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 21, 0);
    model.result("pg1").setIndex("looplevel", 2, 1);
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
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 21, 0);
    model.result("pg2").setIndex("looplevel", 2, 1);
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
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 21, 0);
    model.result("pg3").setIndex("looplevel", 2, 1);
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
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("colorscalemode", "linear");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 11, 0);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").label("\u5168\u7cfb\u7edf T \u77e9\u9635");

    model.result().create("pg4", "PlotGroup1D");

    model.nodeGroup("grp1").add("plotgroup", "pg4");

    model.result("pg4").run();
    model.result("pg4").label("\u5168\u7cfb\u7edf T11");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "real(ta.T11)", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "real(T11)", 0);
    model.result("pg4").feature("glob1").setIndex("expr", "imag(ta.T11)", 1);
    model.result("pg4").feature("glob1").setIndex("descr", "imag(T11)", 1);
    model.result("pg4").set("ylog", true);
    model.result("pg4").set("xlog", true);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");

    model.nodeGroup("grp1").add("plotgroup", "pg5");

    model.result("pg5").run();
    model.result("pg5").label("\u5168\u7cfb\u7edf T12");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").setIndex("expr", "real(ta.T12)", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "real(T12)", 0);
    model.result("pg5").feature("glob1").setIndex("expr", "imag(ta.T12)", 1);
    model.result("pg5").feature("glob1").setIndex("descr", "imag(T12)", 1);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");

    model.nodeGroup("grp1").add("plotgroup", "pg6");

    model.result("pg6").run();
    model.result("pg6").label("\u5168\u7cfb\u7edf T21");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").setIndex("expr", "real(ta.T21)", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "real(T21)", 0);
    model.result("pg6").feature("glob1").setIndex("expr", "imag(ta.T21)", 1);
    model.result("pg6").feature("glob1").setIndex("descr", "imag(T21)", 1);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");

    model.nodeGroup("grp1").add("plotgroup", "pg7");

    model.result("pg7").run();
    model.result("pg7").label("\u5168\u7cfb\u7edf T22");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").setIndex("expr", "real(ta.T22)", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "real(T22)", 0);
    model.result("pg7").feature("glob1").setIndex("expr", "imag(ta.T22)", 1);
    model.result("pg7").feature("glob1").setIndex("descr", "imag(T22)", 1);
    model.result("pg7").run();

    model.func().create("int1", "Interpolation");
    model.func("int1").label("\u63d2\u503c\uff1a\u63a5\u6536\u5668 T \u77e9\u9635");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "wax_guard_acoustics_T_receiver.csv");
    model.func("int1").setEntry("columnType", "col2", "value");
    model.func("int1").setEntry("columnType", "col3", "value");
    model.func("int1").setEntry("columnType", "col5", "value");
    model.func("int1").setEntry("columnType", "col6", "value");
    model.func("int1").setEntry("columnType", "col7", "value");
    model.func("int1").setEntry("columnType", "col8", "value");
    model.func("int1").setEntry("columnType", "col9", "value");
    model.func("int1").setIndex("argunit", "Hz", 0);
    model.func("int1").setEntry("funcnames", "col2", "Trec11_real");
    model.func("int1").setIndex("fununit", "1", 0);
    model.func("int1").setEntry("funcnames", "col3", "Trec11_imag");
    model.func("int1").setIndex("fununit", "1", 1);
    model.func("int1").setEntry("funcnames", "col4", "Trec12_real");
    model.func("int1").setIndex("fununit", "1", 2);
    model.func("int1").setEntry("funcnames", "col5", "Trec12_imag");
    model.func("int1").setIndex("fununit", "1", 3);
    model.func("int1").setEntry("funcnames", "col6", "Trec21_real");
    model.func("int1").setIndex("fununit", "1", 4);
    model.func("int1").setEntry("funcnames", "col7", "Trec21_imag");
    model.func("int1").setIndex("fununit", "1", 5);
    model.func("int1").setEntry("funcnames", "col8", "Trec22_real");
    model.func("int1").setIndex("fununit", "1", 6);
    model.func("int1").setEntry("funcnames", "col9", "Trec22_imag");
    model.func("int1").setIndex("fununit", "1", 7);
    model.func("int1").importData();
    model.func().create("int2", "Interpolation");
    model.func("int2").label("\u63d2\u503c\uff1a\u8026\u5408\u5668 T \u77e9\u9635");
    model.func("int2").set("source", "file");
    model.func("int2").set("filename", "wax_guard_acoustics_T_coupler.csv");
    model.func("int2").setEntry("columnType", "col2", "value");
    model.func("int2").setEntry("columnType", "col3", "value");
    model.func("int2").setEntry("columnType", "col5", "value");
    model.func("int2").setEntry("columnType", "col6", "value");
    model.func("int2").setEntry("columnType", "col7", "value");
    model.func("int2").setEntry("columnType", "col8", "value");
    model.func("int2").setEntry("columnType", "col9", "value");
    model.func("int2").setIndex("argunit", "Hz", 0);
    model.func("int2").setEntry("funcnames", "col2", "Tcp11_real");
    model.func("int2").setIndex("fununit", "1", 0);
    model.func("int2").setEntry("funcnames", "col3", "Tcp11_imag");
    model.func("int2").setIndex("fununit", "1", 1);
    model.func("int2").setEntry("funcnames", "col4", "Tcp12_real");
    model.func("int2").setIndex("fununit", "1", 2);
    model.func("int2").setEntry("funcnames", "col5", "Tcp12_imag");
    model.func("int2").setIndex("fununit", "1", 3);
    model.func("int2").setEntry("funcnames", "col6", "Tcp21_real");
    model.func("int2").setIndex("fununit", "1", 4);
    model.func("int2").setEntry("funcnames", "col7", "Tcp21_imag");
    model.func("int2").setIndex("fununit", "1", 5);
    model.func("int2").setEntry("funcnames", "col8", "Tcp22_real");
    model.func("int2").setIndex("fununit", "1", 6);
    model.func("int2").setEntry("funcnames", "col9", "Tcp22_imag");
    model.func("int2").setIndex("fununit", "1", 7);
    model.func("int2").importData();
    model.func().create("int3", "Interpolation");
    model.func("int3").label("\u63d2\u503c\uff1a\u9ea6\u514b\u98ce\u963b\u6297");
    model.func("int3").set("source", "file");
    model.func("int3").set("filename", "wax_guard_acoustics_mic_impedance.csv");
    model.func("int3").setIndex("argunit", "Hz", 0);
    model.func("int3").setEntry("columnType", "col2", "value");
    model.func("int3").setEntry("funcnames", "col2", "Zmic_real");
    model.func("int3").setIndex("fununit", "1", 0);
    model.func("int3").setEntry("funcnames", "col3", "Zmic_imag");
    model.func("int3").setIndex("fununit", "1", 1);
    model.func("int3").importData();
    model.func().create("int4", "Interpolation");
    model.func("int4").label("\u63d2\u503c\uff1a\u6d4b\u91cf\u7ed3\u679c");
    model.func("int4").set("source", "file");
    model.func("int4").set("filename", "wax_guard_acoustics_measurements.csv");
    model.func("int4").setEntry("columnType", "col2", "value");
    model.func("int4").setEntry("columnType", "col3", "value");
    model.func("int4").setEntry("columnType", "col4", "none");
    model.func("int4").setIndex("argunit", "Hz", 0);
    model.func("int4").setEntry("funcnames", "col2", "pWGon_real");
    model.func("int4").setIndex("fununit", "Pa", 0);
    model.func("int4").setEntry("funcnames", "col3", "pWGon_imag");
    model.func("int4").setIndex("fununit", "Pa", 1);
    model.func("int4").importData();

    model.common().create("matinv1", "MatrixInverse", "");
    model.common("matinv1").label("\u77e9\u9635\u6c42\u9006\uff1a\u5165\u53e3\u7ba1");
    model.common("matinv1").set("size", "2");
    model.common("matinv1").setIndex("matrix", "cos(kc_in*L_in)", 0, 0);
    model.common("matinv1").setIndex("matrix", "-Zc_in/(i*S_in)*sin(kc_in*L_in)", 0, 1);
    model.common("matinv1").setIndex("matrix", "i*S_in/Zc_in*sin(kc_in*L_in)", 1, 0);
    model.common("matinv1").setIndex("matrix", "cos(kc_in*L_in)", 1, 1);
    model.common().create("mat1", "Matrix", "");
    model.common("mat1").label("\u77e9\u9635\uff1a\u8035\u804d\u6321\u677f\uff08\u6a21\u578b\uff09");
    model.common("mat1").set("name", "Twg");
    model.common("mat1").set("size", "2");
    model.common("mat1").setIndex("matrix", "matinv1.invT11*comp1.ta.T11+matinv1.invT12*comp1.ta.T21", 0, 0);
    model.common("mat1").setIndex("matrix", "matinv1.invT11*comp1.ta.T12+matinv1.invT12*comp1.ta.T22", 0, 1);
    model.common("mat1").setIndex("matrix", "matinv1.invT21*comp1.ta.T11+matinv1.invT22*comp1.ta.T21", 1, 0);
    model.common("mat1").setIndex("matrix", "matinv1.invT21*comp1.ta.T12+matinv1.invT22*comp1.ta.T22", 1, 1);
    model.common().create("mat2", "Matrix", "");
    model.common("mat2").label("\u77e9\u9635\uff1a\u63a5\u6536\u5668");
    model.common("mat2").set("name", "Trec");
    model.common("mat2").set("size", "2");
    model.common("mat2").setIndex("matrix", "Trec11_real(freq)+i*Trec11_imag(freq)", 0, 0);

    return model;
  }

  public static Model run2(Model model) {
    model.common("mat2").setIndex("matrix", "Trec12_real(freq)+i*Trec12_imag(freq)", 0, 1);
    model.common("mat2").setIndex("matrix", "Trec21_real(freq)+i*Trec21_imag(freq)", 1, 0);
    model.common("mat2").setIndex("matrix", "Trec22_real(freq)+i*Trec22_imag(freq)", 1, 1);
    model.common().create("mat3", "Matrix", "");
    model.common("mat3").label("\u77e9\u9635\uff1a\u63a5\u6536\u5668\u7ba1");
    model.common("mat3").set("name", "Trt");
    model.common("mat3").set("size", "2");
    model.common("mat3").setIndex("matrix", "cos(kc_rt*L_rt)", 0, 0);
    model.common("mat3").setIndex("matrix", "-Zc_rt/(i*S_rt)*sin(kc_rt*L_rt)", 0, 1);
    model.common("mat3").setIndex("matrix", "i*S_rt/Zc_rt*sin(kc_rt*L_rt)", 1, 0);
    model.common("mat3").setIndex("matrix", "cos(kc_rt*L_rt)", 1, 1);
    model.common().create("mat4", "Matrix", "");
    model.common("mat4").label("\u77e9\u9635\uff1a\u8026\u5408\u5668");
    model.common("mat4").set("name", "Tcp");
    model.common("mat4").set("size", "2");
    model.common("mat4").setIndex("matrix", "Tcp11_real(freq)+i*Tcp11_imag(freq)", 0, 0);
    model.common("mat4").setIndex("matrix", "Tcp12_real(freq)+i*Tcp12_imag(freq)", 0, 1);
    model.common("mat4").setIndex("matrix", "Tcp21_real(freq)+i*Tcp21_imag(freq)", 1, 0);
    model.common("mat4").setIndex("matrix", "Tcp22_real(freq)+i*Tcp22_imag(freq)", 1, 1);

    model.nodeGroup().create("grp2", "GlobalDefinitions");
    model.nodeGroup("grp2").label("\u7ec4\uff1a\u77e9\u9635\u4e58\u79ef");

    model.common().create("mat5", "Matrix", "");

    model.nodeGroup("grp2").add("common", "mat5");

    model.common("mat5").set("size", "2");
    model.common("mat5").setIndex("matrix", "Trec11*Trt11+Trec12*Trt21", 0, 0);
    model.common("mat5").setIndex("matrix", "Trec11*Trt12+Trec12*Trt22", 0, 1);
    model.common("mat5").setIndex("matrix", "Trec21*Trt11+Trec22*Trt21", 1, 0);
    model.common("mat5").setIndex("matrix", "Trec21*Trt12+Trec22*Trt22", 1, 1);
    model.common().create("mat6", "Matrix", "");

    model.nodeGroup("grp2").add("common", "mat6");

    model.common("mat6").set("size", "2");
    model.common("mat6").setIndex("matrix", "T511*Twg11+T512*Twg21", 0, 0);
    model.common("mat6").setIndex("matrix", "T511*Twg12+T512*Twg22", 0, 1);
    model.common("mat6").setIndex("matrix", "T521*Twg11+T522*Twg21", 1, 0);
    model.common("mat6").setIndex("matrix", "T521*Twg12+T522*Twg22", 1, 1);
    model.common().create("mat7", "Matrix", "");

    model.nodeGroup("grp2").add("common", "mat7");

    model.common("mat7").set("size", "2");
    model.common("mat7").setIndex("matrix", "T611*Tcp11+T612*Tcp21", 0, 0);
    model.common("mat7").setIndex("matrix", "T611*Tcp12+T612*Tcp22", 0, 1);
    model.common("mat7").setIndex("matrix", "T621*Tcp11+T622*Tcp21", 1, 0);
    model.common("mat7").setIndex("matrix", "T621*Tcp12+T622*Tcp22", 1, 1);

    model.sol("sol1").updateSolution();
    model.sol("sol2").updateSolution();

    model.result("pg4").run();

    model.nodeGroup().duplicate("grp3", "grp1");
    model.nodeGroup("grp3").label("\u8035\u804d\u6321\u677f T \u77e9\u9635");

    model.result("pg8").run();
    model.result("pg8").label("\u8035\u804d\u6321\u677f T11");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").setIndex("expr", "real(Twg11)", 0);
    model.result("pg8").feature("glob1").setIndex("expr", "imag(Twg11)", 1);
    model.result("pg8").run();
    model.result("pg9").run();
    model.result("pg9").label("\u8035\u804d\u6321\u677f T12");
    model.result("pg9").run();
    model.result("pg9").feature("glob1").setIndex("expr", "real(Twg12)", 0);
    model.result("pg9").feature("glob1").setIndex("expr", "imag(Twg12)", 1);
    model.result("pg9").run();
    model.result("pg10").run();
    model.result("pg10").label("\u8035\u804d\u6321\u677f T21");
    model.result("pg10").run();
    model.result("pg10").feature("glob1").setIndex("expr", "real(Twg21)", 0);
    model.result("pg10").feature("glob1").setIndex("expr", "imag(Twg21)", 1);
    model.result("pg10").run();
    model.result("pg11").run();
    model.result("pg11").label("\u8035\u804d\u6321\u677f T22");
    model.result("pg11").run();
    model.result("pg11").feature("glob1").setIndex("expr", "real(Twg22)", 0);
    model.result("pg11").feature("glob1").setIndex("expr", "imag(Twg22)", 1);
    model.result("pg11").run();
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").run();
    model.result("pg12").label("\u7f51\u683c");
    model.result("pg12").create("mesh1", "Mesh");
    model.result("pg12").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg12").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg12").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg12").feature("mesh1").set("meshdomain", "volume");
    model.result("pg12").feature("mesh1").set("filteractive", true);
    model.result("pg12").feature("mesh1").set("logfilterexpr", "x>0");
    model.result("pg12").run();
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").run();
    model.result("pg13").label("\u5168\u7cfb\u7edf\u54cd\u5e94");
    model.result("pg13").set("titletype", "label");
    model.result("pg13").set("ylabelactive", true);
    model.result("pg13").set("ylabel", "\u9ea6\u514b\u98ce (dB SPL)");
    model.result("pg13").set("xlog", true);
    model.result("pg13").set("legendpos", "upperleft");
    model.result("pg13").create("glob1", "Global");
    model.result("pg13").feature("glob1").set("markerpos", "datapoints");
    model.result("pg13").feature("glob1").set("linewidth", "preference");
    model.result("pg13").feature("glob1")
         .setIndex("expr", "20*log10(abs(pWGon_real(freq)+i*pWGon_imag(freq))/20e-6)", 0);
    model.result("pg13").feature("glob1").setIndex("descr", "\u6d4b\u91cf", 0);
    model.result("pg13").feature("glob1").setIndex("expr", "20*log10(abs(Vrec/(T711+T712/Zmic))/20e-6)", 1);
    model.result("pg13").feature("glob1").setIndex("descr", "\u4f20\u9012\u77e9\u9635\uff08\u6a21\u578b\uff09", 1);
    model.result("pg13").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1")
         .label("\u8ba1\u7b97\u7ec4\uff1a\u8035\u804d\u6321\u677f\uff0cT \u77e9\u9635 (real/imag)");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").set("data", "dset1");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "real(Twg11)", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "imag(Twg11)", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "real(Twg12)", 2);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "imag(Twg12)", 3);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "real(Twg21)", 4);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "imag(Twg21)", 5);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "real(Twg22)", 6);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "imag(Twg22)", 7);
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").run();
    model.result("pg14").label("\u7f29\u7565\u56fe");
    model.result("pg14").set("titletype", "none");
    model.result("pg14").set("edges", false);
    model.result("pg14").create("slc1", "Slice");
    model.result("pg14").feature("slc1").set("expr", "ta.v_inst");
    model.result("pg14").feature("slc1").set("quickxnumber", 1);
    model.result("pg14").run();
    model.result("pg14").create("surf1", "Surface");
    model.result("pg14").feature("surf1").set("expr", "1");
    model.result("pg14").feature("surf1").set("coloring", "uniform");
    model.result("pg14").feature("surf1").set("color", "gray");
    model.result("pg14").feature("surf1").create("sel1", "Selection");
    model.result("pg14").feature("surf1").feature("sel1").selection()
         .set(7, 8, 9, 10, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50);
    model.result("pg14").run();
    model.result("pg14").create("line1", "Line");
    model.result("pg14").feature("line1").set("expr", "1");
    model.result("pg14").feature("line1").set("coloring", "uniform");
    model.result("pg14").feature("line1").set("color", "black");
    model.result("pg14").feature("line1").create("sel1", "Selection");
    model.result("pg14").feature("line1").feature("sel1").selection()
         .set(7, 8, 10, 11, 13, 14, 17, 19, 31, 32, 34, 36, 42, 43, 89, 90, 91, 92, 94, 96, 103, 104, 137, 138, 139, 140);
    model.result("pg14").run();
    model.result("pg14").setIndex("looplevel", 11, 0);

    model.component("comp1").view("view1").set("ssao", true);

    model.result("pg14").run();

    model.title("\u8035\u804d\u6321\u677f\u58f0\u5b66\uff1a\u8f6c\u79fb\u77e9\u9635\u8ba1\u7b97");

    model
         .description("\u5728\u672c\u6559\u7a0b\u4e2d\uff0c\u6211\u4eec\u5c06\u5206\u6790\u8035\u804d\u6321\u677f\u7684\u58f0\u5b66\u5c5e\u6027\u3002\u8035\u804d\u6321\u677f\u662f\u4e00\u79cd\u5c0f\u578b\u7a7f\u5b54\u7f51\uff0c\u53ef\u7528\u4e8e\u4fdd\u62a4\u8033\u5185\u5f0f\u63a5\u6536\u5668 (RITE) \u6216\u8033\u9053\u5f0f\u63a5\u6536\u5668 (RIC) \u52a9\u542c\u5668\u6240\u7528\u7684\u63a5\u6536\u5668\uff08\u52a9\u542c\u5668\u4e2d\u7684\u5fae\u578b\u626c\u58f0\u5668\uff09\u3002\u7531\u4e8e\u8be5\u7ed3\u6784\u7684\u5c3a\u5bf8\u975e\u5e38\u5c0f\uff0c\u9700\u8981\u5728\u6a21\u578b\u4e2d\u5305\u542b\u70ed\u8fb9\u754c\u5c42\u548c\u9ecf\u6027\u8fb9\u754c\u5c42\u635f\u8017\u7ec6\u8282\uff0c\u56e0\u6b64\u9700\u8981\u4f7f\u7528\u201c\u70ed\u9ecf\u6027\u58f0\u5b66\uff0c\u9891\u57df\u201d\u63a5\u53e3\u3002\n\n\u7b2c\u4e00\u6b65\uff0c\u6211\u4eec\u4f7f\u7528\u201c\u7aef\u53e3\u626b\u63cf\u201d\u529f\u80fd\u548c\u201c\u7aef\u53e3\u201d\u8fb9\u754c\u6761\u4ef6\u8ba1\u7b97\u8035\u804d\u6321\u677f\u7684\u8f6c\u79fb\u77e9\u9635\uff08\u6216\u4e8c\u7aef\u53e3\uff09\u3002\u4ece CAD \u6587\u4ef6\u5bfc\u5165\u8035\u804d\u6321\u677f\u51e0\u4f55\u7ed3\u6784\uff0c\u4e3a\u4eff\u771f\u505a\u597d\u51c6\u5907\u3002\n\n\u7b2c\u4e8c\u6b65\uff0c\u8ba1\u7b97\u8035\u804d\u6321\u677f\u5b50\u7cfb\u7edf\u88ab\u653e\u7f6e\u5728\u5178\u578b\u6d4b\u91cf\u88c5\u7f6e\u4e2d\u65f6\u7684\u54cd\u5e94\uff0c\u5e76\u5c06\u8ba1\u7b97\u7ed3\u679c\u4e0e\u5b9e\u9645\u6d4b\u91cf\u7ed3\u679c\u8fdb\u884c\u6bd4\u8f83\u3002\u6b64\u64cd\u4f5c\u901a\u8fc7\u96c6\u603b\u8f6c\u79fb\u77e9\u9635\u65b9\u6cd5\u5b8c\u6210\u3002\u6700\u540e\uff0c\u5c06\u8ba1\u7b97\u51fa\u7684\u8035\u804d\u6321\u677f\u8f6c\u79fb\u77e9\u9635\u4e0e\u63a5\u6536\u5668\uff08\u5fae\u578b\u626c\u58f0\u5668\uff09\u3001\u7a84\u7ba1\u548c\u8026\u5408\u5668\u4f53\u79ef\u7684\u5176\u4ed6\u8f6c\u79fb\u77e9\u9635\u5206\u91cf\u4e00\u8d77\u4f7f\u7528\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("wax_guard_acoustics.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
