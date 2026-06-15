/*
 * microstrip_patch_antenna_array_synthesizer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:31 by COMSOL 6.3.0.290. */
public class microstrip_patch_antenna_array_synthesizer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("emw", "ElectromagneticWaves", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/emw", true);

    model.param().set("w_patch", "1.63[mm]");
    model.param().descr("w_patch", "Patch width (y)");
    model.param().set("l_patch", "1.7[mm]");
    model.param().descr("l_patch", "Patch length (x)");
    model.param().set("w_sub", "4[mm]");
    model.param().descr("w_sub", "Substrate width (y)");
    model.param().set("l_sub", "4[mm]");
    model.param().descr("l_sub", "Substrate length (x)");
    model.param().set("w_slot", "0.1[mm]");
    model.param().descr("w_slot", "Slot width (y)");
    model.param().set("l_slot", "0.605[mm]");
    model.param().descr("l_slot", "Slot length (x)");
    model.param().set("thickness_p", "0.1[mm]");
    model.param().descr("thickness_p", "Patch substrate thickness (z)");
    model.param().set("thickness_f", "0.1[mm]");
    model.param().descr("thickness_f", "Feed substrate thickness (z)");
    model.param().set("w_feed", "0.11[mm]");
    model.param().descr("w_feed", "Feed line width (x)");
    model.param().set("l_ext_feed", "0.52[mm]");
    model.param().descr("l_ext_feed", "Extend feed line length (y)");
    model.param().set("f0", "30[GHz]");
    model.param().descr("f0", "Frequency");
    model.param().set("lda0", "c_const/f0");
    model.param().descr("lda0", "Wave length");
    model.param().set("p_sub_eps", "7.8");
    model.param().descr("p_sub_eps", "Patch substrate permittivity");
    model.param().set("f_sub_eps", "7.8");
    model.param().descr("f_sub_eps", "Feed substrate permittivity");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("Patch Substrate");
    model.component("comp1").geom("geom1").feature("blk1")
         .set("size", new String[]{"l_sub", "w_sub", "thickness_p"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"-l_sub/2", "-w_sub/2", "0"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"l_patch", "w_patch", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "thickness_p", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"-l_patch/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("pos", "-w_patch/2", 1);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("blk2").label("Patch");
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "sqrt((0.5*l_sub)^2+(0.5*w_sub)^2)+0.15*lda0");
    model.component("comp1").geom("geom1").feature("sph1").setIndex("layername", "Layer 1", 0);
    model.component("comp1").geom("geom1").feature("sph1").setIndex("layer", "0.1*lda0", 0);
    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").label("Feed Substrate");
    model.component("comp1").geom("geom1").feature("blk3")
         .set("size", new String[]{"l_sub", "w_sub", "thickness_f"});
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new String[]{"-l_sub/2", "-w_sub/2", "0"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("pos", "-thickness_f", 2);
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp1").selection("face").set("blk3", 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"l_slot", "w_slot"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("blk4", "Block");
    model.component("comp1").geom("geom1").feature("blk4").label("Feed");
    model.component("comp1").geom("geom1").feature("blk4").set("size", new String[]{"w_feed", "w_sub/3", "1"});
    model.component("comp1").geom("geom1").feature("blk4").setIndex("size", "thickness_f", 2);
    model.component("comp1").geom("geom1").feature("blk4")
         .set("pos", new String[]{"-w_feed/2", "0", "-thickness_f"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("blk4");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 180);
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("blk5", "Block");
    model.component("comp1").geom("geom1").feature("blk5").label("Feed Extended");
    model.component("comp1").geom("geom1").feature("blk5").set("size", new String[]{"w_feed", "l_ext_feed", "1"});
    model.component("comp1").geom("geom1").feature("blk5").setIndex("size", "thickness_f", 2);
    model.component("comp1").geom("geom1").feature("blk5")
         .set("pos", new String[]{"-w_feed/2", "0", "-thickness_f"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("cosphi", "(x+1e-12)/sqrt(x^2+y^2+1e-12)");
    model.component("comp1").variable("var1").set("sinphi", "(y+1e-12)/sqrt(y^2+x^2+1e-12)");
    model.component("comp1").variable("var1").set("sintheta", "sqrt(x^2+y^2+1e-12)/sqrt(x^2+y^2+z^2)");

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(1, 2, 3, 4, 11, 12, 13, 14);
    model.component("comp1").coordSystem("pml1").set("ScalingType", "Spherical");

    model.component("comp1").physics("emw").create("pec2", "PerfectElectricConductor", 2);
    model.component("comp1").physics("emw").feature("pec2").selection().set(18, 24, 25, 30, 31, 32, 36, 38);
    model.component("comp1").physics("emw").create("ffd1", "FarFieldDomain", 3);
    model.component("comp1").physics("emw").create("lport1", "LumpedPort", 2);
    model.component("comp1").physics("emw").feature("lport1").selection().set(29);

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
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
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
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
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
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
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
    model.component("comp1").material("mat2").label("Patch Substrate");
    model.component("comp1").material("mat2").selection().set(7, 8);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"p_sub_eps"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("Feed Substrate");
    model.component("comp1").material("mat3").selection().set(6, 9, 10);
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"f_sub_eps"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").set(1, 2, 3, 4, 5, 11, 12, 13, 14);

    model.component("comp1").physics("emw").prop("MeshControl").set("SizeControlParameter", "Frequency");
    model.component("comp1").physics("emw").prop("MeshControl").set("PhysicsControlledMeshMaximumFrequency", "f0");

    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("Electric Field (emw)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").label("Multislice");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg1").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("S-parameter (emw)");
    model.result().numerical("gev1").set("data", "dset1");
    model.result().numerical("gev1").set("expr", new String[]{"emw.S11dB"});
    model.result().table().create("tbl1", "Table");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").run();
    model.result().numerical("gev1").setResult();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("Electric Field, Logarithmic (emw)");

    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(18, 24, 25, 30, 31, 32, 36, 38);
    model.component("comp1").measure().selection().geom(2);
    model.component("comp1").measure().selection().set(5, 6, 7, 8, 43, 44, 49, 54);

    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "emw.normE");
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().set(18, 24, 25, 30, 31, 32, 36, 38);
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg2").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg2").feature("surf1").feature("mtrl1").set("family", "chrome");
    model.result("pg2").feature("surf1").set("expr", "1");
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", "emw.normE");
    model.result("pg2").feature("mslc1").create("sel1", "Selection");
    model.result("pg2").feature("mslc1").feature("sel1").selection().set(5);
    model.result("pg2").feature("mslc1").set("colortable", "Prism");
    model.result("pg2").feature("mslc1").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("mslc1").set("colortabletype", "discrete");
    model.result("pg2").feature("mslc1").set("bandcount", 20);
    model.result("pg2").feature("mslc1").create("tran1", "Transparency");
    model.result("pg2").feature("mslc1").feature("tran1").set("transparency", 0.5);
    model.result("pg2").create("surf2", "Surface");
    model.result("pg2").feature("surf2").set("expr", "emw.normE");
    model.result("pg2").feature("surf2").create("sel1", "Selection");
    model.result("pg2").feature("surf2").feature("sel1").selection()
         .set(13, 14, 15, 16, 17, 19, 20, 21, 22, 23, 26, 27, 28, 29, 33, 34, 35, 37, 39, 56, 57, 58, 59, 60, 61);
    model.result("pg2").feature("surf2").set("colortable", "Dipole");
    model.result("pg2").feature("surf2").set("colorscalemode", "logarithmic");
    model.result("pg2").feature("surf2").create("tran1", "Transparency");
    model.result("pg2").feature("surf2").feature("tran1").set("transparency", 0.7);

    model.component("comp1").view().create("view3", "geom1");
    model.component("comp1").view("view3").camera()
         .set("position", new double[]{-6.6444044527800195, -8.859205660612687, 6.666143666143003});
    model.component("comp1").view("view3").set("environmentmap", "envmap_machineshop2");
    model.component("comp1").view("view3").camera().set("zoomanglefull", 31.592493057250977);

    model.result("pg2").set("view", "view3");
    model.result().create("pg3", "PolarGroup");
    model.result("pg3").label("2D Far Field (emw)");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("rp1", "RadiationPattern");
    model.result("pg3").feature("rp1").set("legend", "on");
    model.result("pg3").feature("rp1").set("phidisc", "180");
    model.result("pg3").feature("rp1").set("expr", new String[]{"emw.normEfar"});
    model.result("pg3").feature("rp1").create("exp1", "Export");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("3D Far Field, Gain (emw)");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").set("view", "new");
    model.result("pg4").set("edges", "off");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").create("rp1", "RadiationPattern");
    model.result("pg4").feature("rp1").set("expr", new String[]{"emw.rGaindBEfar"});
    model.result("pg4").feature("rp1").set("colorexpr", new String[]{"emw.normEfar"});
    model.result("pg4").feature("rp1").set("useradiusascolor", true);
    model.result("pg4").feature("rp1").set("directivityexpr", new String[]{"emw.normEfar^2"});
    model.result("pg4").feature("rp1").set("thetadisc", "45");
    model.result("pg4").feature("rp1").set("phidisc", "90");
    model.result("pg4").feature("rp1").set("directivity", "on");
    model.result("pg4").feature("rp1").set("colortable", "RainbowLight");
    model.result("pg4").feature("rp1").create("exp1", "Export");
    model.result("pg4").feature("rp1").feature("exp1").setIndex("expr", "comp1.emw.theta", 0);
    model.result("pg4").feature("rp1").feature("exp1").setIndex("expr", "comp1.emw.phi", 1);
    model.result("pg3").feature("rp1").feature("exp1").setIndex("expr", "comp1.emw.theta", 0);
    model.result("pg3").feature("rp1").feature("exp1").setIndex("expr", "comp1.emw.phi", 1);
    model.result("pg1").run();
    model.result().dataset("dset1").selection().geom("geom1", 3);
    model.result().dataset("dset1").selection().geom("geom1", 3);
    model.result().dataset("dset1").selection().set(6, 7, 8, 9, 10);
    model.result().dataset().create("dset2", "Solution");
    model.result().dataset("dset2").label("Patch");
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().set(25);
    model.result().dataset().create("dset3", "Solution");
    model.result().dataset("dset3").label("Patch Substrate");
    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().set(16, 17, 18, 19, 21, 24, 27, 31, 32, 33, 37, 38, 57, 61);
    model.result().dataset().create("dset4", "Solution");
    model.result().dataset("dset4").label("Feed");
    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().geom("geom1", 2);
    model.result().dataset("dset4").selection().set(30, 36);
    model.result().dataset().create("dset5", "Solution");
    model.result().dataset("dset5").label("Feed Substrate");
    model.result().dataset("dset5").selection().geom("geom1", 2);
    model.result().dataset("dset5").selection().geom("geom1", 2);
    model.result().dataset("dset5").selection().set(13, 14, 15, 18, 20, 24, 27, 31, 32, 33, 37, 38, 57, 60);
    model.result().dataset().create("dset6", "Solution");
    model.result().dataset("dset6").label("Slot with Ground");
    model.result().dataset("dset6").selection().geom("geom1", 2);
    model.result().dataset("dset6").selection().geom("geom1", 2);
    model.result().dataset("dset6").selection().set(18, 24, 31, 32, 38);
    model.result().dataset().create("dset7", "Solution");
    model.result().dataset("dset7").label("Study 1/Solution 1, Patch and Feed Domain");
    model.result().dataset("dset7").selection().geom("geom1", 3);
    model.result().dataset("dset7").selection().geom("geom1", 3);
    model.result().dataset("dset7").selection().set(8, 9, 10);
    model.result().dataset().create("arr1", "Array3D");
    model.result().dataset("arr1").label("Array 3D Patch and Feed");
    model.result().dataset("arr1").set("data", "dset7");
    model.result().dataset("arr1").set("fullsize", new int[]{8, 8, 1});
    model.result().dataset("arr1").set("displmethod", "manual");
    model.result().dataset("arr1").set("displ", new int[]{4, 4, 0});
    model.result().dataset().create("dset8", "Solution");
    model.result().dataset("dset8").label("Study 1/Solution 1, Patch Substrate Domain");
    model.result().dataset("dset8").selection().geom("geom1", 3);
    model.result().dataset("dset8").selection().geom("geom1", 3);
    model.result().dataset("dset8").selection().set(7);
    model.result().dataset().create("arr2", "Array3D");
    model.result().dataset("arr2").label("Array 3D Patch Substrate");
    model.result().dataset("arr2").set("data", "dset8");
    model.result().dataset("arr2").set("fullsize", new int[]{8, 8, 1});
    model.result().dataset("arr2").set("displmethod", "manual");
    model.result().dataset("arr2").set("displ", new int[]{4, 4, 0});
    model.result().dataset().create("dset9", "Solution");
    model.result().dataset("dset9").label("Study 1/Solution 1, Feed Substrate Domain");
    model.result().dataset("dset9").selection().geom("geom1", 3);
    model.result().dataset("dset9").selection().geom("geom1", 3);
    model.result().dataset("dset9").selection().set(6);
    model.result().dataset().create("arr3", "Array3D");
    model.result().dataset("arr3").set("data", "dset9");
    model.result().dataset("arr3").label("Array 3D Feed Substrate");
    model.result().dataset("arr3").set("fullsize", new int[]{8, 8, 1});
    model.result().dataset("arr3").set("displmethod", "manual");
    model.result().dataset("arr3").set("displ", new int[]{4, 4, 0});
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("Input Impedance");
    model.result().numerical("gev2").setIndex("expr", "emw.Zport_1", 0);
    model.result().numerical("gev2").setIndex("unit", "\u03a9", 0);
    model.result().numerical("gev2").setIndex("descr", "Lumped port impedance", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("Input Impedance");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result().numerical().create("max1", "MaxSurface");
    model.result().numerical("max1").selection().set(10, 12, 46, 53);
    model.result().numerical("max1")
         .setIndex("expr", "emw.gaindBEfar+20*log10(sin(8*(emw.k0*0.004*sintheta*cosphi)/2)/(sin((emw.k0*0.004*sintheta*cosphi)/2))*sin(8*(emw.k0*0.004*sintheta*sinphi)/2)/(sin((emw.k0*0.004*sintheta*sinphi)/2)))+0", 0);
    model.result().numerical("max1").setIndex("unit", "", 0);
    model.result().numerical("max1").setIndex("descr", "", 0);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("Surface Maximum 1");
    model.result().numerical("max1").set("table", "tbl3");
    model.result().numerical("max1").setResult();
    model.result("pg1").run();
    model.result("pg1").set("view", "new");
    model.result("pg1").run();

    model.view("view4").label("View Single Antenna");
    model.view("view4").set("showgrid", false);

    model.result("pg1").run();
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("titlecolor", "custom");
    model.result("pg1").set("customtitlecolor", new double[]{0, 0.3333333432674408, 0.5882353186607361});
    model.result("pg1").set("title", "Single Antenna, Electric field norm (V/m), Exploded View");
    model.result("pg1").run();
    model.result("pg1").feature().remove("mslc1");
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("Patch Surface");
    model.result("pg1").feature("surf1").set("data", "dset2");
    model.result("pg1").feature("surf1").set("colortable", "ThermalDark");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").feature("surf1").set("colorlegend", false);
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "custom");
    model.result("pg1").feature("surf1")
         .set("customcolor", new double[]{0.9921568632125854, 0.7254902124404907, 0.07450980693101883});
    model.result("pg1").feature("surf1").set("coloring", "colortable");
    model.result("pg1").feature("surf1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature("def1").set("expr", new String[]{"0", "0", "l_patch"});
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg1").run();
    model.result("pg1").create("surf2", "Surface");
    model.result("pg1").feature("surf2").label("Patch Substrate Surface");
    model.result("pg1").feature("surf2").set("data", "dset3");
    model.result("pg1").feature("surf2").set("colortable", "ThermalDark");
    model.result("pg1").feature("surf2").set("colorlegend", false);
    model.result("pg1").feature("surf2").set("coloring", "uniform");
    model.result("pg1").feature("surf2").set("color", "custom");
    model.result("pg1").feature("surf2")
         .set("customcolor", new double[]{0.5176470875740051, 0.7607843279838562, 0.9176470637321472});
    model.result("pg1").feature("surf2").set("coloring", "colortable");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature().copy("def1", "pg1/surf1/def1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("surf3", "Surface");
    model.result("pg1").feature("surf3").label("Feed Surface");
    model.result("pg1").feature("surf3").set("data", "dset4");
    model.result("pg1").feature("surf3").set("colortable", "ThermalDark");
    model.result("pg1").feature("surf3").set("colorlegend", false);
    model.result("pg1").feature("surf3").set("colortabletrans", "reverse");
    model.result("pg1").feature("surf3").set("coloring", "uniform");
    model.result("pg1").feature("surf3").set("color", "custom");
    model.result("pg1").feature("surf3")
         .set("customcolor", new double[]{0.9921568632125854, 0.7254902124404907, 0.07450980693101883});
    model.result("pg1").feature("surf3").set("coloring", "colortable");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf3").feature().copy("def1", "pg1/surf1/def1");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").feature("def1").set("expr", new String[]{"0", "0", "-l_patch"});
    model.result("pg1").run();
    model.result("pg1").create("surf4", "Surface");
    model.result("pg1").feature("surf4").label("Feed Substrate Surface");
    model.result("pg1").feature("surf4").set("data", "dset5");
    model.result("pg1").feature("surf4").set("colortable", "ThermalDark");
    model.result("pg1").feature("surf4").set("colorlegend", false);
    model.result("pg1").feature("surf4").set("coloring", "uniform");
    model.result("pg1").feature("surf4").set("color", "custom");
    model.result("pg1").feature("surf4")
         .set("customcolor", new double[]{0.7529411911964417, 0.7529411911964417, 0.7529411911964417});
    model.result("pg1").feature("surf4").set("coloring", "colortable");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf4").feature().copy("def1", "pg1/surf1/def1");
    model.result("pg1").run();
    model.result("pg1").feature("surf4").feature("def1").set("expr", new String[]{"0", "0", "-l_patch"});
    model.result("pg1").run();
    model.result("pg1").create("surf5", "Surface");
    model.result("pg1").feature("surf5").label("Ground Plane Surface");
    model.result("pg1").feature("surf5").set("data", "dset6");
    model.result("pg1").feature("surf5").set("colortable", "ThermalDark");
    model.result("pg1").feature("surf5").set("colorlegend", false);
    model.result("pg1").feature("surf5").set("coloring", "uniform");
    model.result("pg1").feature("surf5").set("color", "custom");
    model.result("pg1").feature("surf5")
         .set("customcolor", new double[]{0.9921568632125854, 0.7254902124404907, 0.07450980693101883});
    model.result("pg1").feature("surf5").set("coloring", "colortable");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result().remove("pg3");
    model.result().remove("pg4");
    model.result().create("pg2", "PolarGroup");
    model.result("pg2").run();
    model.result("pg2").create("rp1", "RadiationPattern");
    model.result("pg2").feature("rp1").set("markerpos", "datapoints");
    model.result("pg2").feature("rp1").set("linewidth", "preference");
    model.result("pg2").feature("rp1").set("phidisc", 180);
    model.result("pg2").run();
    model.result("pg2").label("2D Far Field yz (emw)");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("titlecolor", "custom");
    model.result("pg2").set("customtitlecolor", new double[]{0, 0.3333333432674408, 0.5882353186607361});
    model.result("pg2").set("title", "Normalized Far-field Pattern on yz-plane");
    model.result("pg2").set("showlegends", false);
    model.result("pg2").set("manualgrid", true);
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("tspacing", 10);
    model.result("pg2").set("rspacing", 5);
    model.result("pg2").set("rmin", -50);
    model.result("pg2").set("rmax", 0.5);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("rp1")
         .set("expr", "emw.gaindBEfar+20*log10(sin(8*(emw.k0*0.004*sintheta*cosphi)/2)/(sin((emw.k0*0.004*sintheta*cosphi)/2))*sin(8*(emw.k0*0.004*sintheta*sinphi)/2)/(sin((emw.k0*0.004*sintheta*sinphi)/2)))-41.626");
    model.result("pg2").feature("rp1").set("normal", new int[]{1, 0, 0});
    model.result("pg2").feature("rp1").set("refdir", new int[]{0, 1, 0});
    model.result("pg2").feature("rp1").set("linecolor", "red");
    model.result("pg2").feature("rp1").set("linewidth", 2);
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("3D Far Field, Gain (emw)");
    model.result("pg3").create("rp1", "RadiationPattern");
    model.result("pg3").feature("rp1")
         .set("expr", "emw.gaindBEfar+20*log10(sin(8*(emw.k0*0.004*sintheta*cosphi)/2)/(sin((emw.k0*0.004*sintheta*cosphi)/2))*sin(8*(emw.k0*0.004*sintheta*sinphi)/2)/(sin((emw.k0*0.004*sintheta*sinphi)/2)))-41.626");
    model.result("pg3").feature("rp1").set("thresholdactive", true);
    model.result("pg3").feature("rp1").set("threshold", -45);
    model.result("pg3").feature("rp1").set("thetadisc", 180);
    model.result("pg3").feature("rp1").set("phidisc", 180);
    model.result("pg3").feature("rp1").set("directivity", true);
    model.result("pg3").feature("rp1")
         .set("directivityexpr", "(emw.normEfar*(sin(8*(emw.k0*0.004*sintheta*cosphi)/2)/(8*sin((emw.k0*0.004*sintheta*cosphi)/2))*sin(8*(emw.k0*0.004*sintheta*sinphi)/2)/(8*sin((emw.k0*0.004*sintheta*sinphi)/2))))^2");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("titlecolor", "custom");
    model.result("pg3").set("customtitlecolor", new double[]{0, 0.3333333432674408, 0.5882353186607361});
    model.result("pg3").set("title", "Normalized 3D Far-field Pattern");

    model.view("view5").label("View Far Field");
    model.view("view5").set("showgrid", false);

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("Virtual Array View");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("titlecolor", "custom");
    model.result("pg4").set("customtitlecolor", new double[]{0, 0.3333333432674408, 0.5882353186607361});
    model.result("pg4").set("title", "Virtual Array View");
    model.result("pg4").set("edges", false);
    model.result("pg4").set("view", "new");
    model.result("pg4").run();

    model.view("view6").label("View Virtual Array");
    model.view("view6").set("showgrid", false);

    model.result("pg4").run();
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("data", "arr1");
    model.result("pg4").feature("surf1").set("expr", "1");
    model.result("pg4").feature("surf1").set("colortable", "Thermal");
    model.result("pg4").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg4").feature("surf1").set("colorlegend", false);
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "custom");
    model.result("pg4").feature("surf1")
         .set("customcolor", new double[]{0.9921568632125854, 0.7254902124404907, 0.07450980693101883});
    model.result("pg4").feature().duplicate("surf2", "surf1");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf3", "surf1");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg4").feature("surf1").feature("mtrl1").set("family", "gold");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").set("data", "arr2");
    model.result("pg4").feature("surf2")
         .set("customcolor", new double[]{0.21176470816135406, 0.5490196347236633, 0.7960784435272217});
    model.result("pg4").run();
    model.result("pg4").feature("surf3").set("data", "arr3");
    model.result("pg4").feature("surf3")
         .set("customcolor", new double[]{0.7529411911964417, 0.7529411911964417, 0.7529411911964417});
    model.result("pg4").run();
    model.result("pg4").set("showlegends", false);
    model.result("pg4").run();
    model.result().create("pg5", "PolarGroup");
    model.result("pg5").run();
    model.result("pg5").label("2D Far Field xz (emw)");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("titlecolor", "custom");
    model.result("pg5").set("customtitlecolor", new double[]{0, 0.3333333432674408, 0.5882353186607361});
    model.result("pg5").set("title", "Normalized Far-field Pattern on xz-plane");
    model.result("pg5").set("manualgrid", true);
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("tspacing", 10);
    model.result("pg5").set("rspacing", 5);
    model.result("pg5").set("rmin", -50);
    model.result("pg5").set("rmax", 0.5);
    model.result("pg5").set("showlegends", false);
    model.result("pg5").create("rp1", "RadiationPattern");
    model.result("pg5").feature("rp1").set("markerpos", "datapoints");
    model.result("pg5").feature("rp1").set("linewidth", "preference");
    model.result("pg5").feature("rp1")
         .set("expr", "emw.gaindBEfar+20*log10(sin(8*(emw.k0*0.004*sintheta*cosphi)/2)/(sin((emw.k0*0.004*sintheta*cosphi)/2))*sin(8*(emw.k0*0.004*sintheta*sinphi)/2)/(sin((emw.k0*0.004*sintheta*sinphi)/2)))-41.626");
    model.result("pg5").feature("rp1").set("phidisc", 180);
    model.result("pg5").feature("rp1").set("normal", new int[]{0, -1, 0});
    model.result("pg5").feature("rp1").set("linecolor", "green");
    model.result("pg5").feature("rp1").set("linewidth", 2);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("2D Far Field xy (emw)");
    model.result("pg6").set("title", "Normalized Far-field Pattern on xy-plane");
    model.result("pg6").run();
    model.result("pg6").feature("rp1").set("linecolor", "blue");
    model.result("pg6").feature("rp1").set("normal", new int[]{0, 0, 1});
    model.result("pg4").run();

    model.title("\u7f1d\u9699\u8026\u5408\u5fae\u5e26\u8d34\u7247\u5929\u7ebf\u9635\u5217\u5408\u6210\u5668");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u53c2\u6570\u5316\u51e0\u4f55\n\u2022 \u5c06\u6750\u6599\u7684\u5916\u89c2\u3001\u989c\u8272\u548c\u7eb9\u7406\u53ef\u89c6\u5316\n\u2022 \u5728\u540c\u4e00\u7a97\u53e3\u4e2d\u751f\u6210\u591a\u4e2a\u7ed8\u56fe\u4ee5\u53ef\u89c6\u5316\u7ed3\u679c\n\u2022 \u7528\u4e8e\u901a\u8fc7\u590d\u9009\u6846\u4f7f\u7528\u4e0d\u540c\u7684\u89c6\u56fe\u6765\u53ef\u89c6\u5316\u7ed3\u679c\u7684\u9009\u9879\n\n\u5fae\u5e26\u8d34\u7247\u5929\u7ebf\u9635\u5217\u5728\u8bb8\u591a\u884c\u4e1a\u4e2d\u7528\u4f5c\u96f7\u8fbe\u548c\u5c04\u9891\u4fe1\u53f7\u7684\u6536\u53d1\u5668\uff0c\u6210\u4e3a 5G \u79fb\u52a8\u7f51\u7edc\u7cfb\u7edf\u7684\u9996\u9009\u5de5\u5177\u3002\n\n\u8be5 App \u6a21\u62df\u4e00\u4e2a\u5728\u591a\u5c42\u4f4e\u6e29\u5171\u70e7\u9676\u74f7 (LTCC) \u57fa\u677f\u4e0a\u5236\u4f5c\u7684\u5355\u7f1d\u8026\u5408\u5fae\u5e26\u8d34\u7247\u5929\u7ebf\u3002\u60a8\u53ef\u4ee5\u5728\u5176\u4e2d\u6a21\u62df\u5929\u7ebf\u9635\u5217\u7684\u8fdc\u573a\u8f90\u5c04\u65b9\u5411\u56fe\u53ca\u5176\u65b9\u5411\u6027\u3002\u901a\u8fc7\u5c06\u9635\u5217\u56e0\u5b50\u4e58\u4ee5\u5355\u5929\u7ebf\u8f90\u5c04\u65b9\u5411\u56fe\uff0c\u53ef\u4ee5\u8fd1\u4f3c\u8ba1\u7b97\u8fdc\u573a\u8f90\u5c04\u65b9\u5411\u56fe\uff0c\u4ece\u800c\u5728\u65e0\u9700\u6a21\u62df\u590d\u6742\u7684\u5168\u9635\u5217\u6a21\u578b\u7684\u60c5\u51b5\u4e0b\u6267\u884c\u6709\u6548\u7684\u8fdc\u573a\u5206\u6790\u3002\n\n\u6b64\u5916\uff0c\u60a8\u8fd8\u53ef\u4ee5\u8bc4\u4f30 5G \u79fb\u52a8\u7f51\u7edc\uff08\u9ed8\u8ba4\u8f93\u5165\u9891\u7387\u4e3a 30\u00a0GHz\uff09\u7684\u76f8\u63a7\u5929\u7ebf\u9635\u5217\u539f\u578b\uff0c\u901a\u8fc7\u6539\u53d8\u5929\u7ebf\u7684\u51e0\u4f55\u5c3a\u5bf8\u548c\u57fa\u677f\u6750\u6599\u7b49\u5c5e\u6027\u53ef\u4ee5\u5b9e\u73b0\u8fd9\u4e00\u70b9\u3002");

    model.label("microstrip_patch_antenna_array_synthesizer.mph");

    model.result("pg4").run();
    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("filename", "user:///microstrip_patch_antenna_array_synthesizer");
    model.result().report("rpt1").set("imagesize", "xlarge");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1")
         .set("summary", "\u672c App \u6a21\u62df\u4e00\u4e2a\u5728\u591a\u5c42\u4f4e\u6e29\u5171\u70e7\u9676\u74f7 (LTCC) \u57fa\u677f\u4e0a\u5236\u4f5c\u7684\u5355\u7f1d\u8026\u5408\u5fae\u5e26\u8d34\u7247\u5929\u7ebf\uff0c\u7ed3\u679c\u5305\u62ec\u5929\u7ebf\u9635\u5217\u7684\u8fdc\u573a\u8f90\u5c04\u65b9\u5411\u56fe\u53ca\u5176\u65b9\u5411\u6027\u3002\u901a\u8fc7\u5c06\u9635\u5217\u56e0\u5b50\u4e58\u4ee5\u5355\u5929\u7ebf\u8f90\u5c04\u65b9\u5411\u56fe\uff0c\u53ef\u4ee5\u8fd1\u4f3c\u8ba1\u7b97\u8fdc\u573a\u8f90\u5c04\u65b9\u5411\u56fe\uff0c\u4ece\u800c\u5728\u65e0\u9700\u6a21\u62df\u590d\u6742\u7684\u5168\u9635\u5217\u6a21\u578b\u7684\u60c5\u51b5\u4e0b\u6267\u884c\u6709\u6548\u7684\u8fdc\u573a\u5206\u6790\u3002\u4f7f\u7528\u9ed8\u8ba4\u8f93\u5165\u9891\u7387\uff0830\u00a0GHz\uff09\uff0c\u8fd8\u53ef\u4ee5\u5feb\u901f\u8bc4\u4f30 5G \u79fb\u52a8\u7f51\u7edc\u7684\u76f8\u63a7\u5929\u7ebf\u9635\u5217\u539f\u578b\u3002");
    model.result().report("rpt1").feature("tp1").set("includeacknowledgment", false);
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u5168\u5c40\u5b9a\u4e49");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").label("\u5173\u4e8e\u8f6f\u4ef6");
    model.result().report("rpt1").feature("sec1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").feature("sec1").label("\u53c2\u6570 1");
    model.result().report("rpt1").feature("sec1").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec1").feature("sec1").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("\u7ec4\u4ef6 1");
    model.result().report("rpt1").feature("sec2").feature().create("comp1", "ModelNode");
    model.result().report("rpt1").feature("sec2").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec1").label("\u51e0\u4f55 1");
    model.result().report("rpt1").feature("sec2").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec2").feature("sec1").feature().create("geom1", "Geometry");
    model.result().report("rpt1").feature("sec2").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec2").label("\u6750\u6599");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec1").label("\u8d34\u7247\u57fa\u677f");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec1").set("source", "firstchild");
    model.result().report("rpt1").feature("sec2").feature("sec2").feature("sec1").feature()
         .create("mat1", "Material");
    model.result().report("rpt1").feature("sec2").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec2").feature("sec3").label("\u7f51\u683c 1");
    model.result().report("rpt1").feature("sec2").feature("sec3").set("source", "firstchild");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature().create("mesh1", "Mesh");
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("mesh1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("mesh1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("mesh1").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("mesh1").setIndex("children", false, 3, 1);
    model.result().report("rpt1").feature("sec2").feature("sec3").feature("mesh1").setIndex("children", false, 4, 1);
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("\u7ed3\u679c");
    model.result().report("rpt1").feature("sec3").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").label("\u6d3e\u751f\u503c");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1")
         .label("S \u53c2\u6570\uff0cS11dB (emw)");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec1").feature().create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").label("\u8f93\u5165\u963b\u6297");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature().create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec2").feature("mtbl1")
         .set("noderef", "tbl2");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3").label("\u65b9\u5411\u6027");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3").feature().create("mtbl1", "Table");
    model.result().report("rpt1").feature("sec3").feature("sec1").feature("sec3").feature("mtbl1")
         .set("noderef", "rp1");
    model.result().report("rpt1").feature("sec3").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec2").label("\u7ed8\u56fe\u7ec4");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec1").label("\u7535\u573a (emw)");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec1").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec2")
         .label("\u4e8c\u7ef4\u8fdc\u573a xy (emw)");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec2").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec2").feature("pg1")
         .set("noderef", "pg6");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec3")
         .label("\u4e8c\u7ef4\u8f90\u5c04\u6a21\u5f0f yz (emw)");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec3").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec3").feature("pg1")
         .set("noderef", "pg2");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature().create("sec4", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec4")
         .label("\u4e8c\u7ef4\u8fdc\u573a xz (emw)");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec4").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec4").feature("pg1")
         .set("noderef", "pg5");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature().create("sec5", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec5")
         .label("\u4e09\u7ef4\u8fdc\u573a (emw)");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec5").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec5").feature("pg1")
         .set("noderef", "pg3");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature().create("sec6", "Section");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec6")
         .label("\u865a\u62df\u9635\u5217\u89c6\u56fe");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec6").feature()
         .create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("sec2").feature("sec6").feature("pg1")
         .set("noderef", "pg4");

    model.title("\u7f1d\u9699\u8026\u5408\u5fae\u5e26\u8d34\u7247\u5929\u7ebf\u9635\u5217\u5408\u6210\u5668");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u53c2\u6570\u5316\u51e0\u4f55\n\u2022 \u5c06\u6750\u6599\u7684\u5916\u89c2\u3001\u989c\u8272\u548c\u7eb9\u7406\u53ef\u89c6\u5316\n\u2022 \u5728\u540c\u4e00\u7a97\u53e3\u4e2d\u751f\u6210\u591a\u4e2a\u7ed8\u56fe\u4ee5\u53ef\u89c6\u5316\u7ed3\u679c\n\u2022 \u7528\u4e8e\u901a\u8fc7\u590d\u9009\u6846\u4f7f\u7528\u4e0d\u540c\u7684\u89c6\u56fe\u6765\u53ef\u89c6\u5316\u7ed3\u679c\u7684\u9009\u9879\n\n\u5fae\u5e26\u8d34\u7247\u5929\u7ebf\u9635\u5217\u5728\u8bb8\u591a\u884c\u4e1a\u4e2d\u7528\u4f5c\u96f7\u8fbe\u548c\u5c04\u9891\u4fe1\u53f7\u7684\u6536\u53d1\u5668\uff0c\u6210\u4e3a 5G \u79fb\u52a8\u7f51\u7edc\u7cfb\u7edf\u7684\u9996\u9009\u5de5\u5177\u3002\n\n\u8be5 App \u6a21\u62df\u4e00\u4e2a\u5728\u591a\u5c42\u4f4e\u6e29\u5171\u70e7\u9676\u74f7 (LTCC) \u57fa\u677f\u4e0a\u5236\u4f5c\u7684\u5355\u7f1d\u8026\u5408\u5fae\u5e26\u8d34\u7247\u5929\u7ebf\u3002\u60a8\u53ef\u4ee5\u5728\u5176\u4e2d\u6a21\u62df\u5929\u7ebf\u9635\u5217\u7684\u8fdc\u573a\u8f90\u5c04\u65b9\u5411\u56fe\u53ca\u5176\u65b9\u5411\u6027\u3002\u901a\u8fc7\u5c06\u9635\u5217\u56e0\u5b50\u4e58\u4ee5\u5355\u5929\u7ebf\u8f90\u5c04\u65b9\u5411\u56fe\uff0c\u53ef\u4ee5\u8fd1\u4f3c\u8ba1\u7b97\u8fdc\u573a\u8f90\u5c04\u65b9\u5411\u56fe\uff0c\u4ece\u800c\u5728\u65e0\u9700\u6a21\u62df\u590d\u6742\u7684\u5168\u9635\u5217\u6a21\u578b\u7684\u60c5\u51b5\u4e0b\u6267\u884c\u6709\u6548\u7684\u8fdc\u573a\u5206\u6790\u3002\n\n\u6b64\u5916\uff0c\u60a8\u8fd8\u53ef\u4ee5\u8bc4\u4f30 5G \u79fb\u52a8\u7f51\u7edc\uff08\u9ed8\u8ba4\u8f93\u5165\u9891\u7387\u4e3a 30\u00a0GHz\uff09\u7684\u76f8\u63a7\u5929\u7ebf\u9635\u5217\u539f\u578b\uff0c\u901a\u8fc7\u6539\u53d8\u5929\u7ebf\u7684\u51e0\u4f55\u5c3a\u5bf8\u548c\u57fa\u677f\u6750\u6599\u7b49\u5c5e\u6027\u53ef\u4ee5\u5b9e\u73b0\u8fd9\u4e00\u70b9\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("microstrip_patch_antenna_array_synthesizer.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
