/*
 * evaporative_cooling.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:24 by COMSOL 6.3.0.290. */
public class evaporative_cooling {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Phase_Change");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "TurbulentFlowlowRekeps", "geom1");

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

    model.component("comp1").geom("geom1").insertFile("evaporative_cooling_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

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

    model.component("comp1").physics("spf").prop("TurbulenceModelProperty")
         .set("WallTreatment", "LowReynoldsNumber");
    model.component("comp1").physics("spf").selection().set(1);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(3);
    model.component("comp1").selection("sel1").label("\u7a7a\u6c14");
    model.component("comp1").selection("sel1").set(1);

    model.component("comp1").physics("spf").selection().named("sel1");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(33);
    model.component("comp1").physics("spf").feature("inl1").set("U0in", "2[m/s]");
    model.component("comp1").physics("spf").create("open1", "OpenBoundary", 2);
    model.component("comp1").physics("spf").feature("open1").selection().set(1);
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().set(2);

    model.component("comp1").mesh("mesh1").autoMeshSize(8);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size3").selection().set(3, 4, 5, 7, 8, 14, 27, 28, 30);
    model.component("comp1").mesh("mesh1").feature("size3").set("custom", false);
    model.component("comp1").mesh("mesh1").feature("size3").set("hauto", 6);
    model.component("comp1").mesh("mesh1").run("size3");
    model.component("comp1").mesh("mesh1").feature().duplicate("size4", "size3");
    model.component("comp1").mesh("mesh1").feature("size4").set("hauto", 4);
    model.component("comp1").mesh("mesh1").feature("size4").selection().set(9, 12, 29);
    model.component("comp1").mesh("mesh1").feature("size4").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size4").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size4").set("hmax", 0.2);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().all();
    model.component("comp1").mesh("mesh1").feature("bl1").set("trimminangle", 350);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blnlayers", 4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u5207\u9762");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection().set(3, 4, 5, 7, 8, 9, 12, 14, 27, 28, 29, 30);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg3").set("data", "surf1");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "spf.Delta_wPlus");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "\u6e29\u5ea6", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();

    model.component("comp1").physics().create("ht", "HeatTransferInMoistAir", "geom1");

    model.study("std1").feature("wdi").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);

    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", "1");
    model.component("comp1").physics().create("mt", "MoistureTransportInAir", "geom1");

    model.study("std1").feature("wdi").setSolveFor("/physics/mt", false);
    model.study("std1").feature("stat").setSolveFor("/physics/mt", false);

    model.component("comp1").physics("mt").prop("ShapeProperty").set("order_relativehumidity_disc", "1");

    model.component("comp1").multiphysics().create("ham1", "HeatAndMoisture", 3);

    model.study("std1").feature("wdi").setSolveFor("/multiphysics/ham1", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/ham1", false);

    model.component("comp1").multiphysics("ham1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("ham1").set("Moist_physics", "mt");
    model.component("comp1").multiphysics("ham1").selection().all();

    model.param().set("phi0", "0.2");
    model.param().descr("phi0", "\u521d\u59cb\u76f8\u5bf9\u6e7f\u5ea6");
    model.param().set("K", "100[m/s]");
    model.param().descr("K", "\u84b8\u53d1\u7387\u5e38\u6570");

    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("rho", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("cs", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat2").propertyGroup("def").func().create("an3", "Analytic");
    model.component("comp1").material("mat2").label("Water, liquid");
    model.component("comp1").material("mat2").set("family", "water");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"273.15", "413.15", "1.3799566804-0.021224019151*T^1+1.3604562827E-4*T^2-4.6454090319E-7*T^3+8.9042735735E-10*T^4-9.0790692686E-13*T^5+3.8457331488E-16*T^6"}, {"413.15", "553.75", "0.00401235783-2.10746715E-5*T^1+3.85772275E-8*T^2-2.39730284E-11*T^3"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"273.15", "553.75", "12010.1471-80.4072879*T^1+0.309866854*T^2-5.38186884E-4*T^3+3.62536437E-7*T^4"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").label("Piecewise 3");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("smooth", "contd1");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho")
         .set("pieces", new String[][]{{"273.15", "293.15", "0.000063092789034*T^3-0.060367639882855*T^2+18.9229382407066*T-950.704055329848"}, {"293.15", "373.15", "0.000010335053319*T^3-0.013395065634452*T^2+4.969288832655160*T+432.257114008512"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").label("Piecewise 4");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat2").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"273.15", "553.75", "-0.869083936+0.00894880345*T^1-1.58366345E-5*T^2+7.97543259E-9*T^3"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat2").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").label("Interpolation");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs")
         .set("table", new String[][]{{"273", "1403"}, 
         {"278", "1427"}, 
         {"283", "1447"}, 
         {"293", "1481"}, 
         {"303", "1507"}, 
         {"313", "1526"}, 
         {"323", "1541"}, 
         {"333", "1552"}, 
         {"343", "1555"}, 
         {"353", "1555"}, 
         {"363", "1550"}, 
         {"373", "1543"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("interp", "piecewisecubic");
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("fununit", new String[]{"m/s"});
    model.component("comp1").material("mat2").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("expr", "-1/rho(T)*d(rho(T),T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat2").propertyGroup("def").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").label("Analytic 2");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("funcname", "gamma_w");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("expr", "1+(T/Cp(T))*(alpha_p(T)*cs(T))^2");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("fununit", "1");
    model.component("comp1").material("mat2").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").label("Analytic 3");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("funcname", "muB");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("expr", "2.79*eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("args", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("fununit", "Pa*s");
    model.component("comp1").material("mat2").propertyGroup("def").func("an3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat2").propertyGroup("def").func("an3")
         .set("plotargs", new String[][]{{"T", "273.15", "553.75"}});
    model.component("comp1").material("mat2").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(T)", "0", "0", "0", "alpha_p(T)", "0", "0", "0", "alpha_p(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("ratioofspecificheat", "gamma_w(T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]", "0", "0", "0", "5.5e-6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat2").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat2").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat2").selection().set(3);

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(3);
    model.component("comp1").selection("sel2").label("\u6c34");
    model.component("comp1").selection("sel2").set(3);

    model.component("comp1").material("mat2").selection().named("sel2");

    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("\u73bb\u7483");
    model.component("comp1").selection("com1").set("input", new String[]{"sel1", "sel2"});

    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat3").label("Glass (quartz)");
    model.component("comp1").material("mat3").set("family", "custom");
    model.component("comp1").material("mat3").set("diffuse", "custom");
    model.component("comp1").material("mat3").set("ambient", "custom");
    model.component("comp1").material("mat3").set("noise", true);
    model.component("comp1").material("mat3").set("fresnel", 0.99);
    model.component("comp1").material("mat3").set("roughness", 0.02);
    model.component("comp1").material("mat3").set("diffusewrap", 0);
    model.component("comp1").material("mat3").set("reflectance", 0);
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]", "0", "0", "0", "1e-14[S/m]", "0", "0", "0", "1e-14[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"4.2", "0", "0", "0", "4.2", "0", "0", "0", "4.2"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "2210[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]", "0", "0", "0", "1.4[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "730[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1.5", "0", "0", "0", "1.5", "0", "0", "0", "1.5"});
    model.component("comp1").material("mat3").selection().named("com1");

    model.component("comp1").physics("ht").create("fluid1", "FluidHeatTransferModel", 3);
    model.component("comp1").physics("ht").feature("fluid1").selection().set(3);
    model.component("comp1").physics("ht").feature("fluid1").create("cec1", "ConvectivelyEnhancedConductivity", 3);
    model.component("comp1").physics("ht").feature("fluid1").feature("cec1")
         .set("NuCorrType", "verticalParallelPlates");
    model.component("comp1").physics("ht").feature("fluid1").feature("cec1").set("NuCorrVLength", "8[cm]");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").physics("ht").feature("fluid1").feature("cec1").set("NuCorrVThickness", "3.5[cm]");
    model.component("comp1").physics("ht").create("solid1", "SolidHeatTransferModel", 3);
    model.component("comp1").physics("ht").feature("solid1").selection().named("com1");
    model.component("comp1").physics("ht").create("ifl1", "Inflow", 2);
    model.component("comp1").physics("ht").feature("ifl1").selection().set(33);
    model.component("comp1").physics("ht").create("open1", "OpenBoundary", 2);
    model.component("comp1").physics("ht").feature("open1").selection().set(1);
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ht").feature("sym1").selection().set(2, 6, 11, 13, 18, 31, 32);
    model.component("comp1").physics("ht").create("init2", "init", 3);
    model.component("comp1").physics("ht").feature("init2").selection().set(3);
    model.component("comp1").physics("ht").feature("init2").selection().named("sel2");
    model.component("comp1").physics("ht").feature("init2").set("Tinit", "80[degC]");
    model.component("comp1").physics("mt").selection().named("sel1");
    model.component("comp1").physics("mt").feature("init1").set("phi_init", "phi0");
    model.component("comp1").physics("mt").create("ifl1", "Inflow", 2);
    model.component("comp1").physics("mt").feature("ifl1").selection().set(33);
    model.component("comp1").physics("mt").feature("ifl1").set("phiustr", "phi0");
    model.component("comp1").physics("mt").create("open1", "OpenBoundary", 2);
    model.component("comp1").physics("mt").feature("open1").selection().set(1);
    model.component("comp1").physics("mt").feature("open1").set("phiustr", "phi0");
    model.component("comp1").physics("mt").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("mt").feature("sym1").selection().set(2);
    model.component("comp1").physics("mt").create("ws1", "WetSurface", 2);
    model.component("comp1").physics("mt").feature("ws1").selection().set(12);
    model.component("comp1").physics("mt").feature("ws1").set("K", "K");

    model.component("comp1").multiphysics("ham1").set("includeLatentHeatSourceOnSurfaces", false);
    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 3);
    model.component("comp1").multiphysics("nitf1").set("BoussinesqApproximation", true);
    model.component("comp1").multiphysics().create("mf1", "MoistureFlow", 3);

    model.study("std1").feature("wdi").setSolveFor("/multiphysics/nitf1", false);
    model.study("std1").feature("wdi").setSolveFor("/multiphysics/mf1", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/mf1", false);
    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/spf", false);
    model.study("std2").feature("time").setSolveFor("/physics/ht", true);
    model.study("std2").feature("time").setSolveFor("/physics/mt", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/ham1", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/nitf1", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/mf1", true);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u65e0\u6f5c\u70ed\u6e90");
    model.study("std2").feature("time").set("tunit", "min");
    model.study("std2").feature("time").set("tlist", "range(0,20[s],20)");
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").showAutoSequences("all");

    model.sol("sol3").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol3").feature("t1").set("initialstepbdf", 0.1);
    model.sol("sol3").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol3").feature("t1").set("maxstepbdf", 1);
    model.sol("sol3").feature("t1").create("fc2", "FullyCoupled");
    model.sol("sol3").feature("t1").feature("fc1").set("jtech", "onevery");

    model.study("std2").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u6e29\u5ea6 (ht)");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").setIndex("looplevel", 61, 0);
    model.result("pg4").feature().create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("solutionparams", "parent");
    model.result("pg4").feature("vol1").set("expr", "T");
    model.result("pg4").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("vol1").set("smooth", "internal");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("data", "parent");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u76f8\u5bf9\u6e7f\u5ea6 (mt)");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 61, 0);
    model.result("pg5").feature().create("mslc1", "Multislice");
    model.result("pg5").feature("mslc1").set("expr", "mt.phi");
    model.result("pg5").feature("mslc1").set("colortable", "Kyanite");
    model.result("pg5").feature("mslc1").set("smooth", "internal");
    model.result("pg5").feature("mslc1").set("data", "parent");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").setIndex("looplevel", 61, 0);
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u58c1\u6e29");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("solutionparams", "parent");
    model.result("pg6").feature("surf1").set("expr", "ht.Tvar");
    model.result("pg6").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg6").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg6").feature("surf1").feature("sel1").selection().set(3, 4, 5, 7, 8, 9, 12, 14, 27, 28, 29, 30);
    model.result("pg6").feature().create("vol1", "Volume");
    model.result("pg6").feature("vol1").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg6").feature("vol1").set("showsolutionparams", "on");
    model.result("pg6").feature("vol1").set("solutionparams", "parent");
    model.result("pg6").feature("vol1").set("expr", "nitf1.T");
    model.result("pg6").feature("vol1").set("smooth", "internal");
    model.result("pg6").feature("vol1").set("showsolutionparams", "on");
    model.result("pg6").feature("vol1").set("data", "parent");
    model.result("pg6").feature("vol1").feature().create("sel1", "Selection");
    model.result("pg6").feature("vol1").feature("sel1").selection().geom("geom1", 3);
    model.result("pg6").feature("vol1").feature("sel1").selection().set(2, 4, 5, 6, 7);
    model.result("pg6").feature("vol1").set("inheritplot", "surf1");
    model.result("pg6").feature().create("arwv1", "ArrowVolume");
    model.result("pg6").feature("arwv1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg6").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg6").feature("arwv1").set("solutionparams", "parent");
    model.result("pg6").feature("arwv1").set("expr", new String[]{"nitf1.ux", "nitf1.uy", "nitf1.uz"});
    model.result("pg6").feature("arwv1").set("xnumber", 30);
    model.result("pg6").feature("arwv1").set("ynumber", 30);
    model.result("pg6").feature("arwv1").set("znumber", 30);
    model.result("pg6").feature("arwv1").set("arrowtype", "cone");
    model.result("pg6").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg6").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg6").feature("arwv1").set("data", "parent");
    model.result("pg6").feature("arwv1").feature().create("col1", "Color");
    model.result("pg6").feature("arwv1").feature("col1").set("showcolordata", "off");
    model.result("pg6").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg6").feature("arwv1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("vol1").create("tran1", "Transparency");
    model.result("pg4").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature().remove("arwv1");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").label("\u6e29\u5ea6\u548c\u6d41\u7ebf");
    model.result("pg7").create("str1", "Streamline");
    model.result("pg7").feature("str1").set("data", "dset1");
    model.result("pg7").feature("str1").set("color", "white");
    model.result("pg7").feature("str1").selection().set(33);
    model.result("pg7").feature("str1").set("pointtype", "arrow");
    model.result("pg7").feature("str1").set("arrowcountactive", true);
    model.result("pg7").feature("str1").set("arrowcount", 80);
    model.result("pg7").run();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("quickplane", "xz");
    model.result().dataset("cpl1").set("data", "dset3");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").run();
    model.result("pg8").label("\u6c34\u5206\u6d53\u5ea6\u548c\u76f8\u5bf9\u6e7f\u5ea6");
    model.result("pg8").set("view", "new");
    model.result("pg8").set("data", "cpl1");
    model.result("pg8").setIndex("looplevel", 31, 0);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "mt.cv");
    model.result("pg8").feature("surf1").set("descr", "\u84b8\u6c7d\u6d53\u5ea6");
    model.result("pg8").run();
    model.result("pg8").create("con1", "Contour");
    model.result("pg8").feature("con1").set("expr", "mt.phi");
    model.result("pg8").feature("con1").set("number", 7);
    model.result("pg8").feature("con1").set("contourtype", "tubes");
    model.result("pg8").feature("con1").set("tuberadiusscaleactive", true);
    model.result("pg8").feature("con1").set("tuberadiusscale", 0.025);
    model.result("pg8").feature("con1").set("contourlabels", true);
    model.result("pg8").feature("con1").set("labelprec", 2);
    model.result("pg8").feature("con1").set("labelcolor", "white");
    model.result("pg8").run();
    model.result().numerical().create("av1", "AvVolume");
    model.result().numerical("av1").label("\u5e73\u5747\u6c34\u6e29");
    model.result().numerical("av1").set("data", "dset3");
    model.result().numerical("av1").selection().set(3);
    model.result().numerical("av1").set("expr", new String[]{"T"});
    model.result().numerical("av1").set("descr", new String[]{"\u6e29\u5ea6"});
    model.result().numerical("av1").set("unit", new String[]{"\u00b0C"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5e73\u5747\u6c34\u6e29");
    model.result().numerical("av1").set("table", "tbl1");
    model.result().numerical("av1").setResult();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").set("data", "none");
    model.result("pg9").create("tblp1", "Table");
    model.result("pg9").feature("tblp1").set("source", "table");
    model.result("pg9").feature("tblp1").set("table", "tbl1");
    model.result("pg9").feature("tblp1").set("linewidth", "preference");
    model.result("pg9").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").label("\u5e73\u5747\u6c34\u6e29\u968f\u65f6\u95f4\u7684\u53d8\u5316");
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").label("\u84b8\u53d1\u7684\u6c34\u91cf");
    model.result().numerical("int1").set("data", "dset3");
    model.result().numerical("int1").selection().set(12);
    model.result().numerical("int1").setIndex("expr", "2*mt.ntflux", 0);
    model.result().numerical("int1").set("dataseries", "integral");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u84b8\u53d1\u7684\u6c34\u91cf");
    model.result().numerical("int1").set("table", "tbl2");
    model.result().numerical("int1").setResult();

    model.component("comp1").multiphysics().create("ham2", "HeatAndMoisture", 3);
    model.component("comp1").multiphysics("ham2").selection().set(1);

    model.study("std1").feature("stat").setSolveFor("/multiphysics/ham2", false);
    model.study("std2").feature("time").setSolveFor("/multiphysics/ham2", false);
    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/spf", false);
    model.study("std3").feature("time").setSolveFor("/physics/ht", true);
    model.study("std3").feature("time").setSolveFor("/physics/mt", true);
    model.study("std3").feature("time").setSolveFor("/multiphysics/ham1", true);
    model.study("std3").feature("time").setSolveFor("/multiphysics/nitf1", true);
    model.study("std3").feature("time").setSolveFor("/multiphysics/mf1", true);
    model.study("std3").feature("time").setSolveFor("/multiphysics/ham2", true);
    model.study("std3").label("\u7814\u7a76 3\uff1a\u6f5c\u70ed\u6e90");
    model.study("std3").feature("time").set("tunit", "min");
    model.study("std3").feature("time").set("tlist", "range(0,20[s],20)");
    model.study("std3").feature("time").setSolveFor("/multiphysics/ham1", false);
    model.study("std3").feature("time").set("usesol", true);
    model.study("std3").feature("time").set("notsolmethod", "sol");
    model.study("std3").feature("time").set("notstudy", "std1");
    model.study("std3").showAutoSequences("all");

    model.sol("sol4").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol4").feature("t1").set("initialstepbdf", 0.1);
    model.sol("sol4").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol4").feature("t1").set("maxstepbdf", 1);
    model.sol("sol4").feature("t1").create("fc2", "FullyCoupled");
    model.sol("sol4").feature("t1").feature("fc1").set("jtech", "onevery");

    model.study("std3").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").label("\u6e29\u5ea6 (ht) 1");
    model.result("pg10").set("data", "dset4");
    model.result("pg10").setIndex("looplevel", 61, 0);
    model.result("pg10").feature().create("vol1", "Volume");
    model.result("pg10").feature("vol1").set("showsolutionparams", "on");
    model.result("pg10").feature("vol1").set("solutionparams", "parent");
    model.result("pg10").feature("vol1").set("expr", "T");
    model.result("pg10").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg10").feature("vol1").set("smooth", "internal");
    model.result("pg10").feature("vol1").set("showsolutionparams", "on");
    model.result("pg10").feature("vol1").set("data", "parent");
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").label("\u76f8\u5bf9\u6e7f\u5ea6 (mt) 1");
    model.result("pg11").set("data", "dset4");
    model.result("pg11").setIndex("looplevel", 61, 0);
    model.result("pg11").feature().create("mslc1", "Multislice");
    model.result("pg11").feature("mslc1").set("expr", "mt.phi");
    model.result("pg11").feature("mslc1").set("colortable", "Kyanite");
    model.result("pg11").feature("mslc1").set("smooth", "internal");
    model.result("pg11").feature("mslc1").set("data", "parent");
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1) 1");
    model.result("pg12").set("data", "dset4");
    model.result("pg12").setIndex("looplevel", 61, 0);
    model.result("pg12").set("showlegendsunit", true);
    model.result("pg12").feature().create("surf1", "Surface");
    model.result("pg12").feature("surf1").label("\u58c1\u6e29");
    model.result("pg12").feature("surf1").set("showsolutionparams", "on");
    model.result("pg12").feature("surf1").set("solutionparams", "parent");
    model.result("pg12").feature("surf1").set("expr", "ht.Tvar");
    model.result("pg12").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg12").feature("surf1").set("smooth", "internal");
    model.result("pg12").feature("surf1").set("showsolutionparams", "on");
    model.result("pg12").feature("surf1").set("data", "parent");
    model.result("pg12").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg12").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg12").feature("surf1").feature("sel1").selection().set(3, 4, 5, 7, 8, 9, 12, 14, 27, 28, 29, 30);
    model.result("pg12").feature().create("vol1", "Volume");
    model.result("pg12").feature("vol1").label("\u56fa\u4f53\u6e29\u5ea6");
    model.result("pg12").feature("vol1").set("showsolutionparams", "on");
    model.result("pg12").feature("vol1").set("solutionparams", "parent");
    model.result("pg12").feature("vol1").set("expr", "nitf1.T");
    model.result("pg12").feature("vol1").set("smooth", "internal");
    model.result("pg12").feature("vol1").set("showsolutionparams", "on");
    model.result("pg12").feature("vol1").set("data", "parent");
    model.result("pg12").feature("vol1").feature().create("sel1", "Selection");
    model.result("pg12").feature("vol1").feature("sel1").selection().geom("geom1", 3);
    model.result("pg12").feature("vol1").feature("sel1").selection().set(2, 4, 5, 6, 7);
    model.result("pg12").feature("vol1").set("inheritplot", "surf1");
    model.result("pg12").feature().create("arwv1", "ArrowVolume");
    model.result("pg12").feature("arwv1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg12").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg12").feature("arwv1").set("solutionparams", "parent");
    model.result("pg12").feature("arwv1").set("expr", new String[]{"nitf1.ux", "nitf1.uy", "nitf1.uz"});
    model.result("pg12").feature("arwv1").set("xnumber", 30);
    model.result("pg12").feature("arwv1").set("ynumber", 30);
    model.result("pg12").feature("arwv1").set("znumber", 30);
    model.result("pg12").feature("arwv1").set("arrowtype", "cone");
    model.result("pg12").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg12").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg12").feature("arwv1").set("data", "parent");
    model.result("pg12").feature("arwv1").feature().create("col1", "Color");
    model.result("pg12").feature("arwv1").feature("col1").set("showcolordata", "off");
    model.result("pg12").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg12").feature("arwv1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").feature("vol1").create("tran1", "Transparency");
    model.result("pg10").run();
    model.result("pg7").run();
    model.result().duplicate("pg13", "pg7");
    model.result("pg13").run();
    model.result("pg13").set("data", "dset4");
    model.result().dataset("cpl1").set("data", "dset4");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().table("tbl1").clearTableData();
    model.result().numerical().duplicate("av2", "av1");
    model.result().numerical("av2").set("data", "dset4");
    model.result().numerical("av2").set("table", "tbl1");
    model.result().numerical("av2").setResult();
    model.result("pg9").run();
    model.result().numerical("av1").set("table", "tbl1");
    model.result().numerical("av1").appendResult();
    model.result("pg9").run();
    model.result("pg9").feature("tblp1").set("linewidth", 2);
    model.result("pg9").feature("tblp1").set("legend", true);
    model.result("pg9").feature("tblp1").set("legendmethod", "manual");
    model.result("pg9").feature("tblp1").setIndex("legends", "\u5305\u542b\u84b8\u53d1\u6f5c\u70ed", 0);
    model.result("pg9").feature("tblp1").setIndex("legends", "\u5ffd\u7565\u84b8\u53d1\u6f5c\u70ed", 1);
    model.result("pg9").run();
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u6e29\u5ea6 (degC)");
    model.result("pg9").set("legendlayout", "outside");
    model.result("pg9").set("legendposoutside", "bottom");
    model.result("pg9").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u8d28\u91cf\u5e73\u8861");
    model.result().numerical("gev1").set("data", "dset4");
    model.result().numerical("gev1").set("expr", new String[]{"mt.massBalance"});
    model.result().numerical("gev1").set("descr", new String[]{"\u8d28\u91cf\u5e73\u8861"});
    model.result().numerical("gev1").set("unit", new String[]{"kg/s"});
    model.result().numerical("gev1").set("expr", new String[]{"mt.massBalance", "mt.dwcInt"});
    model.result().numerical("gev1")
         .set("descr", new String[]{"\u8d28\u91cf\u5e73\u8861", "\u603b\u7d2f\u79ef\u5438\u6e7f\u7387"});
    model.result().numerical("gev1").set("expr", new String[]{"mt.massBalance", "mt.dwcInt", "mt.ntfluxInt"});
    model.result().numerical("gev1")
         .set("descr", new String[]{"\u8d28\u91cf\u5e73\u8861", "\u603b\u7d2f\u79ef\u5438\u6e7f\u7387", "\u603b\u51c0\u5438\u6e7f\u7387"});
    model.result().numerical("gev1")
         .set("expr", new String[]{"mt.massBalance", "mt.dwcInt", "mt.ntfluxInt", "mt.GInt"});
    model.result().numerical("gev1")
         .set("descr", new String[]{"\u8d28\u91cf\u5e73\u8861", "\u603b\u7d2f\u79ef\u5438\u6e7f\u7387", "\u603b\u51c0\u5438\u6e7f\u7387", "\u603b\u8d28\u91cf\u6e90"});
    model.result().numerical("gev1")
         .set("expr", new String[]{"mt.massBalance", "mt.dwcInt", "mt.ntfluxInt", "mt.GInt", "mt.ws1.ntfluxInt"});
    model.result().numerical("gev1")
         .set("descr", new String[]{"\u8d28\u91cf\u5e73\u8861", "\u603b\u7d2f\u79ef\u5438\u6e7f\u7387", "\u603b\u51c0\u5438\u6e7f\u7387", "\u603b\u8d28\u91cf\u6e90", "\u603b\u51c0\u5438\u6e7f\u7387"});
    model.result().numerical("gev1")
         .set("expr", new String[]{"mt.massBalance", "mt.dwcInt", "mt.ntfluxInt", "mt.GInt", "mt.ws1.ntfluxInt", "mt.ifl1.ntfluxInt"});
    model.result().numerical("gev1")
         .set("descr", new String[]{"\u8d28\u91cf\u5e73\u8861", "\u603b\u7d2f\u79ef\u5438\u6e7f\u7387", "\u603b\u51c0\u5438\u6e7f\u7387", "\u603b\u8d28\u91cf\u6e90", "\u603b\u51c0\u5438\u6e7f\u7387", "\u603b\u51c0\u5438\u6e7f\u7387"});
    model.result().numerical("gev1").setIndex("descr", "\u603b\u51c0\u5438\u6e7f\u7387\uff0c\u84b8\u53d1", 4);
    model.result().numerical("gev1").setIndex("expr", "mt.ifl1.ntfluxInt+mt.open1.ntfluxInt", 5);
    model.result().numerical("gev1")
         .setIndex("descr", "\u603b\u51c0\u5438\u6e7f\u7387\uff0c\u5165\u53e3/\u51fa\u53e3", 5);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u8d28\u91cf\u5e73\u8861");
    model.result().numerical("gev1").set("table", "tbl3");
    model.result().numerical("gev1").setResult();
    model.result().create("pg14", "PlotGroup1D");
    model.result("pg14").set("data", "none");
    model.result("pg14").create("tblp1", "Table");
    model.result("pg14").feature("tblp1").set("source", "table");
    model.result("pg14").feature("tblp1").set("table", "tbl3");
    model.result("pg14").feature("tblp1").set("linewidth", "preference");
    model.result("pg14").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg14").run();
    model.result("pg14").run();
    model.result("pg14").label("\u8d28\u91cf\u5e73\u8861");
    model.result("pg14").set("legendlayout", "outside");
    model.result("pg14").set("legendposoutside", "bottom");
    model.result("pg14").set("legendrowcount", 6);
    model.result("pg14").run();
    model.result("pg14").feature("tblp1").set("legend", true);
    model.result("pg14").feature("tblp1").set("linewidth", 2);
    model.result("pg13").run();

    model.title("\u6c34\u7684\u84b8\u53d1\u51b7\u5374");

    model
         .description("\u672c\u6559\u7a0b\u4ecb\u7ecd\u5982\u4f55\u8026\u5408\u4e09\u4e2a\u7269\u7406\u573a\u63a5\u53e3\u4ee5\u5bf9\u84b8\u53d1\u51b7\u5374\u5efa\u6a21\u3002\u9700\u8981\u8003\u8651\u7684\u5f71\u54cd\u6709\u4f20\u70ed\u3001\u6c34\u84b8\u6c14\u4f20\u9012\u4ee5\u53ca\u6d41\u4f53\u6d41\u52a8\u3002\u201c\u6e7f\u8868\u9762\u201d\u7279\u5f81\u7528\u4e8e\u5b9e\u73b0\u6c34\u84b8\u6c14\u7684\u6e90\u9879\uff0c\u5e76\u8ba1\u7b97\u201c\u8fb9\u754c\u70ed\u6e90\u201d\u7279\u5f81\u4e2d\u7684\u84b8\u53d1\u70ed\u6e90\u3002\u201c\u6e7f\u7a7a\u6c14\u201d\u7279\u5f81\u7528\u4e8e\u51c6\u786e\u5b9a\u4e49\u4e0e\u6c34\u5206\u76f8\u5173\u7684\u70ed\u529b\u5b66\u5c5e\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("evaporative_cooling.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
