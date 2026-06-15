/*
 * transient_arc_3d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:49 by COMSOL 6.3.0.290. */
public class transient_arc_3d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electric_Discharge_Module\\Arc_Discharges");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mef", "ElectricInductionCurrents", "geom1");
    model.component("comp1").physics("mef").prop("ShapeProperty").set("order_magneticvectorpotential", "1");
    model.component("comp1").physics("mef").prop("ShapeProperty").set("order_electricpotential", "1");
    model.component("comp1").physics("mef").create("alcf1", "ElectromagneticModelFluid");
    model.component("comp1").physics("mef").feature("alcf1").selection().all();
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics("ht").feature("fluid1").set("u_src", new String[]{"fromCommonDef"});
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");

    model.component("comp1").multiphysics().create("phs1", "EquilibriumDischargeHeatSource", 3);
    model.component("comp1").multiphysics("phs1").set("phsEMHeat_physics", "mef");
    model.component("comp1").multiphysics("phs1").set("phsHeat_physics", "ht");
    model.component("comp1").multiphysics("phs1").selection().all();
    model.component("comp1").multiphysics().create("mhd1", "Magnetohydrodynamics", 3);
    model.component("comp1").multiphysics("mhd1").set("EMForce_physics", "mef");
    model.component("comp1").multiphysics("mhd1").set("FluidFlow_physics", "spf");
    model.component("comp1").multiphysics("mhd1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mef", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/phs1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/mhd1", true);

    model.param().set("L", "200[mm]");
    model.param().descr("L", "Copper rail length");
    model.param().set("L_wire", "20[mm]");
    model.param().descr("L_wire", "Ignition wire position");
    model.param().set("r0", "10[mm]");
    model.param().descr("r0", "Electrode radius");
    model.param().set("gap", "10[mm]");
    model.param().descr("gap", "Rail gap");
    model.param().set("d0", "5[mm]");
    model.param().descr("d0", "Insulator thickness");
    model.param().set("ts_ms", "0.4");
    model.param().descr("ts_ms", "Simulation start time in ms");
    model.param().set("t", "ts_ms[ms]");
    model.param().descr("t", "Time");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("funcname", "ic");
    model.func("int1")
         .set("table", new String[][]{{"0", "0"}, 
         {"2.156e-01", "9.511e+01"}, 
         {"4.370e-01", "2.196e+02"}, 
         {"9.160e-01", "4.368e+02"}, 
         {"1.222e+00", "5.483e+02"}, 
         {"1.539e+00", "6.470e+02"}, 
         {"1.917e+00", "7.398e+02"}, 
         {"2.169e+00", "7.903e+02"}, 
         {"2.434e+00", "8.174e+02"}, 
         {"2.632e+00", "8.316e+02"}, 
         {"2.975e+00", "8.164e+02"}, 
         {"3.570e+00", "7.673e+02"}, 
         {"4.942e+00", "6.656e+02"}, 
         {"5.742e+00", "6.084e+02"}});
    model.func("int1").set("interp", "piecewisecubic");
    model.func("int1").set("extrap", "linear");
    model.func("int1").setIndex("fununit", "A", 0);
    model.func("int1").setIndex("argunit", "ms", 0);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"r0*3", "(r0+d0+gap/2)"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"0", "-(r0+d0+gap/2)"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("layer", "d0", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "r0");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1")
         .set("pos", new String[]{"0", "-(r0+gap/2)"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").selection("input").set("c1", "r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mir1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mir1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "L", 0);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", "1[m]");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").setIndex("layer", "0.2[m]", 0);
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev1").set("angle2", 180);
    model.component("comp1").geom("geom1").feature("rev1").set("axis", new int[]{1, 0});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");
    model.component("comp1").coordSystem("ie1").selection().set(1, 2);
    model.component("comp1").coordSystem("ie1").set("ScalingType", "Spherical");

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").set(1, 2, 3);

    model.component("comp1").physics("mef").create("al1", "AmperesLaw", 3);
    model.component("comp1").physics("mef").feature("al1").selection().set(1, 2, 3, 4, 9);
    model.component("comp1").physics("mef").create("mi2", "MagneticInsulation", 2);
    model.component("comp1").physics("mef").feature("mi2").selection().set(16, 19, 22, 25);
    model.component("comp1").physics("mef").feature("mi2").create("term1", "Terminal", 2);
    model.component("comp1").physics("mef").feature("mi2").feature("term1").selection().set(25);
    model.component("comp1").physics("mef").feature("mi2").feature("term1").set("I0", "ic(t)/2");
    model.component("comp1").physics("mef").feature("mi2").create("ein1", "ElectricInsulation", 2);
    model.component("comp1").physics("mef").feature("mi2").feature("ein1").selection().set(19, 22);
    model.component("comp1").physics("mef").create("pmc1", "PerfectMagneticConductor", 2);
    model.component("comp1").physics("mef").feature("pmc1").selection()
         .set(1, 2, 6, 9, 11, 14, 17, 20, 23, 26, 29, 32);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").label("\u663e\u5f0f 1");
    model.component("comp1").selection("sel1").set(1, 2, 6, 9, 11, 14, 17, 20, 23, 26, 29, 32);

    model.component("comp1").physics("mef").feature("pmc1").selection().named("sel1");
    model.component("comp1").physics("ht").selection().set(6, 7);
    model.component("comp1").physics("ht").create("ofl1", "ConvectiveOutflow", 2);
    model.component("comp1").physics("ht").feature("ofl1").selection().set(19, 22, 38, 39, 44, 45);

    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").label("\u663e\u5f0f 2");
    model.component("comp1").selection("sel2").set(19, 22, 38, 39, 44, 45);

    model.component("comp1").physics("ht").feature("ofl1").selection().named("sel2");
    model.component("comp1").physics("ht").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ht").feature("sym1").selection().set(20, 23);

    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").label("\u663e\u5f0f 3");
    model.component("comp1").selection("sel3").set(20, 23);

    model.component("comp1").physics("ht").feature("sym1").selection().named("sel3");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1")
         .set("T0", "15e3[K]*exp(-((x-L_wire)/1.5[mm])^2-((y-0[mm])/1[mm])^2)+293.15[K]");
    model.component("comp1").variable("var1").descr("T0", "Initial temperature");

    model.component("comp1").physics("ht").create("init2", "init", 3);
    model.component("comp1").physics("ht").feature("init2").selection().all();
    model.component("comp1").physics("ht").feature("init2").set("Tinit", "T0");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().set(21, 27, 35, 36);

    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").label("\u663e\u5f0f 4");
    model.component("comp1").selection("sel4").set(21, 27, 35, 36);

    model.component("comp1").physics("ht").feature("hf1").selection().named("sel4");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", 6);
    model.component("comp1").physics("spf").selection().set(6, 7);
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("sel2");
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().named("sel3");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().set(5, 8);
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(4, 9);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("int3", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("int4", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup("def").func().create("int5", "Interpolation");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RadiationHeatTransfer", "RadiationHeatTransfer", "Radiation heat transfer");
    model.component("comp1").material("mat3").propertyGroup("RadiationHeatTransfer").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat3").label("Air (1[atm])");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("funcname", "rho");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"250", "0.9946550000000003"}, 
         {"500", "0.70208"}, 
         {"1.0000E+03", "3.5104E-01"}, 
         {"1.5000E+03", "2.3404E-01"}, 
         {"2.0000E+03", "1.7552E-01"}, 
         {"2.5000E+03", "1.4000E-01"}, 
         {"3.0000E+03", "1.1446E-01"}, 
         {"3.5000E+03", "9.3032E-02"}, 
         {"4.0000E+03", "7.6104E-02"}, 
         {"4.5000E+03", "6.6142E-02"}, 
         {"5.0000E+03", "5.8385E-02"}, 
         {"5.5000E+03", "5.1562E-02"}, 
         {"6.0000E+03", "4.4803E-02"}, 
         {"6.5000E+03", "3.7916E-02"}, 
         {"7.0000E+03", "3.1512E-02"}, 
         {"7.5000E+03", "2.6550E-02"}, 
         {"8.0000E+03", "2.3274E-02"}, 
         {"8.5000E+03", "2.1144E-02"}, 
         {"9.0000E+03", "1.9599E-02"}, 
         {"9.5000E+03", "1.8331E-02"}, 
         {"1.0000E+04", "1.7200E-02"}, 
         {"1.0500E+04", "1.6139E-02"}, 
         {"1.1000E+04", "1.5111E-02"}, 
         {"1.1500E+04", "1.4098E-02"}, 
         {"1.2000E+04", "1.3091E-02"}, 
         {"1.2500E+04", "1.2092E-02"}, 
         {"1.3000E+04", "1.1110E-02"}, 
         {"1.3500E+04", "1.0166E-02"}, 
         {"1.4000E+04", "9.2765E-03"}, 
         {"1.4500E+04", "8.4660E-03"}, 
         {"1.5100E+04", "7.6169E-03"}, 
         {"1.5600E+04", "7.0184E-03"}, 
         {"1.6100E+04", "6.5152E-03"}, 
         {"1.6600E+04", "6.0968E-03"}, 
         {"1.7100E+04", "5.7498E-03"}, 
         {"1.7600E+04", "5.4602E-03"}, 
         {"1.8100E+04", "5.2157E-03"}, 
         {"1.8600E+04", "5.0072E-03"}, 
         {"1.9100E+04", "4.8241E-03"}, 
         {"1.9600E+04", "4.6677E-03"}, 
         {"2.0100E+04", "4.5204E-03"}, 
         {"2.0600E+04", "4.3854E-03"}, 
         {"2.1100E+04", "4.2597E-03"}, 
         {"2.1600E+04", "4.1411E-03"}, 
         {"2.2100E+04", "4.0277E-03"}, 
         {"2.2600E+04", "3.9184E-03"}, 
         {"2.3100E+04", "3.8142E-03"}, 
         {"2.3600E+04", "3.7061E-03"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("interp", "piecewisecubic");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat3").propertyGroup("def").func("int1")
         .set("fununit", new String[]{"kg/m^3"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat3").propertyGroup("def").func("int2").set("funcname", "cp");
    model.component("comp1").material("mat3").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"250", "992.0499999999996"}, 
         {"500", "1047.3"}, 
         {"1.0000E+03", "1.1350E+03"}, 
         {"1.5000E+03", "1.2185E+03"}, 
         {"2.0000E+03", "1.3284E+03"}, 
         {"2.5000E+03", "1.6204E+03"}, 
         {"3.0000E+03", "2.5694E+03"}, 
         {"3.5000E+03", "3.8519E+03"}, 
         {"4.0000E+03", "3.2636E+03"}, 
         {"4.5000E+03", "2.3532E+03"}, 
         {"5.0000E+03", "2.7245E+03"}, 
         {"5.5000E+03", "4.2948E+03"}, 
         {"6.0000E+03", "7.2121E+03"}, 
         {"6.5000E+03", "1.1153E+04"}, 
         {"7.0000E+03", "1.4026E+04"}, 
         {"7.5000E+03", "1.2842E+04"}, 
         {"8.0000E+03", "8.8706E+03"}, 
         {"8.5000E+03", "5.8073E+03"}, 
         {"9.0000E+03", "4.4973E+03"}, 
         {"9.5000E+03", "4.3527E+03"}, 
         {"1.0000E+04", "4.8672E+03"}, 
         {"1.0500E+04", "5.8662E+03"}, 
         {"1.1000E+04", "7.2153E+03"}, 
         {"1.1500E+04", "8.9527E+03"}, 
         {"1.2000E+04", "1.0925E+04"}, 
         {"1.2500E+04", "1.3448E+04"}, 
         {"1.3100E+04", "1.6405E+04"}, 
         {"1.3600E+04", "1.8742E+04"}, 
         {"1.4200E+04", "2.0835E+04"}, 
         {"1.4800E+04", "2.1700E+04"}, 
         {"1.5300E+04", "2.1260E+04"}, 
         {"1.5800E+04", "1.9857E+04"}, 
         {"1.6300E+04", "1.7790E+04"}, 
         {"1.6800E+04", "1.5439E+04"}, 
         {"1.7300E+04", "1.3132E+04"}, 
         {"1.7800E+04", "1.1080E+04"}, 
         {"1.8300E+04", "9.3797E+03"}, 
         {"1.8800E+04", "8.0422E+03"}, 
         {"1.9300E+04", "7.0523E+03"}, 
         {"1.9900E+04", "6.2517E+03"}, 
         {"2.0400E+04", "5.8859E+03"}, 
         {"2.0900E+04", "5.7630E+03"}, 
         {"2.1400E+04", "5.8794E+03"}, 
         {"2.1900E+04", "6.2451E+03"}, 
         {"2.2500E+04", "7.0426E+03"}, 
         {"2.3100E+04", "8.2694E+03"}, 
         {"2.3600E+04", "9.6487E+03"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("int2").set("interp", "piecewisecubic");
    model.component("comp1").material("mat3").propertyGroup("def").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat3").propertyGroup("def").func("int2")
         .set("fununit", new String[]{"J/kg/K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int3").label("Interpolation 3");
    model.component("comp1").material("mat3").propertyGroup("def").func("int3").set("funcname", "mu");
    model.component("comp1").material("mat3").propertyGroup("def").func("int3")
         .set("table", new String[][]{{"250", "1.7569999999999996E-5"}, 
         {"500", "2.7055E-5"}, 
         {"1.0000E+03", "4.4457E-05"}, 
         {"1.5000E+03", "5.9515E-05"}, 
         {"2.0000E+03", "7.3301E-05"}, 
         {"2.5000E+03", "8.6274E-05"}, 
         {"3.0000E+03", "9.9011E-05"}, 
         {"3.5000E+03", "1.1245E-04"}, 
         {"4.0000E+03", "1.2570E-04"}, 
         {"4.5000E+03", "1.3794E-04"}, 
         {"5.0000E+03", "1.4962E-04"}, 
         {"5.5000E+03", "1.6124E-04"}, 
         {"6.0000E+03", "1.7308E-04"}, 
         {"6.5000E+03", "1.8480E-04"}, 
         {"7.0000E+03", "1.9518E-04"}, 
         {"7.5000E+03", "2.0372E-04"}, 
         {"8.0000E+03", "2.1188E-04"}, 
         {"8.5000E+03", "2.2065E-04"}, 
         {"9.0000E+03", "2.2969E-04"}, 
         {"9.5000E+03", "2.3821E-04"}, 
         {"1.0000E+04", "2.4532E-04"}, 
         {"1.0500E+04", "2.4996E-04"}, 
         {"1.1000E+04", "2.5102E-04"}, 
         {"1.1500E+04", "2.4749E-04"}, 
         {"1.2000E+04", "2.3872E-04"}, 
         {"1.2500E+04", "2.2476E-04"}, 
         {"1.3000E+04", "2.0634E-04"}, 
         {"1.3500E+04", "1.8495E-04"}, 
         {"1.4000E+04", "1.6211E-04"}, 
         {"1.4500E+04", "1.3965E-04"}, 
         {"1.5000E+04", "1.1885E-04"}, 
         {"1.5500E+04", "1.0056E-04"}, 
         {"1.6000E+04", "8.5196E-05"}, 
         {"1.6500E+04", "7.2815E-05"}, 
         {"1.7000E+04", "6.3199E-05"}, 
         {"1.7500E+04", "5.5980E-05"}, 
         {"1.8000E+04", "5.0743E-05"}, 
         {"1.8500E+04", "4.7240E-05"}, 
         {"1.9000E+04", "4.4807E-05"}, 
         {"1.9500E+04", "4.3748E-05"}, 
         {"2.0000E+04", "4.2922E-05"}, 
         {"2.0500E+04", "4.2597E-05"}, 
         {"2.1000E+04", "4.2611E-05"}, 
         {"2.1500E+04", "4.2822E-05"}, 
         {"2.2000E+04", "4.3099E-05"}, 
         {"2.2500E+04", "4.3462E-05"}, 
         {"2.3000E+04", "4.3881E-05"}, 
         {"2.3500E+04", "4.3593E-05"}, 
         {"2.4000E+04", "4.2921E-05"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("int3").set("interp", "piecewisecubic");
    model.component("comp1").material("mat3").propertyGroup("def").func("int3").set("extrap", "linear");
    model.component("comp1").material("mat3").propertyGroup("def").func("int3").set("fununit", new String[]{"Pa*s"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int4").label("Interpolation 4");
    model.component("comp1").material("mat3").propertyGroup("def").func("int4").set("funcname", "k");
    model.component("comp1").material("mat3").propertyGroup("def").func("int4")
         .set("table", new String[][]{{"250", "0.02421549999999999"}, 
         {"500", "0.041378"}, 
         {"1.0000E+03", "7.4598E-02"}, 
         {"1.5000E+03", "1.0739E-01"}, 
         {"2.0000E+03", "1.4507E-01"}, 
         {"2.5000E+03", "2.2793E-01"}, 
         {"3.0000E+03", "4.7237E-01"}, 
         {"3.5000E+03", "7.4638E-01"}, 
         {"4.0000E+03", "5.9775E-01"}, 
         {"4.5000E+03", "4.9737E-01"}, 
         {"5.0000E+03", "7.1051E-01"}, 
         {"5.5000E+03", "1.2856E+00"}, 
         {"6.0000E+03", "2.2826E+00"}, 
         {"6.5000E+03", "3.4708E+00"}, 
         {"7.0000E+03", "4.0697E+00"}, 
         {"7.5000E+03", "3.4726E+00"}, 
         {"8.0000E+03", "2.3897E+00"}, 
         {"8.5000E+03", "1.6902E+00"}, 
         {"9.0000E+03", "1.4510E+00"}, 
         {"9.5000E+03", "1.4968E+00"}, 
         {"1.0000E+04", "1.7086E+00"}, 
         {"1.0500E+04", "2.0356E+00"}, 
         {"1.1000E+04", "2.4420E+00"}, 
         {"1.1500E+04", "2.9213E+00"}, 
         {"1.2000E+04", "3.4487E+00"}, 
         {"1.2500E+04", "4.0036E+00"}, 
         {"1.3000E+04", "4.5197E+00"}, 
         {"1.3500E+04", "4.9744E+00"}, 
         {"1.4000E+04", "5.2825E+00"}, 
         {"1.4500E+04", "5.4198E+00"}, 
         {"1.5000E+04", "5.3585E+00"}, 
         {"1.5500E+04", "5.1518E+00"}, 
         {"1.6000E+04", "4.8285E+00"}, 
         {"1.6500E+04", "4.4678E+00"}, 
         {"1.7000E+04", "4.1271E+00"}, 
         {"1.7500E+04", "3.8423E+00"}, 
         {"1.8000E+04", "3.6279E+00"}, 
         {"1.8500E+04", "3.4842E+00"}, 
         {"1.9000E+04", "3.4028E+00"}, 
         {"1.9500E+04", "3.3743E+00"}, 
         {"2.0000E+04", "3.3881E+00"}, 
         {"2.0500E+04", "3.4362E+00"}, 
         {"2.1000E+04", "3.5114E+00"}, 
         {"2.1500E+04", "3.6082E+00"}, 
         {"2.2000E+04", "3.7223E+00"}, 
         {"2.2500E+04", "3.8501E+00"}, 
         {"2.3000E+04", "3.9860E+00"}, 
         {"2.3500E+04", "4.1315E+00"}, 
         {"2.4000E+04", "4.2831E+00"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("int4").set("interp", "piecewisecubic");
    model.component("comp1").material("mat3").propertyGroup("def").func("int4").set("extrap", "linear");
    model.component("comp1").material("mat3").propertyGroup("def").func("int4")
         .set("fununit", new String[]{"W/m/K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int4").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int5").label("Interpolation 5");
    model.component("comp1").material("mat3").propertyGroup("def").func("int5").set("funcname", "sigma");
    model.component("comp1").material("mat3").propertyGroup("def").func("int5")
         .set("table", new String[][]{{"250", "0"}, 
         {"500", "0"}, 
         {"1.0000E+03", "0.0000E+00"}, 
         {"1.5000E+03", "9.5610E-15"}, 
         {"2.0000E+03", "1.2172E-08"}, 
         {"2.5000E+03", "5.9528E-05"}, 
         {"3.0000E+03", "9.4093E-03"}, 
         {"3.6000E+03", "2.8954E-01"}, 
         {"4.1000E+03", "1.8605E+00"}, 
         {"4.6000E+03", "7.0505E+00"}, 
         {"5.1000E+03", "1.9684E+01"}, 
         {"5.6000E+03", "4.5108E+01"}, 
         {"6.1000E+03", "9.0194E+01"}, 
         {"6.6000E+03", "1.6591E+02"}, 
         {"7.1000E+03", "2.9647E+02"}, 
         {"7.6000E+03", "5.2076E+02"}, 
         {"8.1000E+03", "8.5399E+02"}, 
         {"8.6000E+03", "1.2750E+03"}, 
         {"9.1000E+03", "1.7564E+03"}, 
         {"9.6000E+03", "2.2752E+03"}, 
         {"1.0100E+04", "2.8142E+03"}, 
         {"1.0600E+04", "3.3614E+03"}, 
         {"1.1100E+04", "3.9087E+03"}, 
         {"1.1600E+04", "4.4513E+03"}, 
         {"1.2100E+04", "4.9859E+03"}, 
         {"1.2600E+04", "5.5095E+03"}, 
         {"1.3100E+04", "6.0204E+03"}, 
         {"1.3600E+04", "6.5145E+03"}, 
         {"1.4100E+04", "6.9915E+03"}, 
         {"1.4600E+04", "7.4471E+03"}, 
         {"1.5100E+04", "7.8816E+03"}, 
         {"1.5600E+04", "8.2954E+03"}, 
         {"1.6100E+04", "8.6903E+03"}, 
         {"1.6600E+04", "9.0689E+03"}, 
         {"1.7100E+04", "9.4343E+03"}, 
         {"1.7600E+04", "9.7889E+03"}, 
         {"1.8100E+04", "1.0135E+04"}, 
         {"1.8700E+04", "1.0542E+04"}, 
         {"1.9200E+04", "1.0876E+04"}, 
         {"1.9700E+04", "1.1198E+04"}, 
         {"2.0200E+04", "1.1518E+04"}, 
         {"2.0700E+04", "1.1830E+04"}, 
         {"2.1200E+04", "1.2132E+04"}, 
         {"2.1700E+04", "1.2419E+04"}, 
         {"2.2200E+04", "1.2686E+04"}, 
         {"2.2700E+04", "1.2927E+04"}, 
         {"2.3200E+04", "1.3127E+04"}, 
         {"2.3700E+04", "1.3297E+04"}});
    model.component("comp1").material("mat3").propertyGroup("def").func("int5").set("interp", "piecewisecubic");
    model.component("comp1").material("mat3").propertyGroup("def").func("int5").set("extrap", "linear");
    model.component("comp1").material("mat3").propertyGroup("def").func("int5").set("fununit", new String[]{"S/m"});
    model.component("comp1").material("mat3").propertyGroup("def").func("int5").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "cp(T)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("dynamicviscosity", "mu(T)");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"if(sigma(T)<sigma_min,sigma_min,sigma(T))", "0", "0", "0", "if(sigma(T)<sigma_min,sigma_min,sigma(T))", "0", "0", "0", "if(sigma(T)<sigma_min,sigma_min,sigma(T))"});
    model.component("comp1").material("mat3").propertyGroup("def").set("ratioofspecificheat", "1.40");
    model.component("comp1").material("mat3").propertyGroup("def").set("sigma_min", "1[S/m]");
    model.component("comp1").material("mat3").propertyGroup("def").descr("sigma_min", "");
    model.component("comp1").material("mat3").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("RadiationHeatTransfer")
         .label("Radiation heat transfer");
    model.component("comp1").material("mat3").propertyGroup("RadiationHeatTransfer").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat3").propertyGroup("RadiationHeatTransfer").func("int1")
         .set("funcname", "Qrad");
    model.component("comp1").material("mat3").propertyGroup("RadiationHeatTransfer").func("int1")
         .set("table", new String[][]{{"0", "0"}, 
         {"2.9542E+03", "1.2924E+03"}, 
         {"3.1955E+03", "2.9978E+03"}, 
         {"3.4127E+03", "6.3924E+03"}, 
         {"3.6298E+03", "1.4418E+04"}, 
         {"3.8469E+03", "3.2518E+04"}, 
         {"4.1364E+03", "8.2048E+04"}, 
         {"4.5948E+03", "1.7496E+05"}, 
         {"5.0050E+03", "3.8369E+05"}, 
         {"5.4634E+03", "9.4135E+05"}, 
         {"5.8253E+03", "2.3095E+06"}, 
         {"6.0907E+03", "4.9248E+06"}, 
         {"6.3320E+03", "9.9288E+06"}, 
         {"6.6456E+03", "2.5052E+07"}, 
         {"6.9834E+03", "6.3211E+07"}, 
         {"7.2729E+03", "1.4257E+08"}, 
         {"7.6348E+03", "3.3070E+08"}, 
         {"8.0449E+03", "7.0517E+08"}, 
         {"8.4551E+03", "1.4217E+09"}, 
         {"8.8652E+03", "2.7870E+09"}, 
         {"9.2995E+03", "4.7485E+09"}, 
         {"9.7579E+03", "8.5574E+09"}, 
         {"1.0337E+04", "1.5421E+10"}, 
         {"1.0940E+04", "2.7791E+10"}, 
         {"1.1567E+04", "4.4769E+10"}, 
         {"1.2146E+04", "6.4464E+10"}, 
         {"1.2798E+04", "9.2825E+10"}, 
         {"1.3498E+04", "1.1948E+11"}, 
         {"1.4245E+04", "1.5378E+11"}, 
         {"1.4921E+04", "1.7693E+11"}, 
         {"1.5717E+04", "1.9794E+11"}, 
         {"1.6537E+04", "2.2144E+11"}, 
         {"1.7213E+04", "2.5477E+11"}, 
         {"1.7961E+04", "2.8502E+11"}, 
         {"1.8805E+04", "3.4685E+11"}, 
         {"1.9674E+04", "4.3409E+11"}, 
         {"2.0591E+04", "5.5873E+11"}, 
         {"2.1628E+04", "7.3961E+11"}, 
         {"2.2714E+04", "9.7905E+11"}, 
         {"2.3896E+04", "1.2960E+12"}, 
         {"2.5271E+04", "1.7156E+12"}, 
         {"2.7105E+04", "2.2710E+12"}, 
         {"2.8987E+04", "2.8422E+12"}});
    model.component("comp1").material("mat3").propertyGroup("RadiationHeatTransfer").func("int1")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat3").propertyGroup("RadiationHeatTransfer").func("int1")
         .set("extrap", "linear");
    model.component("comp1").material("mat3").propertyGroup("RadiationHeatTransfer").func("int1")
         .set("fununit", new String[]{"W/m^3"});
    model.component("comp1").material("mat3").propertyGroup("RadiationHeatTransfer").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat3").propertyGroup("RadiationHeatTransfer").set("Qrad", "Qrad(T)");
    model.component("comp1").material("mat3").propertyGroup("RadiationHeatTransfer").addInput("temperature");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("density", new String[]{"rho(T)*spf.pA/1[atm]"});

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").material("mat3").selection().set(1, 2, 3, 6, 7);
    model.component("comp1").material("mat3").propertyGroup("def").func("int1").set("interp", "linear");
    model.component("comp1").material("mat3").propertyGroup("def").func("int2").set("interp", "linear");
    model.component("comp1").material("mat3").propertyGroup("def").func("int3").set("interp", "linear");
    model.component("comp1").material("mat3").propertyGroup("def").func("int4").set("interp", "linear");
    model.component("comp1").material("mat3").propertyGroup("def").func("int5").set("interp", "linear");
    model.component("comp1").material("mat3").propertyGroup("RadiationHeatTransfer").func("int1")
         .set("interp", "linear");

    model.component("comp1").mesh("mesh1").contribute("geom/detail", false);
    model.component("comp1").mesh("mesh1").contribute("physics/mef", false);
    model.component("comp1").mesh("mesh1").contribute("physics/ht", false);
    model.component("comp1").mesh("mesh1").contribute("physics/spf", false);
    model.component("comp1").mesh("mesh1").contribute("multiphysics/phs1", false);
    model.component("comp1").mesh("mesh1").contribute("multiphysics/mhd1", false);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(3, 4, 5, 6, 7, 8, 9);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(20, 23);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "0.8[mm]");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().set(6, 7);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmax", "10[mm]");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hgrad", 1.1);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(1, 2);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/phs1", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/mhd1", false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tunit", "ms");
    model.study("std1").feature("time").set("tlist", "range(ts_ms,0.2,4)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol1").feature("t1").set("initialstepbdf", "0.1[us]");
    model.sol("sol1").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol1").feature("t1").set("maxstepbdf", "2[us]");
    model.sol("sol1").feature("t1").feature("se1").set("ntolfact", 1);
    model.sol("sol1").feature("t1").feature("se1").feature().remove("ss3");
    model.sol("sol1").feature("t1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_T", "comp1_p", "comp1_u"});
    model.sol("sol1").feature("t1").feature("se1").feature("ss1").set("linsolver", "i1");
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("subjtech", "once");
    model.sol("sol1").feature("t1").create("i2", "Iterative");
    model.sol("sol1").feature("t1").feature("i2").create("mg1", "Multigrid");
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").set("prefun", "amg");
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").set("hybridization", "multi");
    model.sol("sol1").feature("t1").feature("i2").feature("mg1").set("hybridvar", new String[]{"comp1_A"});
    model.sol("sol1").feature("t1").feature("i2").create("dp1", "DirectPreconditioner");
    model.sol("sol1").feature("t1").feature("i2").feature("dp1").set("linsolver", "pardiso");
    model.sol("sol1").feature("t1").feature("i2").feature("dp1")
         .set("hybridvar", new String[]{"comp1_V", "comp1_mef_mi2_term1_V0_ode"});
    model.sol("sol1").feature("t1").feature("se1").feature("ss2").set("linsolver", "i2");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (mef)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("solutionparams", "parent");
    model.result("pg1").feature("mslc1").set("expr", "mef.normB");
    model.result("pg1").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("mslc1").set("xcoord", "mef.CPx");
    model.result("pg1").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("mslc1").set("ycoord", "mef.CPy");
    model.result("pg1").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("mslc1").set("zcoord", "mef.CPz");
    model.result("pg1").feature("mslc1").set("colortable", "Prism");
    model.result("pg1").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg1").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("strmsl1").set("xcoord", "mef.CPx");
    model.result("pg1").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("strmsl1").set("ycoord", "mef.CPy");
    model.result("pg1").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("strmsl1").set("zcoord", "mef.CPz");
    model.result("pg1").feature("strmsl1").set("titletype", "none");
    model.result("pg1").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg1").feature("strmsl1").set("udist", 0.02);
    model.result("pg1").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg1").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("inheritcolor", false);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("data", "parent");
    model.result("pg1").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg1").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg1").feature("strmsl1").feature("col1").set("expr", "mef.normB");
    model.result("pg1").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg1").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u573a (mef)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "mef.normE");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "mef.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "mef.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "mef.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Prism");
    model.result("pg2").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("expr", new String[]{"mef.Ex", "mef.Ey", "mef.Ez"});
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "mef.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "mef.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "mef.CPz");
    model.result("pg2").feature("strmsl1").set("titletype", "none");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("udist", 0.02);
    model.result("pg2").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg2").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("inheritcolor", false);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("data", "parent");
    model.result("pg2").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg2").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "mef.normE");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("expr", "T");
    model.result("pg3").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("vol1").set("smooth", "internal");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u901f\u5ea6 (spf)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("slc1", "Slice");
    model.result("pg4").feature("slc1").label("\u5207\u9762");
    model.result("pg4").feature("slc1").set("showsolutionparams", "on");
    model.result("pg4").feature("slc1").set("expr", "spf.U");
    model.result("pg4").feature("slc1").set("smooth", "internal");
    model.result("pg4").feature("slc1").set("showsolutionparams", "on");
    model.result("pg4").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection().set(21, 27, 35, 36);
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u538b\u529b (spf)");
    model.result("pg5").set("data", "surf1");
    model.result("pg5").setIndex("looplevel", 19, 0);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u8868\u9762");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("expr", "p");
    model.result("pg5").feature("surf1").set("colortable", "Dipole");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature("surf1").feature().create("tran1", "Transparency");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature().remove("strmsl1");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").set("edges", false);
    model.result("pg3").run();
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").importData("data0400.txt");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").importData("data0400x.txt");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("twoyaxes", true);
    model.result("pg6").create("tblp1", "Table");
    model.result("pg6").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg6").feature("tblp1").set("linewidth", "preference");
    model.result("pg6").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg6").feature("tblp1").set("plotcolumns", new int[]{2});
    model.result("pg6").feature("tblp1").set("legend", true);
    model.result("pg6").run();
    model.result("pg6").feature("tblp1").set("legendmethod", "manual");
    model.result("pg6").feature("tblp1").setIndex("legends", "I (A), experiments", 0);
    model.result("pg6").feature().duplicate("tblp2", "tblp1");
    model.result("pg6").run();
    model.result("pg6").feature("tblp2").set("plotcolumns", new int[]{3});
    model.result("pg6").feature("tblp2").set("plotonsecyaxis", true);
    model.result("pg6").feature("tblp2").setIndex("legends", "V (V), experiments", 0);
    model.result("pg6").run();
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("data", "dset1");
    model.result("pg6").feature("glob1").setIndex("expr", "comp1.mef.I0_1*2", 0);
    model.result("pg6").feature("glob1").set("linemarker", "cycle");
    model.result("pg6").feature("glob1").set("markerpos", "interp");
    model.result("pg6").feature().duplicate("glob2", "glob1");
    model.result("pg6").run();
    model.result("pg6").feature("glob2").setIndex("expr", "comp1.mef.V0_1", 0);
    model.result("pg6").feature("glob2").set("plotonsecyaxis", true);
    model.result("pg6").run();
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("yseclabelactive", true);
    model.result("pg6").set("axislimits", true);
    model.result("pg6").set("xmin", 0);
    model.result("pg6").set("xmax", 4);
    model.result("pg6").set("ymin", 0);
    model.result("pg6").set("ymax", 900);
    model.result("pg6").set("yminsec", 0);
    model.result("pg6").set("ymaxsec", 90);
    model.result("pg6").run();

    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("maxop1").selection().set(28);
    model.component("comp1").cpl().create("maxop2", "Maximum");
    model.component("comp1").cpl("maxop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("maxop2").selection().set(22);

    model.component("comp1").variable("var1").set("disp_a", "maxop1(x*(T==maxop1(T)))-L_wire");
    model.component("comp1").variable("var1").descr("disp_a", "Simulated arc displacement (anode)");
    model.component("comp1").variable("var1").set("disp_c", "maxop2(x*(T==maxop2(T)))-L_wire");
    model.component("comp1").variable("var1").descr("disp_c", "Simulated arc displacement (cathode)");

    model.sol("sol1").updateSolution();

    model.result("pg1").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").create("tblp1", "Table");
    model.result("pg7").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg7").feature("tblp1").set("linewidth", "preference");
    model.result("pg7").feature("tblp1").set("table", "tbl2");
    model.result("pg7").feature("tblp1").set("legend", true);
    model.result("pg7").run();
    model.result("pg7").feature("tblp1").set("legendmethod", "manual");
    model.result("pg7").feature("tblp1").setIndex("legends", "Experiments (anode)", 0);
    model.result("pg7").feature("tblp1").setIndex("legends", "Experiments (cathode)", 1);
    model.result("pg7").run();
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "comp1.disp_a", 0);
    model.result("pg7").feature("glob1").setIndex("expr", "comp1.disp_c", 1);
    model.result("pg7").feature("glob1").set("linemarker", "cycle");
    model.result("pg7").feature("glob1").set("markerpos", "interp");
    model.result("pg7").run();
    model.result("pg7").set("legendpos", "lowerright");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("axislimits", true);
    model.result("pg7").set("xmin", 0);
    model.result("pg7").set("xmax", 4);
    model.result("pg7").set("ymin", -10);
    model.result("pg7").set("ymax", 180);
    model.result("pg7").set("legendpos", "upperleft");
    model.result("pg7").run();

    model.title("\u94dc\u5bfc\u8f68\u4e2d\u7684\u77ac\u6001\u7535\u5f27\u653e\u7535");

    model
         .description("\u4e0d\u5fc5\u8981\u7684\u7535\u5f27\u53ef\u80fd\u4f1a\u5bf9\u7535\u6c14\u548c\u7535\u5b50\u8bbe\u5907\u53ca\u7cfb\u7edf\u4ea7\u751f\u4e25\u91cd\u7684\u4e0d\u826f\u5f71\u54cd\u3002\u4e3a\u4e86\u66f4\u597d\u5730\u7406\u89e3\u548c\u9884\u6d4b\u7535\u5f27\u52a8\u529b\u5b66\uff0c\u8fdb\u884c\u77ac\u6001\u7535\u5f27\u8fc7\u7a0b\u7684\u591a\u7269\u7406\u573a\u4eff\u771f\u81f3\u5173\u91cd\u8981\u3002\u672c\u6a21\u578b\u91c7\u7528\u57fa\u4e8e\u78c1\u6d41\u4f53\u52a8\u529b\u5b66\u516c\u5f0f\u7684\u201c\u7535\u5f27\u653e\u7535\u201d\u591a\u7269\u7406\u573a\u63a5\u53e3\uff0c\u5bf9\u94dc\u5bfc\u8f68\u4e2d\u7684\u77ac\u6001\u7535\u5f27\u653e\u7535\u8fd0\u52a8\u8fdb\u884c\u4e86\u4e09\u7ef4\u4eff\u771f\u3002\u5f97\u5230\u7684\u7535\u5f27\u7535\u538b\u548c\u7535\u5f27\u901f\u5ea6\u4e0e\u5b9e\u9a8c\u7ed3\u679c\u8868\u73b0\u51fa\u826f\u597d\u7684\u4e00\u81f4\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("transient_arc_3d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
