/*
 * gaussian_beam_propagation_optical_prism.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:41 by COMSOL 6.3.0.290. */
public class gaussian_beam_propagation_optical_prism {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Optical_Scattering");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

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

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("lda0", "1[um]", "\u81ea\u7531\u7a7a\u95f4\u4e2d\u7684\u6ce2\u957f");
    model.param().set("w0", "3*lda0", "\u9ad8\u65af\u5149\u675f\u534a\u5f84");
    model.param().set("anglePrism", "30[deg]", "\u68f1\u955c\u89d2\u5ea6");
    model.param().set("H", "6*sqrt(2)*w0", "\u51e0\u4f55\u9ad8\u5ea6");
    model.param().set("W", "H+H*tan(anglePrism)", "\u51e0\u4f55\u5bbd\u5ea6");
    model.param().set("n0", "1", "\u6298\u5c04\u7387\uff0c\u80cc\u666f\u7a7a\u6c14");
    model.param().set("np", "1.5", "\u6298\u5c04\u7387\uff0c\u68f1\u955c");
    model.param()
         .set("alphaInc", "anglePrism", "\u4ece\u7a7a\u6c14\u5165\u5c04\u5230\u68f1\u955c\u7684\u89d2\u5ea6");
    model.param()
         .set("alphaTrPrism", "asin((n0/np)*sin(alphaInc))", "\u4ece\u7a7a\u6c14\u900f\u5c04\u5230\u68f1\u955c\u7684\u89d2\u5ea6");
    model.param()
         .set("alphaIncPrism", "anglePrism-alphaTrPrism", "\u4ece\u68f1\u955c\u5165\u5c04\u5230\u7a7a\u6c14\u7684\u89d2\u5ea6");
    model.param()
         .set("alphaTr", "asin((np/n0)*sin(alphaIncPrism))", "\u4ece\u68f1\u955c\u900f\u5c04\u5230\u7a7a\u6c14\u7684\u89d2\u5ea6");
    model.param().set("kxScaDir", "cos(-alphaTr)", "\u4f20\u8f93\u6563\u5c04\u6ce2\u65b9\u5411\uff0cx \u5206\u91cf");
    model.param().set("kyScaDir", "sin(-alphaTr)", "\u4f20\u8f93\u6563\u5c04\u6ce2\u65b9\u5411\uff0cy \u5206\u91cf");
    model.param()
         .set("dOffset", "5[um]", "\u68f1\u955c\u79fb\u52a8\u5230\u51e0\u4f55\u4e2d\u5fc3\u9644\u8fd1\u7684\u504f\u79fb\u8ddd\u79bb");
    model.param().create("par2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2")
         .set("n1_s", "sqrt((n0*sin(alphaInc))^2+n0*np*cos(alphaInc)*cos(alphaTrPrism))", "\u6297\u53cd\u5c04\u6d82\u5c42 1 \u6298\u5c04\u7387\uff0cs \u504f\u632f");
    model.param("par2")
         .set("n2_s", "sqrt((np*sin(alphaIncPrism))^2+np*n0*cos(alphaIncPrism)*cos(alphaTr))", "\u6297\u53cd\u5c04\u6d82\u5c42 2 \u6298\u5c04\u7387\uff0cs \u504f\u632f");
    model.param("par2")
         .set("theta1_s", "asin((1/n1_s)*sin(alphaInc))", "\u6297\u53cd\u5c04\u6d82\u5c42\u4e2d\u7684\u900f\u5c04\u89d2 1\uff0cs \u504f\u632f\u6ce2");
    model.param("par2")
         .set("theta2_s", "asin((n2_s/1)*sin(alphaIncPrism))", "\u6297\u53cd\u5c04\u6d82\u5c42\u4e2d\u7684\u900f\u5c04\u89d2 2\uff0cs \u504f\u632f\u6ce2");
    model.param("par2")
         .set("d1_s", "lda0/(4*n1_s*cos(theta1_s))", "\u6297\u53cd\u5c04\u6d82\u5c421 \u539a\u5ea6\uff0cs \u504f\u632f");
    model.param("par2")
         .set("d2_s", "lda0/(4*n2_s*cos(theta2_s))", "\u6297\u53cd\u5c04\u6d82\u5c42 2 \u539a\u5ea6\uff0cs \u504f\u632f");
    model.param("par2")
         .set("n1_p", "sqrt(n0*np/(2*cos(alphaInc)*cos(alphaTrPrism)))*sqrt(1+sqrt(1-4*n0*cos(alphaInc)*cos(alphaTrPrism)*sin(alphaInc)*sin(alphaInc)/np))", "\u6297\u53cd\u5c04\u6d82\u5c42 1 \u6298\u5c04\u7387\uff0cp \u504f\u632f");
    model.param("par2")
         .set("n2_p", "sqrt(np*n0/(2*cos(alphaIncPrism)*cos(alphaTr)))*sqrt(1+sqrt(1-4*np*cos(alphaIncPrism)*cos(alphaTr)*sin(alphaIncPrism)*sin(alphaIncPrism)/n0))", "\u6297\u53cd\u5c04\u6d82\u5c42 2 \u6298\u5c04\u7387\uff0cp \u504f\u632f");
    model.param("par2")
         .set("theta1_p", "asin((1/n1_p)*sin(alphaInc))", "\u6297\u53cd\u5c04\u6d82\u5c42\u4e2d\u7684\u900f\u5c04\u89d2 1\uff0cp \u504f\u632f\u6ce2");
    model.param("par2")
         .set("theta2_p", "asin((n2_p/1)*sin(alphaIncPrism))", "\u6297\u53cd\u5c04\u6d82\u5c42\u4e2d\u7684\u900f\u5c04\u89d2 2\uff0cp \u504f\u632f\u6ce2");
    model.param("par2")
         .set("d1_p", "lda0/(4*n1_p*cos(theta1_p))", "\u6297\u53cd\u5c04\u6d82\u5c42 1 \u539a\u5ea6\uff0cp \u504f\u632f");
    model.param("par2")
         .set("d2_p", "lda0/(4*n2_p*cos(theta2_p))", "\u6297\u53cd\u5c04\u6d82\u5c42 2 \u539a\u5ea6\uff0cp \u504f\u632f");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W", "H"});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-H*tan(anglePrism)+dOffset", 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-H/2", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "5[um]", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "H/2", 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "5[um]", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-H/2", 2, 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

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
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"np"});

    model.component("comp1").physics("ewfd").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewfd").create("mbc1", "MatchedBoundaryCondition", 1);
    model.component("comp1").physics("ewfd").feature("mbc1").selection().set(1, 2);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").label("\u663e\u5f0f 1");
    model.component("comp1").selection("sel1").set(1, 2);

    model.component("comp1").physics("ewfd").feature("mbc1").selection().named("sel1");
    model.component("comp1").physics("ewfd").feature("mbc1").set("IncidentField", "GaussianBeam");
    model.component("comp1").physics("ewfd").feature("mbc1").set("w0", "w0");
    model.component("comp1").physics("ewfd").feature("mbc1").set("p0", "W/2");
    model.component("comp1").physics("ewfd").feature("mbc1").set("Eg0", new int[]{0, 0, 1});
    model.component("comp1").physics("ewfd").feature("mbc1").set("kdir", new int[]{1, 0, 0});
    model.component("comp1").physics("ewfd").feature("mbc1").create("rpnt1", "ReferencePoint", 0);
    model.component("comp1").physics("ewfd").feature("mbc1").feature("rpnt1").set("refPosDefinition", "UserDefined");
    model.component("comp1").physics("ewfd").feature("mbc1").feature("rpnt1")
         .set("refPos", new String[]{"-W/2", "0", "0"});
    model.component("comp1").physics("ewfd").create("mbc2", "MatchedBoundaryCondition", 1);
    model.component("comp1").physics("ewfd").feature("mbc2").selection().set(7, 9);

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").label("\u663e\u5f0f 2");
    model.component("comp1").selection("sel2").set(7, 9);

    model.component("comp1").physics("ewfd").feature("mbc2").selection().named("sel2");
    model.component("comp1").physics("ewfd").feature("mbc2")
         .set("kScaDir", new String[]{"kxScaDir", "kyScaDir", "0"});
    model.component("comp1").physics("ewfd").create("mbc3", "MatchedBoundaryCondition", 1);
    model.component("comp1").physics("ewfd").feature("mbc3").selection().set(3);

    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(1);
    model.component("comp1").selection("sel3").label("\u663e\u5f0f 3");
    model.component("comp1").selection("sel3").set(3);

    model.component("comp1").physics("ewfd").feature("mbc3").selection().named("sel3");
    model.component("comp1").physics("ewfd").feature("mbc3")
         .set("kScaDir", new String[]{"-cos(2*alphaInc)", "sin(2*alphaInc)", "0"});
    model.component("comp1").physics("ewfd").create("mbc4", "MatchedBoundaryCondition", 1);
    model.component("comp1").physics("ewfd").feature("mbc4").selection().set(4);
    model.component("comp1").physics("ewfd").feature("mbc4")
         .set("kScaDir", new String[]{"cos(alphaIncPrism)", "-sin(alphaIncPrism)", "0"});
    model.component("comp1").physics("ewfd").create("trans1", "TransitionBoundaryCondition", 1);
    model.component("comp1").physics("ewfd").feature("trans1").selection().set(5);
    model.component("comp1").physics("ewfd").feature("trans1").set("n_mat", "userdef");
    model.component("comp1").physics("ewfd").feature("trans1").set("n", "n1_s");
    model.component("comp1").physics("ewfd").feature("trans1").set("ki_mat", "userdef");
    model.component("comp1").physics("ewfd").feature("trans1").set("d", "d1_s");
    model.component("comp1").physics("ewfd").create("trans2", "TransitionBoundaryCondition", 1);
    model.component("comp1").physics("ewfd").feature("trans2").selection().set(6);
    model.component("comp1").physics("ewfd").feature("trans2").set("n_mat", "userdef");
    model.component("comp1").physics("ewfd").feature("trans2").set("n", "n2_s");
    model.component("comp1").physics("ewfd").feature("trans2").set("ki_mat", "userdef");
    model.component("comp1").physics("ewfd").feature("trans2").set("d", "d2_s");
    model.component("comp1").physics().copy("ewfd2", "ewfd");
    model.component("comp1").physics("ewfd2").prop("components").set("components", "inplane");
    model.component("comp1").physics("ewfd2").feature("mbc1").set("Eg0", new int[]{0, 1, 0});
    model.component("comp1").physics("ewfd2").feature("trans1").set("n", "n1_p");
    model.component("comp1").physics("ewfd2").feature("trans1").set("d", "d1_p");
    model.component("comp1").physics("ewfd2").feature("trans2").set("n", "n2_p");
    model.component("comp1").physics("ewfd2").feature("trans2").set("d", "d2_p");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().named("sel1");
    model.component("comp1").cpl().duplicate("intop2", "intop1");
    model.component("comp1").cpl("intop2").selection().named("sel3");
    model.component("comp1").cpl().duplicate("intop3", "intop2");
    model.component("comp1").cpl("intop3").selection().named("sel2");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("Pin_s", "-intop1(ewfd.nPoav)", "\u8f93\u5165\u529f\u7387\uff0cs \u504f\u632f\u6ce2");
    model.component("comp1").variable("var1")
         .set("Pref_s", "intop2(ewfd.nPoav)", "\u68f1\u955c\u53cd\u5c04\u7684\u529f\u7387\uff0cs \u504f\u632f\u6ce2");
    model.component("comp1").variable("var1")
         .set("Ptran_s", "intop3(ewfd.nPoav)", "\u68f1\u955c\u900f\u5c04\u7684\u529f\u7387\uff0cs \u504f\u632f\u6ce2");
    model.component("comp1").variable("var1")
         .set("R_s", "Pref_s/Pin_s", "\u53cd\u5c04\u7387\uff0cs \u504f\u632f\u6ce2");
    model.component("comp1").variable("var1")
         .set("T_s", "Ptran_s/Pin_s", "\u900f\u5c04\u7387\uff0cs \u504f\u632f\u6ce2");
    model.component("comp1").variable("var1")
         .set("Pin_p", "-intop1(ewfd2.nPoav)", "\u8f93\u5165\u529f\u7387\uff0cp \u504f\u632f\u6ce2");
    model.component("comp1").variable("var1")
         .set("Pref_p", "intop2(ewfd2.nPoav)", "\u68f1\u955c\u53cd\u5c04\u7684\u529f\u7387\uff0cp \u504f\u632f\u6ce2");
    model.component("comp1").variable("var1")
         .set("Ptran_p", "intop3(ewfd2.nPoav)", "\u68f1\u955c\u900f\u5c04\u7684\u529f\u7387\uff0cp \u504f\u632f\u6ce2");
    model.component("comp1").variable("var1")
         .set("R_p", "Pref_p/Pin_p", "\u53cd\u5c04\u7387\uff0cp \u504f\u632f\u6ce2");
    model.component("comp1").variable("var1")
         .set("T_p", "Ptran_p/Pin_p", "\u900f\u5c04\u7387\uff0cp \u504f\u632f\u6ce2");

    model.study("std1").feature("wave").setSolveFor("/physics/ewfd2", false);
    model.study("std1").feature("wave").set("plist", "lda0");
    model.study("std1").feature().duplicate("wave1", "wave");
    model.study("std1").feature("wave1").setSolveFor("/physics/ewfd", false);
    model.study("std1").feature("wave1").setSolveFor("/physics/ewfd2", true);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u573a (ewfd2)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "ewfd2.normE");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "R_s", 0);
    model.result().numerical("gev1").setIndex("expr", "T_s", 1);
    model.result().numerical("gev1").setIndex("expr", "R_p", 2);
    model.result().numerical("gev1").setIndex("expr", "T_p", 3);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "ewfd.Ez");
    model.result("pg3").feature("surf1").set("colortable", "ThermalWaveDark");
    model.result("pg3").run();
    model.result("pg3").set("edgecolor", "white");
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "ewfd2.Ey");

    model.title("\u9ad8\u65af\u5149\u675f\u901a\u8fc7\u5149\u5b66\u68f1\u955c\u7684\u4f20\u64ad");

    model
         .description("\u672c\u6a21\u578b\u4f7f\u7528 s \u504f\u632f\u548c p \u504f\u632f\u7684\u5165\u5c04\u9ad8\u65af\u5149\u675f\uff0c\u6f14\u793a\u5149\u5728\u901a\u8fc7\u5149\u5b66\u68f1\u955c\u65f6\u7684\u6298\u5c04\u73b0\u8c61\u3002\u68f1\u955c\u8fb9\u754c\u6d82\u6709\u6297\u53cd\u5c04\u6d82\u5c42\u3002\u540c\u65f6\uff0c\u6a21\u578b\u8fd8\u8ba1\u7b97\u4e86\u53cd\u5c04\u7387\u548c\u900f\u5c04\u7387\uff0c\u4ee5\u5206\u6790\u6297\u53cd\u5c04\u6d82\u5c42\u7684\u6027\u80fd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("gaussian_beam_propagation_optical_prism.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
