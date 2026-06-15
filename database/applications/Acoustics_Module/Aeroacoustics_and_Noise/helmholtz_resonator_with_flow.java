/*
 * helmholtz_resonator_with_flow.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:24 by COMSOL 6.3.0.290. */
public class helmholtz_resonator_with_flow {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Aeroacoustics_and_Noise");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "TurbulentFlowSST", "geom1");

    model.study().create("std1");
    model.study("std1").create("wdi", "WallDistanceInitialization");
    model.study("std1").feature("wdi").set("solnum", "auto");
    model.study("std1").feature("wdi").set("notsolnum", "auto");
    model.study("std1").feature("wdi").set("outputmap", new String[]{});
    model.study("std1").feature("wdi").set("ngenAUX", "1");
    model.study("std1").feature("wdi").set("goalngenAUX", "1");
    model.study("std1").feature("wdi").set("ngenAUX", "1");
    model.study("std1").feature("wdi").set("goalngenAUX", "1");
    model.study("std1").feature("wdi").setSolveFor("/physics/spf", true);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Ma", "0.0", "\u6d41\u52a8\u9a6c\u8d6b\u6570");
    model.param().set("c0", "343[m/s]", "\u58f0\u901f");
    model.param().set("Uin", "Ma*c0", "\u6d41\u5165\u901f\u5ea6");
    model.param().set("Lin", "60[cm]", "\u5165\u53e3\u7ba1\u9053\u957f\u5ea6");
    model.param().set("Lout", "50[cm]", "\u51fa\u53e3\u7ba1\u9053\u957f\u5ea6");
    model.param().set("Lpml", "10[cm]", "PML \u57df\u957f\u5ea6");
    model.param().set("Dmain", "4.859[cm]", "\u4e3b\u7ba1\u9053\u76f4\u5f84");
    model.param().set("Dneck", "4.044[cm]", "\u9888\u90e8\u76f4\u5f84");
    model.param().set("Lneck", "32.47[cm]-24.42[cm]", "\u9888\u90e8\u957f\u5ea6");
    model.param().set("Dreson", "15.32[cm]", "\u5171\u632f\u5668\u4e3b\u4f53\u76f4\u5f84");
    model.param().set("Lreson", "24.42[cm]", "\u5171\u632f\u5668\u4e3b\u4f53\u957f\u5ea6");
    model.param().set("Lsource", "5[cm]", "\u6e90\u57df\u957f\u5ea6");
    model.param().set("f0", "200[Hz]", "\u6700\u5927\u9891\u7387");
    model.param()
         .set("dvisc", "0.22[mm]*sqrt(100[Hz]/f0)", "f0 \u4e0b\u7684\u9ecf\u6ede\u8fb9\u754c\u5c42\u539a\u5ea6");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "Dmain/2");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "Lin+Lout+2*Lpml");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "0", "-Lin-Lpml"});
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("layer", "Lpml", 0);
    model.component("comp1").geom("geom1").feature("cyl1").set("layerside", false);
    model.component("comp1").geom("geom1").feature("cyl1").set("layerbottom", true);
    model.component("comp1").geom("geom1").feature("cyl1").set("layertop", true);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "Dneck/2");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "1.2*Lneck");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"Dmain/2-0.2*Lneck", "0", "0"});
    model.component("comp1").geom("geom1").feature("cyl2").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", "Dreson/2");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "Lreson");
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new String[]{"Dmain/2+Lneck", "0", "0"});
    model.component("comp1").geom("geom1").feature("cyl3").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("cyl4", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl4").set("r", "Dmain/2");
    model.component("comp1").geom("geom1").feature("cyl4").set("h", "Lsource");
    model.component("comp1").geom("geom1").feature("cyl4").set("pos", new String[]{"0", "0", "-Lin"});
    model.component("comp1").geom("geom1").run("cyl4");
    model.component("comp1").geom("geom1").create("cyl5", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl5").set("r", "Dmain/2");
    model.component("comp1").geom("geom1").feature("cyl5").set("h", 0.2);
    model.component("comp1").geom("geom1").feature("cyl5").set("pos", new double[]{0, 0, -0.1});
    model.component("comp1").geom("geom1").run("cyl5");
    model.component("comp1").geom("geom1").create("pard1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard1").selection("domain").set("cyl2", 1);
    model.component("comp1").geom("geom1").feature("pard1").set("partitionwith", "extendedfaces");
    model.component("comp1").geom("geom1").feature("pard1").selection("extendedface").set("cyl1", 12, 15);
    model.component("comp1").geom("geom1").feature("pard1").set("repairtoltype", "relative");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("pard1", 1);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input")
         .set("cyl1", "cyl3", "cyl4", "cyl5", "del1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "zx");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("del2").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del2").selection("input")
         .set("par1", 2, 4, 6, 8, 10, 12, 14, 16);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(2, 5, 8, 11, 14, 17, 29, 34);
    model.component("comp1").selection("sel1").label("\u5bf9\u79f0");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2")
         .set(1, 4, 7, 10, 13, 16, 20, 21, 22, 23, 24, 25, 27, 28, 30, 31, 32, 35);
    model.component("comp1").selection("sel2").label("\u58c1");

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

    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().named("sel1");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().set(19);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(3);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "Uin");

    model.component("comp1").mesh("mesh1").label("\u7f51\u683c - CFD");
    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection().set(3, 19);
    model.component("comp1").mesh("mesh1").feature("fq1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("fq1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.005);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.001);
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.1);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.4);
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 1);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(1, 2);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 8);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().set(2);
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("swe2").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("elemcount", 50);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").create("swe3", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe3").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe3").selection().set(6);
    model.component("comp1").mesh("mesh1").feature("swe3").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe3").feature("dis1").set("numelem", 8);
    model.component("comp1").mesh("mesh1").create("swe4", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe4").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe4").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("swe4").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe4").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe4").feature("dis1").set("elemcount", 50);
    model.component("comp1").mesh("mesh1").feature("swe4").feature("dis1").set("elemratio", 4);
    model.component("comp1").mesh("mesh1").feature("swe4").feature("dis1").set("reverse", true);
    model.component("comp1").mesh("mesh1").create("cr1", "CornerRefinement");
    model.component("comp1").mesh("mesh1").feature("cr1").selection("boundary").named("sel2");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(26);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", 0.0015);
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().named("sel2");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhminfact", 1.4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - CFD");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "Ma", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "Ma", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "0.05 0.1", 0);
    model.study("std1").showAutoSequences("all");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", "-0.0001", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", -1, 0, 2);
    model.result().dataset("cln1").setIndex("genpoints", 0, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "-0.0001", 1, 1);
    model.result().dataset("cln1").setIndex("genpoints", 1, 1, 2);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("CFD\uff1a\u901f\u5ea6");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("selnumber", 50);
    model.result("pg1").feature("str1").selection().set(12, 26);
    model.result("pg1").feature("str1").set("inheritplot", "vol1");
    model.result("pg1").feature("str1").create("col1", "Color");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("CFD\uff1a\u6e4d\u6d41\u9ecf\u5ea6");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "spf.muT");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("CFD\uff1a\u8f74\u4e0a\u6e4d\u6d41\u9ecf\u5ea6");
    model.result("pg3").set("data", "cln1");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").label("CFD\uff1a\u8f74\u4e0a\u6e4d\u6d41\u9ecf\u5ea6");
    model.result("pg3").feature("lngr1").set("expr", "spf.muT");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "z");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg3").feature("lngr1").set("legendpattern", "Ma = eval(Ma)");
    model.result("pg3").run();

    model.component("comp1").physics().create("lnsf", "LinearizedNavierStokesFrequency", "geom1");

    model.study("std1").feature("wdi").setSolveFor("/physics/lnsf", false);
    model.study("std1").feature("stat").setSolveFor("/physics/lnsf", false);

    model.component("comp1").multiphysics().create("bffc1", "BackgroundFluidFlowCoupling", 3);
    model.component("comp1").multiphysics("bffc1").selection().all();
    model.component("comp1").multiphysics("bffc1").set("mapTurbulentViscosity", true);

    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").contribute("geom/detail", true);
    model.component("comp1").mesh("mesh2").label("\u7f51\u683c - \u58f0\u5b66");
    model.component("comp1").mesh("mesh2").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh2").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("size").set("hmax", "Dmain/8");
    model.component("comp1").mesh("mesh2").feature("size").set("hmin", "Dmain/15");
    model.component("comp1").mesh("mesh2").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh2").feature("ftet1").selection().set(2, 3, 4, 5, 7, 8);
    model.component("comp1").mesh("mesh2").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").selection().set(26);
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").set("hmax", "Dmain/15");
    model.component("comp1").mesh("mesh2").run("ftet1");
    model.component("comp1").mesh("mesh2").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh2").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh2").feature("swe1").feature("dis1").set("numelem", 16);
    model.component("comp1").mesh("mesh2").run("swe1");
    model.component("comp1").mesh("mesh2").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh2").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh2").feature("bl1").selection().geom(3);
    model.component("comp1").mesh("mesh2").feature("bl1").selection().set();
    model.component("comp1").mesh("mesh2").feature("bl1").selection().allGeom();
    model.component("comp1").mesh("mesh2").feature("bl1").set("smoothtransition", false);
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp").selection().named("sel2");
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp").set("blnlayers", 2);
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh2").feature("bl1").feature("blp").set("blhmin", "Dmain/40");
    model.component("comp1").mesh("mesh2").run();

    model.study().create("std2");
    model.study("std2").create("mapp", "Mapping");
    model.study("std2").feature("mapp").set("solnum", "auto");
    model.study("std2").feature("mapp").set("notsolnum", "auto");
    model.study("std2").feature("mapp").set("outputmap", new String[]{});
    model.study("std2").feature("mapp").setSolveFor("/physics/spf", false);
    model.study("std2").feature("mapp").setSolveFor("/physics/lnsf", false);
    model.study("std2").feature("mapp").setSolveFor("/multiphysics/bffc1", true);
    model.study("std2").feature("mapp").setSolveFor("/physics/spf", false);
    model.study("std2").feature("mapp").setSolveFor("/physics/lnsf", false);

    return model;
  }

  public static Model run2(Model model) {
    model.study("std2").feature("mapp").set("notstudy", "std1");
    model.study("std2").feature("mapp").set("notsol", "sol1");
    model.study("std2").feature("mapp").set("notsolnum", "all");
    model.study("std2").label("\u7814\u7a76 2 - \u6620\u5c04");
    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u6620\u5c04\u6bd4\u8f83\uff1az \u5411\u901f\u5ea6");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().set(12);
    model.result("pg4").feature("lngr1").set("expr", "w");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg4").feature("lngr1").set("legendpattern", "Ma = eval(Ma)");
    model.result("pg4").run();
    model.result("pg4").create("lngr2", "LineGraph");
    model.result("pg4").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr2").set("linewidth", "preference");
    model.result("pg4").feature("lngr2").set("data", "dset3");
    model.result("pg4").feature("lngr2").selection().set(12);
    model.result("pg4").feature("lngr2").set("expr", "bffc1.u_mapz");
    model.result("pg4").feature("lngr2").set("descr", "\u6620\u5c04\u7684\u901f\u5ea6\uff0cz \u5206\u91cf");
    model.result("pg4").feature("lngr2").set("xdata", "expr");
    model.result("pg4").feature("lngr2").set("xdataexpr", "x");
    model.result("pg4").feature("lngr2").set("linestyle", "dashed");
    model.result("pg4").feature("lngr2").set("legend", true);
    model.result("pg4").feature("lngr2").set("legendmethod", "evaluated");
    model.result("pg4").feature("lngr2").set("legendpattern", "Ma = eval(Ma)");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u6620\u5c04\u6bd4\u8f83\uff1a\u52a8\u529b\u5b66\u9ecf\u5ea6");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").set("expr", "spf.mu_eff");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").set("expr", "bffc1.mu_eff_map");
    model.result("pg5").feature("lngr2").set("descr", "\u6620\u5c04\u7684\u6e4d\u6d41\u9ecf\u5ea6");
    model.result("pg5").run();

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u53d8\u91cf\uff1a\u5e73\u9762\u6ce2");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("k0", "lnsf.omega/(lnsf.c0+Uin)", "\u5bf9\u6d41\u6ce2\u6570");
    model.component("comp1").variable("var1").set("pb", "1[Pa]*exp(-i*k0*z)", "\u80cc\u666f\u58f0\u538b");
    model.component("comp1").variable("var1").set("ub", "0", "\u80cc\u666f x \u5411\u58f0\u901f");
    model.component("comp1").variable("var1").set("vb", "0", "\u80cc\u666f y \u5411\u58f0\u901f");
    model.component("comp1").variable("var1")
         .set("wb", "-1/(lnsf.iomega*lnsf.rho0)*d(pb,z)", "\u80cc\u666f z \u5411\u58f0\u901f");
    model.component("comp1").variable("var1")
         .set("Tb", "lnsf.alpha_p*lnsf.T0/(lnsf.rho0*lnsf.Cp)*pb", "\u80cc\u666f\u58f0\u5b66\u6e29\u5ea6");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().set(6);
    model.component("comp1").cpl("intop1").set("opname", "intop_in");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop2").selection().set(18);
    model.component("comp1").cpl("intop2").set("opname", "intop_out");

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(1, 6);

    model.component("comp1").physics("lnsf").feature("wall1").set("MechanicalCondition", "Slip");
    model.component("comp1").physics("lnsf").feature("wall1").set("ThermalCondition", "Adiabatic");
    model.component("comp1").physics("lnsf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("lnsf").feature("sym1").selection().named("sel1");
    model.component("comp1").physics("lnsf").create("baf1", "BackgroundAcousticFields", 3);
    model.component("comp1").physics("lnsf").feature("baf1").selection().set(2);
    model.component("comp1").physics("lnsf").feature("baf1").set("p", "pb");
    model.component("comp1").physics("lnsf").feature("baf1").set("u", new String[]{"ub", "vb", "wb"});
    model.component("comp1").physics("lnsf").feature("baf1").set("T", "Tb");

    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").setSolveFor("/physics/spf", false);
    model.study("std3").feature("freq").setSolveFor("/physics/lnsf", true);
    model.study("std3").feature("freq").setSolveFor("/multiphysics/bffc1", false);
    model.study("std3").feature("freq").set("plist", "range(50,10,200)");
    model.study("std3").feature("freq").set("usesol", true);
    model.study("std3").feature("freq").set("notsolmethod", "sol");
    model.study("std3").feature("freq").set("notstudy", "std2");
    model.study("std3").feature("freq").set("notsolnum", "all");
    model.study("std3").feature("freq").set("useparam", true);
    model.study("std3").feature("freq").setIndex("pname_aux", "Ma", 0);
    model.study("std3").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std3").feature("freq").setIndex("punit_aux", "", 0);
    model.study("std3").feature("freq").setIndex("pname_aux", "Ma", 0);
    model.study("std3").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std3").feature("freq").setIndex("punit_aux", "", 0);
    model.study("std3").feature("freq").setIndex("plistarr_aux", "0.05 0.1", 0);
    model.study("std3").feature("freq").set("pcontinuationmode", "manual");
    model.study("std3").feature("freq").set("pcontinuation", "freq");
    model.study("std3").setGenPlots(false);
    model.study("std3").label("\u7814\u7a76 3 - \u58f0\u5b66\uff0cMa = 0.05 \u548c Ma = 0.1");
    model.study("std3").showAutoSequences("all");

    model.sol("sol4").feature("s1").feature("i1").active(true);

    model.study("std3").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");

    model.study("std1").feature("wdi").setSolveFor("/physics/acpr", false);
    model.study("std1").feature("stat").setSolveFor("/physics/acpr", false);
    model.study("std2").feature("mapp").setSolveFor("/physics/acpr", false);
    model.study("std3").feature("freq").setSolveFor("/physics/acpr", false);

    model.component("comp1").physics("acpr").create("nra1", "NarrowRegionAcousticsModel", 3);
    model.component("comp1").physics("acpr").feature("nra1").selection().set(7);
    model.component("comp1").physics("acpr").feature("nra1").set("DuctType", "CircularDuct");
    model.component("comp1").physics("acpr").feature("nra1").set("a", "Dneck/2");
    model.component("comp1").physics("acpr").create("bpf1", "BackgroundPressureField", 3);
    model.component("comp1").physics("acpr").feature("bpf1").selection().set(2);
    model.component("comp1").physics("acpr").feature("bpf1").set("pamp", 1);
    model.component("comp1").physics("acpr").feature("bpf1").set("c_mat", "from_mat");
    model.component("comp1").physics("acpr").feature("bpf1").set("dir", new int[]{0, 0, 1});

    model.study().create("std4");
    model.study("std4").create("freq", "Frequency");
    model.study("std4").feature("freq").setSolveFor("/physics/spf", false);
    model.study("std4").feature("freq").setSolveFor("/physics/lnsf", false);
    model.study("std4").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std4").feature("freq").setSolveFor("/multiphysics/bffc1", false);
    model.study("std4").feature("freq").set("plist", "range(50,10,200)");
    model.study("std4").setGenPlots(false);
    model.study("std4").label("\u7814\u7a76 4 - \u58f0\u5b66\uff0cMa = 0");
    model.study("std4").createAutoSequences("all");

    model.sol("sol5").runAll();

    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u58f0\u5b66\uff1a\u538b\u529b\uff0cMa = 0");
    model.result("pg6").set("data", "dset5");
    model.result("pg6").setIndex("looplevel", 11, 0);
    model.result("pg6").selection().geom("geom1", 3);
    model.result("pg6").selection().geom("geom1", 3);
    model.result("pg6").selection().set(3, 4, 5, 7, 8);
    model.result("pg6").set("applyselectiontodatasetedges", true);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "acpr.p_t");
    model.result("pg6").feature("surf1").set("colortable", "Wave");
    model.result("pg6").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("applyselectiontodatasetedges", false);
    model.result("pg7").run();
    model.result("pg7").label("\u58f0\u5b66\uff1a\u538b\u529b\uff0cMa = 0.1");
    model.result("pg7").set("data", "dset4");
    model.result("pg7").setIndex("looplevel", 11, 0);
    model.result("pg7").selection().geom("geom1", 3);
    model.result("pg7").selection().geom("geom1", 3);
    model.result("pg7").selection().set(3, 4, 5, 7, 8);
    model.result("pg7").set("applyselectiontodatasetedges", true);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "lnsf.p_t");
    model.result("pg7").feature("surf1").set("colortable", "Wave");
    model.result("pg7").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").setIndex("looplevel", 1, 1);
    model.result("pg7").run();
    model.result("pg7").setIndex("looplevel", 2, 1);
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u58f0\u5b66\uff1a\u58f0\u538b\u7ea7\uff0cMa = 0.05");
    model.result("pg8").setIndex("looplevel", 1, 1);
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("expr", "lnsf.Lp");
    model.result("pg8").feature("surf1").set("colortable", "Rainbow");
    model.result("pg8").feature("surf1").set("colorscalemode", "linear");
    model.result("pg8").feature("surf1").set("resolution", "finer");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u4f20\u8f93\u635f\u8017");
    model.result("pg9").set("data", "none");
    model.result("pg9").set("titletype", "label");
    model.result("pg9").set("xlabelactive", true);
    model.result("pg9").set("xlabel", "f (Hz)");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "TL (dB)");
    model.result("pg9").set("axislimits", true);
    model.result("pg9").set("ymin", 0);
    model.result("pg9").set("ymax", 40);
    model.result("pg9").set("xmin", 50);
    model.result("pg9").set("xmax", 200);
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").set("data", "dset5");
    model.result("pg9").feature("glob1").setIndex("expr", "20*log10(abs(intop_in(1[Pa])/intop_out(acpr.p_t)))", 0);
    model.result("pg9").feature("glob1").setIndex("descr", "Ma = 0", 0);
    model.result("pg9").feature("glob1").set("linemarker", "point");
    model.result("pg9").run();
    model.result("pg9").create("glob2", "Global");
    model.result("pg9").feature("glob2").set("markerpos", "datapoints");
    model.result("pg9").feature("glob2").set("linewidth", "preference");
    model.result("pg9").feature("glob2").set("data", "dset4");
    model.result("pg9").feature("glob2").setIndex("expr", "20*log10(abs(intop_in(pb)/intop_out(lnsf.p_t)))", 0);
    model.result("pg9").feature("glob2").set("xdatasolnumtype", "level1");
    model.result("pg9").feature("glob2").set("linemarker", "point");

    model.study("std1").feature("wdi").set("useadvanceddisable", true);
    model.study("std1").feature("wdi").set("disabledcoordinatesystems", new String[]{"pml1"});
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledcoordinatesystems", new String[]{"pml1"});

    model.result("pg7").run();

    model
         .title("\u6d41\u52a8\u5bf9\u4ea5\u59c6\u970d\u5179\u5171\u632f\u5668\u7684\u5f71\u54cd\uff1a\u6d41\u52a8\u4e0e\u58f0\u5b66\u76f8\u4e92\u4f5c\u7528");

    model
         .description("\u4ea5\u59c6\u970d\u5179\u5171\u632f\u5668\u56e0\u5176\u53ef\u4ee5\u4f7f\u7279\u5b9a\u7684\u7a84\u9891\u5e26\u8870\u51cf\u800c\u7528\u4e8e\u6392\u6c14\u7cfb\u7edf\u3002\u7cfb\u7edf\u4e2d\u7684\u6d41\u4f53\u6d41\u52a8\u4f1a\u6539\u53d8\u5171\u632f\u5668\u7684\u58f0\u5b66\u5c5e\u6027\u548c\u5b50\u7cfb\u7edf\u7684\u4f20\u8f93\u635f\u8017\u3002\u5728\u672c\u6559\u5b66\u6a21\u578b\u4e2d\uff0c\u4ea5\u59c6\u970d\u5179\u5171\u632f\u5668\u4f4d\u4e8e\u4e3b\u7ba1\u9053\u7684\u4fa7\u5206\u652f\uff0c\u7814\u7a76\u4e86\u4e3b\u7ba1\u9053\u4e2d\u5f15\u5165\u6d41\u52a8\u65f6\u7684\u4f20\u8f93\u635f\u8017\u3002\n\n\u5176\u4e2d\u4f7f\u7528 SST \u6e4d\u6d41\u6a21\u578b\u8ba1\u7b97 Ma = 0.05 \u548c Ma = 0.1 \u65f6\u7684\u5e73\u5747\u6d41\u91cf\u3002\u7136\u540e\u4f7f\u7528\u7ebf\u6027\u7eb3\u7ef4-\u65af\u6258\u514b\u65af\uff0c\u9891\u57df (LNS) \u63a5\u53e3\u6c42\u89e3\u58f0\u5b66\u95ee\u9898\u3002LNS \u6a21\u578b\u8026\u5408\u4e86\u5e73\u5747\u6d41\u901f\u3001\u538b\u529b\u548c\u6e4d\u6d41\u9ecf\u5ea6\u3002\u4eff\u771f\u7ed3\u679c\u4e0e\u671f\u520a\u8bba\u6587\u4e2d\u7684\u6d4b\u91cf\u7ed3\u679c\u8fdb\u884c\u4e86\u6bd4\u8f83\uff0c\u5927\u5c0f\u548c\u5171\u632f\u4f4d\u7f6e\u4e0e\u6d4b\u91cf\u6570\u636e\u5448\u73b0\u9ad8\u5ea6\u4e00\u81f4\uff08\u5982\u4e00\u7ef4\u7ed8\u56fe\u6240\u793a\uff09\u3002\u9700\u8981\u4e25\u683c\u6a21\u62df\u8870\u51cf\u6548\u5e94\u4e0e\u6d41\u52a8\u6548\u5e94\u4e4b\u95f4\u7684\u5e73\u8861\uff0c\u624d\u80fd\u5f97\u5230\u6b63\u786e\u7684\u5171\u632f\u4f4d\u7f6e\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("helmholtz_resonator_with_flow.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
