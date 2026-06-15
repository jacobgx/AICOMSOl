/*
 * horn_shape_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class horn_shape_optimization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);

    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", 0.025);
    model.component("comp1").geom("geom1").feature("sq1").set("pos", new double[]{0, -0.2});
    model.component("comp1").geom("geom1").run("sq1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 0.2);
    model.component("comp1").geom("geom1").feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "0 0.1 0.1 0.025 0.025 0");
    model.component("comp1").geom("geom1").feature("pol1").set("y", "0 0 0 -0.175 -0.175 -0.175");

    model.param().set("f0", "5[kHz]");
    model.param().descr("f0", "\u4f18\u5316\u9891\u7387");
    model.param().set("df", "50[Hz]");
    model.param().descr("df", "\u4f18\u5316\u9891\u7387\u5e26\u5bbd");
    model.param().set("Lfar", "1[m]");
    model.param().descr("Lfar", "\u8fdc\u573a\u8ba1\u7b97\u8ddd\u79bb");

    model.component("comp1").common().create("fsd1", "FreeShapeDomain");
    model.component("comp1").common("fsd1").selection().all();

    model.component("comp1").geom("geom1").run();

    model.component("comp1").common("fsd1").selection().set(2);
    model.component("comp1").common().create("poly1", "PolynomialBoundary");
    model.component("comp1").common("poly1").selection().set(8);
    model.component("comp1").common("poly1").set("maximumDisplacementType", "Box");
    model.component("comp1").common("poly1").set("maximumDisplacement", "0.03");
    model.component("comp1").common("poly1").set("order", "8");

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

    model.component("comp1").physics("acpr").create("port1", "Port", 1);
    model.component("comp1").physics("acpr").feature("port1").selection().set(2);
    model.component("comp1").physics("acpr").feature("port1").set("PortType", "Circular");
    model.component("comp1").physics("acpr").feature("port1").set("pamp", 1);
    model.component("comp1").physics("acpr").create("efc1", "ExteriorFieldCalculation", 1);
    model.component("comp1").physics("acpr").feature("efc1").selection().set(6);
    model.component("comp1").physics("acpr").feature("efc1").setIndex("SymmetryCondition2", 1, 0);
    model.component("comp1").physics("acpr").create("pmb1", "PerfectlyMatchedBoundary", 1);
    model.component("comp1").physics("acpr").feature("pmb1").selection().set(10);
    model.component("comp1").physics("acpr").prop("MeshControl").set("SizeControlParameter", "Frequency");
    model.component("comp1").physics("acpr").prop("MeshControl").set("PhysicsControlledMeshMaximumFrequency", "f0");
    model.component("comp1").physics("acpr").prop("MeshControl").set("ElementsPerWavelength", "UserDefined");
    model.component("comp1").physics("acpr").prop("MeshControl").set("nperlambda", 10);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u53c2\u8003\u89e3");
    model.study("std1").feature("freq").set("plist", "range(3500,50,6500)");
    model.study("std1").feature("freq").setSolveFor("/frame/material1", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 61, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 61, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", false);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "rev1");
    model.result("pg3").setIndex("looplevel", 61, 0);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg3").feature("surf1").set("colortable", "Wave");
    model.result("pg3").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b, 3D (acpr)");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "rev1");
    model.result("pg4").setIndex("looplevel", 61, 0);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").label("\u58f0\u538b\u7ea7, 3D (acpr)");
    model.result().create("pg5", "PolarGroup");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").create("rp1", "RadiationPattern");
    model.result("pg5").feature("rp1").set("expr", new String[]{"acpr.efc1.Lp_pext"});
    model.result("pg5").feature("rp1").set("legend", true);
    model.result("pg5").feature("rp1").set("phidisc", 180);
    model.result("pg5").label("\u5916\u573a\u58f0\u538b\u7ea7 (acpr)");
    model.result("pg5").setIndex("looplevelinput", "last", 0);
    model.result().create("pg6", "PolarGroup");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").create("rp1", "RadiationPattern");
    model.result("pg6").feature("rp1").set("expr", new String[]{"acpr.efc1.pext"});
    model.result("pg6").feature("rp1").set("legend", true);
    model.result("pg6").feature("rp1").set("phidisc", 180);
    model.result("pg6").label("\u5916\u573a\u538b\u529b (acpr)");
    model.result("pg6").setIndex("looplevelinput", "last", 0);
    model.result("pg5").set("symmetricangle", true);
    model.result("pg5").set("zeroangle", "up");
    model.result("pg5").set("rotdir", "cw");
    model.result("pg6").set("symmetricangle", true);
    model.result("pg6").set("zeroangle", "up");
    model.result("pg6").set("rotdir", "cw");
    model.result().create("pg7", "PlotGroup2D");
    model.result().dataset().duplicate("dset1g1", "dset1");
    model.result().dataset("dset1g1").label("\u7814\u7a76 1 - \u53c2\u8003\u89e3/\u89e3 1 (1) - \u51e0\u4f55");
    model.result().dataset("dset1g1").set("frametype", "geometry");
    model.result("pg7").label("\u5f62\u72b6\u4f18\u5316");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").set("frametype", "geometry");
    model.result("pg7").set("edgecolor", "gray");
    model.result("pg7").set("titletype", "none");
    model.result("pg7").create("line1", "Line");
    model.result("pg7").feature("line1").set("expr", "1");
    model.result("pg7").feature("line1").set("coloring", "uniform");
    model.result("pg7").feature("line1").set("color", "fromtheme");
    model.result("pg7").create("arwl1", "ArrowLine");
    model.result("pg7").feature("arwl1").set("data", "dset1g1");
    model.result("pg7").feature("arwl1").set("expr", new String[]{"fsd1.dRg", "fsd1.dZg"});
    model.result("pg7").feature("arwl1").set("scaleactive", true);
    model.result("pg7").feature("arwl1").set("scale", "1");
    model.result("pg7").feature("arwl1").set("placement", "uniform");
    model.result("pg7").feature("arwl1").set("arrowcount", 200);
    model.result("pg7").feature("arwl1").create("col1", "Color");
    model.result("pg7").feature("arwl1").feature("col1").set("expr", "fsd1.rel_disp");
    model.result("pg7").feature("arwl1").feature("col1").set("rangecoloractive", "on");
    model.result("pg7").feature("arwl1").feature("col1").set("rangecolormin", 0);
    model.result("pg7").feature("arwl1").feature("col1").set("rangecolormax", 1);
    model.result("pg7").feature("arwl1").create("sel1", "Selection");
    model.result("pg7").feature("arwl1").feature("sel1").selection().named("dsel_fsd1");
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("rp1").set("radius", "Lfar");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("rp1").set("radius", "Lfar");
    model.result("pg7").run();
    model.result().remove("pg7");
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").add("plotgroup", "pg5");
    model.nodeGroup("grp1").add("plotgroup", "pg6");
    model.nodeGroup("grp1").label("\u53c2\u8003\u89e3");

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std2").feature("freq").set("plist", "f0-df/2 f0 f0+df/2");
    model.study("std2").label("\u7814\u7a76 2 - \u4f18\u5316\u89e3");
    model.study("std2").create("sho", "ShapeOptimization");
    model.study("std2").feature("sho").set("mmamaxiter", 50);
    model.study("std2").feature("sho").set("movelimitactive", false);
    model.study("std2").feature("sho").setIndex("optobj", "comp1.Lp_pext_opt(0, Lfar)", 0);
    model.study("std2").feature("sho").set("objectivetype", "maximization");
    model.study("std2").feature("sho").set("objectivesolution", "min");
    model.study("std2").feature("sho").set("objectivescaling", "init");
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol("sol2").runFromTo("st1", "v1");

    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg7").feature("surf1").set("colortable", "Wave");
    model.result("pg7").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").label("\u58f0\u538b (acpr) 1");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result("pg8").feature("surf1").set("colorscalemode", "linear");
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").label("\u58f0\u538b\u7ea7 (acpr) 1");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("data", "dset2");
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("hasspacevars", false);
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").set("data", "rev2");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg9").feature("surf1").set("colortable", "Wave");
    model.result("pg9").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg9").set("showlegendsunit", true);
    model.result("pg9").label("\u58f0\u538b, 3D (acpr) 1");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").set("data", "rev2");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg10").feature("surf1").set("colortable", "Rainbow");
    model.result("pg10").feature("surf1").set("colorscalemode", "linear");
    model.result("pg10").set("showlegendsunit", true);
    model.result("pg10").label("\u58f0\u538b\u7ea7, 3D (acpr) 1");
    model.result().create("pg11", "PolarGroup");
    model.result("pg11").set("data", "dset2");
    model.result("pg11").create("rp1", "RadiationPattern");
    model.result("pg11").feature("rp1").set("expr", new String[]{"acpr.efc1.Lp_pext"});
    model.result("pg11").feature("rp1").set("legend", true);
    model.result("pg11").feature("rp1").set("phidisc", 180);
    model.result("pg11").label("\u5916\u573a\u58f0\u538b\u7ea7 (acpr) 1");
    model.result().create("pg12", "PolarGroup");
    model.result("pg12").set("data", "dset2");
    model.result("pg12").create("rp1", "RadiationPattern");
    model.result("pg12").feature("rp1").set("expr", new String[]{"acpr.efc1.pext"});
    model.result("pg12").feature("rp1").set("legend", true);
    model.result("pg12").feature("rp1").set("phidisc", 180);
    model.result("pg12").label("\u5916\u573a\u538b\u529b (acpr) 1");
    model.result("pg11").set("symmetricangle", true);
    model.result("pg11").set("zeroangle", "up");
    model.result("pg11").set("rotdir", "cw");
    model.result("pg12").set("symmetricangle", true);
    model.result("pg12").set("zeroangle", "up");
    model.result("pg12").set("rotdir", "cw");
    model.result().create("pg13", "PlotGroup2D");
    model.result().dataset().duplicate("dset2g1", "dset2");
    model.result().dataset("dset2g1").label("\u7814\u7a76 2 - \u4f18\u5316\u89e3/\u89e3 2 (2) - \u51e0\u4f55");
    model.result().dataset("dset2g1").set("frametype", "geometry");
    model.result("pg13").label("\u5f62\u72b6\u4f18\u5316");
    model.result("pg13").set("data", "dset2");
    model.result("pg13").set("frametype", "geometry");
    model.result("pg13").set("edgecolor", "gray");
    model.result("pg13").set("titletype", "none");
    model.result("pg13").create("line1", "Line");
    model.result("pg13").feature("line1").set("expr", "1");
    model.result("pg13").feature("line1").set("coloring", "uniform");
    model.result("pg13").feature("line1").set("color", "fromtheme");
    model.result("pg13").create("arwl1", "ArrowLine");
    model.result("pg13").feature("arwl1").set("data", "dset2g1");
    model.result("pg13").feature("arwl1").set("expr", new String[]{"fsd1.dRg", "fsd1.dZg"});
    model.result("pg13").feature("arwl1").set("scaleactive", true);
    model.result("pg13").feature("arwl1").set("scale", "1");
    model.result("pg13").feature("arwl1").set("placement", "uniform");
    model.result("pg13").feature("arwl1").set("arrowcount", 200);
    model.result("pg13").feature("arwl1").create("col1", "Color");
    model.result("pg13").feature("arwl1").feature("col1").set("expr", "fsd1.rel_disp");
    model.result("pg13").feature("arwl1").feature("col1").set("rangecoloractive", "on");
    model.result("pg13").feature("arwl1").feature("col1").set("rangecolormin", 0);
    model.result("pg13").feature("arwl1").feature("col1").set("rangecolormax", 1);
    model.result("pg13").feature("arwl1").create("sel1", "Selection");
    model.result("pg13").feature("arwl1").feature("sel1").selection().named("dsel_fsd1");
    model.result("pg7").run();

    model.study("std2").feature("freq").set("usestol", true);
    model.study("std2").feature("freq").set("stol", "1e-6");

    model.sol("sol2").feature("o1").feature("s1").set("nonlin", true);
    model.sol("sol2").feature("o1").feature("s1").feature("fc1").set("minstep", "1e-4");

    model.study("std2").feature("sho").set("plot", true);
    model.study("std2").feature("sho").set("plotgroup", "pg13");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg7").run();

    model.study("std2").feature("sho").set("probewindow", "");

    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg11").run();
    model.result("pg11").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg11").setIndex("looplevelindices", 2, 0);
    model.result("pg11").run();
    model.result("pg11").feature("rp1").set("anglerestr", "manual");
    model.result("pg11").feature("rp1").set("phimin", -90);
    model.result("pg11").feature("rp1").set("phirange", 180);
    model.result("pg11").feature("rp1").set("radius", "Lfar");
    model.result("pg11").feature("rp1").set("legendmethod", "manual");
    model.result("pg11").feature("rp1").setIndex("legends", "\u4f18\u5316 (5 kHz)", 0);
    model.result("pg11").feature("rp1").set("linecolor", "black");
    model.result("pg11").feature().duplicate("rp2", "rp1");
    model.result("pg11").run();
    model.result("pg11").feature("rp2").set("data", "dset1");
    model.result("pg11").feature("rp2").setIndex("looplevelinput", "manual", 0);
    model.result("pg11").feature("rp2").setIndex("looplevel", new int[]{31}, 0);
    model.result("pg11").feature("rp2").setIndex("legends", "\u53c2\u8003 (5 kHz)", 0);
    model.result("pg11").feature("rp2").set("linecolor", "blue");
    model.result("pg11").run();
    model.result("pg12").run();
    model.result("pg12").run();
    model.result("pg12").feature("rp1").set("radius", "Lfar");
    model.result("pg13").run();
    model.result("pg13").run();
    model.result("pg13").feature("arwl1").set("placement", "elements");
    model.result("pg13").run();
    model.result("pg13").feature("arwl1").feature("col1").set("colortable", "Rainbow");
    model.result("pg7").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg7");
    model.nodeGroup("grp2").add("plotgroup", "pg8");
    model.nodeGroup("grp2").add("plotgroup", "pg9");
    model.nodeGroup("grp2").add("plotgroup", "pg10");
    model.nodeGroup("grp2").add("plotgroup", "pg11");
    model.nodeGroup("grp2").add("plotgroup", "pg12");
    model.nodeGroup("grp2").add("plotgroup", "pg13");
    model.nodeGroup("grp2").label("\u4f18\u5316\u89e3");

    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std3").feature("freq").set("plist", "range(3500,50,6500)");
    model.study("std3").feature("freq").set("probesel", "none");
    model.study("std3").feature("freq").setSolveFor("/frame/material1", false);
    model.study("std3").feature("freq").set("usesol", true);
    model.study("std3").feature("freq").set("notsolmethod", "sol");
    model.study("std3").feature("freq").set("notstudy", "std2");
    model.study("std3").label("\u7814\u7a76 3 - \u9891\u7387\u626b\u63cf (\u4f18\u5316)");
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg14", "PlotGroup1D");

    model.nodeGroup("grp2").add("plotgroup", "pg14");

    model.result("pg14").run();
    model.result("pg14").label("\u54cd\u5e94");
    model.result("pg14").set("data", "dset3");
    model.result("pg14").set("ylabelactive", true);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg14").set("ylabel", "\u8f74\u4e0a\u58f0\u538b\u7ea7");
    model.result("pg14").set("legendpos", "upperleft");
    model.result("pg14").create("glob1", "Global");
    model.result("pg14").feature("glob1").set("markerpos", "datapoints");
    model.result("pg14").feature("glob1").set("linewidth", "preference");
    model.result("pg14").feature("glob1").setIndex("expr", "Lp_pext_opt(0, Lfar)", 0);
    model.result("pg14").feature("glob1").set("linewidth", 2);
    model.result("pg14").feature("glob1").set("legendmethod", "manual");
    model.result("pg14").feature("glob1").setIndex("legends", "\u4f18\u5316\u8bbe\u8ba1", 0);
    model.result("pg14").feature().duplicate("glob2", "glob1");
    model.result("pg14").run();
    model.result("pg14").feature("glob2").set("data", "dset2");
    model.result("pg14").feature("glob2").set("linestyle", "none");
    model.result("pg14").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg14").feature("glob2").set("linemarker", "square");
    model.result("pg14").run();
    model.result("pg14").feature().duplicate("glob3", "glob1");
    model.result("pg14").run();
    model.result("pg14").feature("glob3").set("data", "dset1");
    model.result("pg14").feature("glob3").setIndex("legends", "\u521d\u59cb\u8bbe\u8ba1", 0);
    model.result("pg14").run();
    model.result("pg10").run();

    model.title("\u5587\u53ed\u5f62\u72b6\u4f18\u5316");

    model
         .description("\u5728\u672c\u4f8b\u4e2d\uff0c\u4e00\u4e2a\u5e73\u9762\u6ce2\u6a21\u5f0f\u9988\u5165\u4e00\u4e2a\u4ece\u65e0\u9650\u6321\u677f\u5411\u5f00\u653e\u534a\u7a7a\u95f4\u8f90\u5c04\u7684\u8f74\u5bf9\u79f0\u5587\u53ed\u3002\u5176\u4e2d\u5047\u8bbe\u9988\u6e90\u6ce2\u5bfc\u7684\u534a\u5f84\u662f\u56fa\u5b9a\u7684\uff0c\u5587\u53ed\u7684\u6df1\u5ea6\u53ca\u5176\u8fde\u63a5\u5230\u6321\u677f\u7684\u5b54\u7684\u5927\u5c0f\u4e5f\u662f\u56fa\u5b9a\u7684\u3002\u901a\u8fc7\u8c03\u6574\u5587\u53ed\u521d\u59cb\u9525\u5f62\u8868\u9762\u7684\u66f2\u7387\uff0c\u53ef\u4ee5\u6539\u53d8\u5176\u65b9\u5411\u6027\u548c\u963b\u6297\u3002\n\n\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u4e3a\u8f74\u5bf9\u79f0\u5587\u53ed\u5e94\u7528\u8fb9\u754c\u5f62\u72b6\u4f18\u5316\u3002\u4e3a\u4e86\u7b80\u5316\u95ee\u9898\uff0c\u5176\u4e2d\u5c06\u7a84\u9891\u5e26\u548c\u5355\u4e00\u65b9\u5411\u7684\u8fdc\u573a\u58f0\u538b\u7ea7\u6700\u5927\u5316\u3002\u672c\u4f8b\u91cd\u70b9\u9610\u8ff0\u4f18\u5316\u8fc7\u7a0b\uff0c\u5176\u4e2d\u6d89\u53ca\u76ee\u6807\u51fd\u6570\u7684\u9009\u62e9\u548c\u4f18\u5316\u6c42\u89e3\u5668\u8bbe\u7f6e\u3002\n\n\u5206\u6790\u7ed3\u679c\u8868\u660e\uff0c\u901a\u8fc7\u5728\u6240\u9009\u53c2\u6570\u5316\u9650\u5236\u5185\u6539\u53d8\u5587\u53ed\u7684\u5f62\u72b6\uff0c\u53ef\u4ee5\u4f7f\u8f74\u4e0a\u58f0\u538b\u7ea7\u6bd4\u7b80\u5355\u7684\u9525\u5f62\u7ed3\u6784\u63d0\u9ad8\u7ea6 1\u00a0dB\u3002\u7136\u800c\uff0c\u9488\u5bf9\u8f74\u4e0a\u58f0\u538b\u7ea7\u7684\u6700\u4f18\u5f62\u72b6\u5374\u53ef\u80fd\u5bfc\u81f4\u5176\u4ed6\u65b9\u5411\u51fa\u73b0\u4e00\u4e9b\u4e0d\u5e0c\u671b\u7684\u6700\u5c0f\u503c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("horn_shape_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
