/*
 * flat_plate_nitf_turbulent.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:37 by COMSOL 6.3.0.290. */
public class flat_plate_nitf_turbulent {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "TurbulentFlowSST", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("dz", "1[m]");
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 2);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");

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
    model.study("std1").feature("wdi").setSolveFor("/physics/ht", true);
    model.study("std1").feature("wdi").setSolveFor("/multiphysics/nitf1", true);
    model.study("std1").feature("wdi").setSolveFor("/physics/ht", false);
    model.study("std1").feature("wdi").setSolveFor("/multiphysics/nitf1", false);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", true);

    model.param().set("L", "5[m]");
    model.param().descr("L", "\u677f\u957f");
    model.param().set("b", "0.5[m]");
    model.param().descr("b", "\u9ad8\u5ea6");
    model.param().set("T0", "283[K]");
    model.param().descr("T0", "\u5165\u53e3\u6e29\u5ea6");
    model.param().set("U0", "0.5[m/s]");
    model.param().descr("U0", "\u5165\u53e3\u901f\u5ea6");
    model.param().set("qw", "10[W/m^2]");
    model.param().descr("qw", "\u58c1\u70ed\u901a\u91cf");
    model.param().set("mesh_coeff", "0.1");
    model.param().descr("mesh_coeff", "\u53c2\u6570\u5316\u7814\u7a76\u7684\u7f51\u683c\u7cfb\u6570");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L*2", "b"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "L", 0);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r1").set("layerleft", true);
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

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1")
         .set("Tb", "integrate(comp1.at2(x,y,u*T),y,0,b)/integrate(comp1.at2(x,y,u),y,0,b)");
    model.component("comp1").variable("var1").descr("Tb", "\u672c\u4f53\u6e29\u5ea6");
    model.component("comp1").variable("var1").set("x_plate", "x-L");
    model.component("comp1").variable("var1").descr("x_plate", "\u6cbf\u677f\u7684\u4f4d\u7f6e");
    model.component("comp1").variable("var1").set("T_film", "0.5*(T+T0)");
    model.component("comp1").variable("var1").descr("T_film", "\u819c\u6e29");
    model.component("comp1").variable("var1").set("rho_film", "mat1.def.rho(ht.pA,T_film)");
    model.component("comp1").variable("var1").descr("rho_film", "\u819c\u5bc6\u5ea6");
    model.component("comp1").variable("var1").set("k_film", "mat1.def.k(T_film)");
    model.component("comp1").variable("var1").descr("k_film", "\u819c\u70ed\u5bfc\u7387");
    model.component("comp1").variable("var1").set("Cp_film", "mat1.def.Cp(T_film)");
    model.component("comp1").variable("var1").descr("Cp_film", "\u819c\u70ed\u5bb9");
    model.component("comp1").variable("var1").set("mu_film", "mat1.def.eta(T_film)");
    model.component("comp1").variable("var1").descr("mu_film", "\u819c\u9ecf\u5ea6");
    model.component("comp1").variable("var1").set("Pr_film", "Cp_film*mu_film/k_film");
    model.component("comp1").variable("var1")
         .descr("Pr_film", "\u57fa\u4e8e\u819c\u5c5e\u6027\u7684\u666e\u6717\u7279\u6570");
    model.component("comp1").variable("var1").set("Re_film", "rho_film*U0*x_plate/mu_film");
    model.component("comp1").variable("var1")
         .descr("Re_film", "\u57fa\u4e8e\u819c\u5c5e\u6027\u7684\u96f7\u8bfa\u6570");
    model.component("comp1").variable("var1").set("Nu_x_turb_Bejan", "0.0296*Re_film^0.8*Pr_film^(1/3)");
    model.component("comp1").variable("var1").descr("Nu_x_turb_Bejan", "\u52aa\u585e\u5c14\u6570 (Bejan, 5.131')");
    model.component("comp1").variable("var1").set("Nu_x_turb_Lienhard", "0.032*Re_film^0.8*Pr_film^0.43");
    model.component("comp1").variable("var1")
         .descr("Nu_x_turb_Lienhard", "\u52aa\u585e\u5c14\u6570 (Lienhard, 6.115)");

    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics("spf").feature("init1").set("u_init", new String[]{"U0", "0", "0"});
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(1);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "U0");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(7);
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("spf").feature("sym1").selection().set(3, 6);
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 1);
    model.component("comp1").physics("ht").feature("ifl1").selection().set(1);
    model.component("comp1").physics("ht").feature("ifl1").set("Tustr", "T0");
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 1);
    model.component("comp1").physics("ht").feature("ofl1").selection().set(7);
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("ht").feature("sym1").selection().set(3, 6);
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").set("q0_input", "qw");
    model.component("comp1").physics("ht").feature("hf1").selection().set(5);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature().remove("size1");
    model.component("comp1").mesh("mesh1").feature().remove("cr1");
    model.component("comp1").mesh("mesh1").feature().remove("ftri1");
    model.component("comp1").mesh("mesh1").feature().remove("bl1");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1")
         .label("\u5206\u5e03\uff08\u6c34\u5e73\uff09");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2, 3, 5, 6);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", "L*100*mesh_coeff");
    model.component("comp1").mesh("mesh1").feature("map1").feature().duplicate("dis2", "dis1");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2")
         .label("\u5206\u5e03\uff08\u5782\u76f4\uff09");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(1, 4, 7);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", "100*mesh_coeff");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 8);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "L", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "L", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "mesh_coeff", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0.1 0.25 0.5 0.75", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().dataset("dset3").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").setIndex("looplevel", 4, 0);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").setIndex("looplevel", 4, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().dataset().create("edg1", "Edge2D");
    model.result().dataset("edg1").label("\u5916\u58c1");
    model.result().dataset("edg1").set("data", "dset3");
    model.result().dataset("edg1").selection().geom("geom1", 1);
    model.result().dataset("edg1").selection().set(2, 5);
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 4, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("line1", "Line");
    model.result("pg3").feature("line1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg3").feature("line1").set("showsolutionparams", "on");
    model.result("pg3").feature("line1").set("expr", "spf.Delta_wPlus");
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("smooth", "internal");
    model.result("pg3").feature("line1").set("showsolutionparams", "on");
    model.result("pg3").feature("line1").set("data", "parent");
    model.result("pg3").feature("line1").feature().create("hght1", "Height");
    model.result("pg3").feature("line1").feature("hght1").label("\u9ad8\u5ea6\u8868\u8fbe\u5f0f");
    model.result("pg3").feature("line1").feature("hght1").set("heightdata", "expr");
    model.result("pg3").feature("line1").feature("hght1").set("expr", "spf.WRHeightExpr");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u6e29\u5ea6 (ht)");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 4, 0);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "T");
    model.result("pg4").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 4, 0);
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u6d41\u4f53\u6e29\u5ea6");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("expr", "nitf1.T");
    model.result("pg5").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg5").feature("surf1").feature("sel1").selection().set(1, 2);
    model.result("pg5").feature().create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg5").feature("arws1").set("showsolutionparams", "on");
    model.result("pg5").feature("arws1").set("solutionparams", "parent");
    model.result("pg5").feature("arws1").set("expr", new String[]{"nitf1.ux", "nitf1.uy"});
    model.result("pg5").feature("arws1").set("xnumber", 30);
    model.result("pg5").feature("arws1").set("ynumber", 30);
    model.result("pg5").feature("arws1").set("arrowtype", "cone");
    model.result("pg5").feature("arws1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("arws1").set("showsolutionparams", "on");
    model.result("pg5").feature("arws1").set("data", "parent");
    model.result("pg5").feature("arws1").feature().create("col1", "Color");
    model.result("pg5").feature("arws1").feature("col1").set("showcolordata", "off");
    model.result("pg5").feature("arws1").feature().create("filt1", "Filter");
    model.result("pg5").feature("arws1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg1").run();
    model.result().dataset().create("dset4", "Solution");
    model.result().dataset("dset4").set("solution", "sol3");
    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().set(2);
    model.result("pg1").run();
    model.result("pg1").set("data", "dset4");
    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").set("data", "dset4");
    model.result("pg4").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u4f20\u70ed\u7cfb\u6570");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u4f20\u70ed\u7cfb\u6570 (W/(m\u00b2.K))");
    model.result("pg6").set("axislimits", true);
    model.result("pg6").set("xmin", 0);
    model.result("pg6").set("xmax", 5);
    model.result("pg6").set("ymin", 2);
    model.result("pg6").set("ymax", 6);
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").label("\u6570\u503c");
    model.result("pg6").feature("lngr1").selection().set(5);
    model.result("pg6").feature("lngr1").set("expr", "qw/(T-Tb)");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x_plate");
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("autoplotlabel", true);
    model.result("pg6").run();
    model.result("pg6").create("lngr2", "LineGraph");
    model.result("pg6").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr2").set("linewidth", "preference");
    model.result("pg6").feature("lngr2").set("data", "dset3");
    model.result("pg6").feature("lngr2").setIndex("looplevelinput", "last", 0);
    model.result("pg6").feature("lngr2").selection().set(5);
    model.result("pg6").feature("lngr2").label("Bejan");
    model.result("pg6").feature("lngr2").set("expr", "ht.kxx*Nu_x_turb_Bejan/x_plate");
    model.result("pg6").feature("lngr2").set("xdata", "expr");
    model.result("pg6").feature("lngr2").set("xdataexpr", "x_plate");
    model.result("pg6").feature("lngr2").set("linestyle", "dashed");
    model.result("pg6").feature("lngr2").set("linecolor", "fromtheme");
    model.result("pg6").feature("lngr2").set("legend", true);
    model.result("pg6").feature("lngr2").set("autosolution", false);
    model.result("pg6").feature("lngr2").set("autoplotlabel", true);
    model.result("pg6").run();
    model.result("pg6").create("lngr3", "LineGraph");
    model.result("pg6").feature("lngr3").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr3").set("linewidth", "preference");
    model.result("pg6").feature("lngr3").set("data", "dset3");
    model.result("pg6").feature("lngr3").setIndex("looplevelinput", "last", 0);
    model.result("pg6").feature("lngr3").selection().set(5);
    model.result("pg6").feature("lngr3").label("Lienhard");
    model.result("pg6").feature("lngr3").set("expr", "ht.kxx*Nu_x_turb_Lienhard/x_plate");
    model.result("pg6").feature("lngr3").set("xdata", "expr");
    model.result("pg6").feature("lngr3").set("xdataexpr", "x_plate");
    model.result("pg6").feature("lngr3").set("linestyle", "dotted");
    model.result("pg6").feature("lngr3").set("linecolor", "fromtheme");
    model.result("pg6").feature("lngr3").set("legend", true);
    model.result("pg6").feature("lngr3").set("autosolution", false);
    model.result("pg6").feature("lngr3").set("autoplotlabel", true);
    model.result("pg6").run();

    model.title("\u5e73\u677f\u4e0a\u65b9\u7684\u975e\u7b49\u6e29\u6e4d\u6d41");

    model
         .description("\u8fd9\u4e2a\u57fa\u51c6\u6a21\u578b\u6a21\u62df\u5e73\u677f\u4e0a\u65b9\u5145\u5206\u53d1\u5c55\u7684\u975e\u7b49\u6e29\u6e4d\u6d41\uff0c\u5e76\u5c06\u4eff\u771f\u5f97\u5230\u7684\u4f20\u70ed\u7cfb\u6570\u4e0e\u57fa\u4e8e\u52aa\u585e\u5c14\u6570\u7684\u76f8\u5173\u51fd\u6570\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    return model;
  }

  public static Model run2(Model model) {
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();

    model.label("flat_plate_nitf_turbulent.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
