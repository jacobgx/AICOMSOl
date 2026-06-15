/*
 * plasma_dc_arc.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:04 by COMSOL 6.3.0.290. */
public class plasma_dc_arc {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Equilibrium_Discharges");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mef", "ElectricInductionCurrents", "geom1");
    model.component("comp1").physics("mef").prop("components").set("components", "inplane");
    model.component("comp1").physics("mef").prop("ShapeProperty").set("order_magneticvectorpotential", "1");
    model.component("comp1").physics("mef").prop("ShapeProperty").set("order_electricpotential", "1");
    model.component("comp1").physics("mef").create("alcf1", "ElectromagneticModelFluid");
    model.component("comp1").physics("mef").feature("alcf1").selection().all();
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.component("comp1").multiphysics().create("phs1", "EquilibriumDischargeHeatSource", 2);
    model.component("comp1").multiphysics("phs1").set("phsEMHeat_physics", "mef");
    model.component("comp1").multiphysics("phs1").set("phsHeat_physics", "ht");
    model.component("comp1").multiphysics("phs1").selection().all();
    model.component("comp1").multiphysics().create("mhd1", "Magnetohydrodynamics", 2);
    model.component("comp1").multiphysics("mhd1").set("EMForce_physics", "mef");
    model.component("comp1").multiphysics("mhd1").set("FluidFlow_physics", "spf");
    model.component("comp1").multiphysics("mhd1").selection().all();
    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 2);
    model.component("comp1").multiphysics("nitf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("nitf1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics().create("bphs1", "EquilibriumDischargeBoundaryHeatSource", 1);
    model.component("comp1").multiphysics("bphs1").set("PHeatBoundary_physics", "mef");
    model.component("comp1").multiphysics("bphs1").set("PHeat_physics", "ht");
    model.component("comp1").multiphysics("bphs1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mef", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/phs1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/mhd1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/nitf1", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/bphs1", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{25, 23});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{25, 8});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{0, -8});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 3, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.3, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 3, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 1.6, 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 5.3, 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 1.6, 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 23, 3, 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("pointinsketch").set("pol1", 2);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", 0.3);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 5, 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 23, 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int3", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int4", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("int5", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RadiationHeatTransfer", "RadiationHeatTransfer", "Radiation heat transfer");
    model.component("comp1").material("mat1").propertyGroup("RadiationHeatTransfer").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").label("Argon (1[atm])");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("funcname", "rho");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .set("table", new String[][]{{"250", "1.37933"}, 
         {"500", "0.97353"}, 
         {"1.0000E+03", "4.8672E-01"}, 
         {"1.5000E+03", "3.2450E-01"}, 
         {"2.0000E+03", "2.4338E-01"}, 
         {"2.5000E+03", "1.9411E-01"}, 
         {"3.0000E+03", "1.6226E-01"}, 
         {"3.5000E+03", "1.3908E-01"}, 
         {"4.0000E+03", "1.2170E-01"}, 
         {"4.5000E+03", "1.0818E-01"}, 
         {"5.0000E+03", "9.7361E-02"}, 
         {"5.5000E+03", "8.8510E-02"}, 
         {"6.0000E+03", "8.1133E-02"}, 
         {"6.5000E+03", "7.4888E-02"}, 
         {"7.0000E+03", "6.9527E-02"}, 
         {"7.5000E+03", "6.4865E-02"}, 
         {"8.0000E+03", "6.0757E-02"}, 
         {"8.5000E+03", "5.7083E-02"}, 
         {"9.0000E+03", "5.3740E-02"}, 
         {"9.5000E+03", "5.0639E-02"}, 
         {"1.0000E+04", "4.7696E-02"}, 
         {"1.0500E+04", "4.4838E-02"}, 
         {"1.1000E+04", "4.2003E-02"}, 
         {"1.1500E+04", "3.9145E-02"}, 
         {"1.2000E+04", "3.6243E-02"}, 
         {"1.2500E+04", "3.3310E-02"}, 
         {"1.3000E+04", "3.0387E-02"}, 
         {"1.3500E+04", "2.7556E-02"}, 
         {"1.4000E+04", "2.4906E-02"}, 
         {"1.4500E+04", "2.2539E-02"}, 
         {"1.5000E+04", "2.0510E-02"}, 
         {"1.5500E+04", "1.8832E-02"}, 
         {"1.6000E+04", "1.7417E-02"}, 
         {"1.6500E+04", "1.6392E-02"}, 
         {"1.7000E+04", "1.5516E-02"}, 
         {"1.7500E+04", "1.4795E-02"}, 
         {"1.8000E+04", "1.4187E-02"}, 
         {"1.8500E+04", "1.3660E-02"}, 
         {"1.9000E+04", "1.3207E-02"}, 
         {"1.9500E+04", "1.2775E-02"}, 
         {"2.0000E+04", "1.2369E-02"}, 
         {"2.0500E+04", "1.1977E-02"}, 
         {"2.1000E+04", "1.1590E-02"}, 
         {"2.1500E+04", "1.1201E-02"}, 
         {"2.2000E+04", "1.0802E-02"}, 
         {"2.2500E+04", "1.0392E-02"}, 
         {"2.3000E+04", "9.9693E-03"}, 
         {"2.3500E+04", "9.5460E-03"}, 
         {"2.4000E+04", "9.1103E-03"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("def").func("int1")
         .set("fununit", new String[]{"kg/m^3"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").set("funcname", "cp");
    model.component("comp1").material("mat1").propertyGroup("def").func("int2")
         .set("table", new String[][]{{"250", "519.5800000000002"}, 
         {"500", "520.33"}, 
         {"1.0000E+03", "5.2041E+02"}, 
         {"1.5000E+03", "5.2036E+02"}, 
         {"2.0000E+03", "5.2034E+02"}, 
         {"2.5000E+03", "5.2033E+02"}, 
         {"3.0000E+03", "5.2033E+02"}, 
         {"3.5000E+03", "5.2033E+02"}, 
         {"4.0000E+03", "5.2033E+02"}, 
         {"4.5000E+03", "5.2034E+02"}, 
         {"5.0000E+03", "5.2045E+02"}, 
         {"5.5000E+03", "5.2095E+02"}, 
         {"6.0000E+03", "5.2276E+02"}, 
         {"6.5000E+03", "5.2801E+02"}, 
         {"7.0000E+03", "5.4091E+02"}, 
         {"7.5000E+03", "5.6859E+02"}, 
         {"8.0000E+03", "6.2133E+02"}, 
         {"8.5000E+03", "7.1515E+02"}, 
         {"9.0000E+03", "8.6785E+02"}, 
         {"9.5000E+03", "1.1027E+03"}, 
         {"1.0000E+04", "1.4577E+03"}, 
         {"1.0500E+04", "1.9469E+03"}, 
         {"1.1000E+04", "2.6087E+03"}, 
         {"1.1500E+04", "3.3854E+03"}, 
         {"1.2100E+04", "4.7392E+03"}, 
         {"1.2600E+04", "6.0073E+03"}, 
         {"1.3200E+04", "7.5907E+03"}, 
         {"1.3800E+04", "8.9539E+03"}, 
         {"1.4300E+04", "9.6220E+03"}, 
         {"1.4800E+04", "9.6348E+03"}, 
         {"1.5300E+04", "8.9748E+03"}, 
         {"1.5800E+04", "7.8353E+03"}, 
         {"1.6300E+04", "6.5129E+03"}, 
         {"1.6800E+04", "5.2621E+03"}, 
         {"1.7300E+04", "4.2265E+03"}, 
         {"1.7800E+04", "3.4503E+03"}, 
         {"1.8300E+04", "2.9240E+03"}, 
         {"1.8800E+04", "2.6221E+03"}, 
         {"1.9400E+04", "2.5240E+03"}, 
         {"1.9900E+04", "2.6606E+03"}, 
         {"2.0400E+04", "2.9976E+03"}, 
         {"2.0900E+04", "3.5472E+03"}, 
         {"2.1400E+04", "4.3186E+03"}, 
         {"2.1900E+04", "5.3062E+03"}, 
         {"2.2400E+04", "6.4791E+03"}, 
         {"2.2900E+04", "7.7741E+03"}, 
         {"2.3500E+04", "9.3495E+03"}, 
         {"2.4000E+04", "1.0553E+04"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("def").func("int2")
         .set("fununit", new String[]{"J/kg/K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").label("Interpolation 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("funcname", "mu");
    model.component("comp1").material("mat1").propertyGroup("def").func("int3")
         .set("table", new String[][]{{"250", "2.1671500000000004E-5"}, 
         {"500", "3.4224E-5"}, 
         {"1.0000E+03", "5.7633E-05"}, 
         {"1.5000E+03", "7.3960E-05"}, 
         {"2.0000E+03", "8.9715E-05"}, 
         {"2.5000E+03", "1.0511E-04"}, 
         {"3.0000E+03", "1.1987E-04"}, 
         {"3.5000E+03", "1.3389E-04"}, 
         {"4.0000E+03", "1.4715E-04"}, 
         {"4.5000E+03", "1.5972E-04"}, 
         {"5.0000E+03", "1.7169E-04"}, 
         {"5.5000E+03", "1.8314E-04"}, 
         {"6.0000E+03", "1.9415E-04"}, 
         {"6.5000E+03", "2.0481E-04"}, 
         {"7.0000E+03", "2.1517E-04"}, 
         {"7.5000E+03", "2.2529E-04"}, 
         {"8.0000E+03", "2.3518E-04"}, 
         {"8.5000E+03", "2.4480E-04"}, 
         {"9.0000E+03", "2.5402E-04"}, 
         {"9.5000E+03", "2.6252E-04"}, 
         {"1.0000E+04", "2.6965E-04"}, 
         {"1.0500E+04", "2.7431E-04"}, 
         {"1.1000E+04", "2.7489E-04"}, 
         {"1.1500E+04", "2.6947E-04"}, 
         {"1.2000E+04", "2.5646E-04"}, 
         {"1.2500E+04", "2.3552E-04"}, 
         {"1.3000E+04", "2.0811E-04"}, 
         {"1.3500E+04", "1.7748E-04"}, 
         {"1.4000E+04", "1.4715E-04"}, 
         {"1.4500E+04", "1.2038E-04"}, 
         {"1.5000E+04", "9.8656E-05"}, 
         {"1.5500E+04", "8.2258E-05"}, 
         {"1.6000E+04", "7.0625E-05"}, 
         {"1.6500E+04", "6.2838E-05"}, 
         {"1.7000E+04", "5.7957E-05"}, 
         {"1.7500E+04", "5.5177E-05"}, 
         {"1.8000E+04", "5.3865E-05"}, 
         {"1.8500E+04", "5.3537E-05"}, 
         {"1.9000E+04", "5.4238E-05"}, 
         {"1.9500E+04", "5.4778E-05"}, 
         {"2.0000E+04", "5.5304E-05"}, 
         {"2.0500E+04", "5.5537E-05"}, 
         {"2.1000E+04", "5.5219E-05"}, 
         {"2.1500E+04", "5.4140E-05"}, 
         {"2.2000E+04", "5.2186E-05"}, 
         {"2.2500E+04", "4.9382E-05"}, 
         {"2.3000E+04", "4.5896E-05"}, 
         {"2.3500E+04", "4.2296E-05"}, 
         {"2.4000E+04", "3.8250E-05"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("fununit", new String[]{"Pa*s"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int3").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int4").label("Interpolation 4");
    model.component("comp1").material("mat1").propertyGroup("def").func("int4").set("funcname", "k");
    model.component("comp1").material("mat1").propertyGroup("def").func("int4")
         .set("table", new String[][]{{"250", "0.016914500000000002"}, 
         {"500", "0.026712"}, 
         {"1.0000E+03", "4.4982E-02"}, 
         {"1.5000E+03", "5.7725E-02"}, 
         {"2.0000E+03", "7.0022E-02"}, 
         {"2.5000E+03", "8.2038E-02"}, 
         {"3.0000E+03", "9.3561E-02"}, 
         {"3.5000E+03", "1.0458E-01"}, 
         {"5.1000E+03", "1.3604E-01"}, 
         {"5.6000E+03", "1.4582E-01"}, 
         {"6.1000E+03", "1.5756E-01"}, 
         {"6.6000E+03", "1.7388E-01"}, 
         {"7.2000E+03", "2.0308E-01"}, 
         {"7.7000E+03", "2.3823E-01"}, 
         {"8.2000E+03", "2.8698E-01"}, 
         {"8.7000E+03", "3.5411E-01"}, 
         {"9.2000E+03", "4.4416E-01"}, 
         {"9.7000E+03", "5.6042E-01"}, 
         {"1.0200E+04", "7.0582E-01"}, 
         {"1.0700E+04", "8.8084E-01"}, 
         {"1.1200E+04", "1.0867E+00"}, 
         {"1.1700E+04", "1.3184E+00"}, 
         {"1.2200E+04", "1.5679E+00"}, 
         {"1.2700E+04", "1.8220E+00"}, 
         {"1.3200E+04", "2.0670E+00"}, 
         {"1.3700E+04", "2.2684E+00"}, 
         {"1.4200E+04", "2.4121E+00"}, 
         {"1.4700E+04", "2.4813E+00"}, 
         {"1.5200E+04", "2.4851E+00"}, 
         {"1.5700E+04", "2.4511E+00"}, 
         {"1.6200E+04", "2.4116E+00"}, 
         {"1.6700E+04", "2.3906E+00"}, 
         {"1.7200E+04", "2.3993E+00"}, 
         {"1.7700E+04", "2.4392E+00"}, 
         {"1.8200E+04", "2.5066E+00"}, 
         {"1.8700E+04", "2.5964E+00"}, 
         {"1.9200E+04", "2.7031E+00"}, 
         {"1.9700E+04", "2.8235E+00"}, 
         {"2.0200E+04", "2.9536E+00"}, 
         {"2.0700E+04", "3.0904E+00"}, 
         {"2.1200E+04", "3.2315E+00"}, 
         {"2.1700E+04", "3.3747E+00"}, 
         {"2.2200E+04", "3.5185E+00"}, 
         {"2.2700E+04", "3.6616E+00"}, 
         {"2.3200E+04", "3.8034E+00"}, 
         {"2.3700E+04", "3.9456E+00"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int4").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("int4").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("def").func("int4")
         .set("fununit", new String[]{"W/m/K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int4").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int5").label("Interpolation 5");
    model.component("comp1").material("mat1").propertyGroup("def").func("int5").set("funcname", "sigma");
    model.component("comp1").material("mat1").propertyGroup("def").func("int5")
         .set("table", new String[][]{{"250", "4.3719000000000004E-23"}, 
         {"500", "3.0784E-23"}, 
         {"1.0000E+03", "3.9216E-23"}, 
         {"1.5000E+03", "1.7575E-18"}, 
         {"2.0000E+03", "1.6592E-11"}, 
         {"2.5000E+03", "2.3956E-07"}, 
         {"3.0000E+03", "5.4577E-05"}, 
         {"3.5000E+03", "2.9073E-03"}, 
         {"4.0000E+03", "6.2885E-02"}, 
         {"4.5000E+03", "7.1816E-01"}, 
         {"5.0000E+03", "5.1615E+00"}, 
         {"5.5000E+03", "2.3647E+01"}, 
         {"6.0000E+03", "7.6821E+01"}, 
         {"6.5000E+03", "1.8290E+02"}, 
         {"7.0000E+03", "3.5808E+02"}, 
         {"7.5000E+03", "6.2440E+02"}, 
         {"8.0000E+03", "9.9034E+02"}, 
         {"8.5000E+03", "1.4378E+03"}, 
         {"9.0000E+03", "1.9358E+03"}, 
         {"9.5000E+03", "2.4584E+03"}, 
         {"1.0000E+04", "2.9900E+03"}, 
         {"1.0600E+04", "3.6295E+03"}, 
         {"1.1100E+04", "4.1603E+03"}, 
         {"1.1700E+04", "4.7920E+03"}, 
         {"1.2200E+04", "5.3120E+03"}, 
         {"1.2700E+04", "5.8233E+03"}, 
         {"1.3200E+04", "6.3220E+03"}, 
         {"1.3700E+04", "6.8047E+03"}, 
         {"1.4200E+04", "7.2664E+03"}, 
         {"1.4700E+04", "7.7047E+03"}, 
         {"1.5200E+04", "8.1189E+03"}, 
         {"1.5700E+04", "8.5107E+03"}, 
         {"1.6200E+04", "8.8829E+03"}, 
         {"1.6700E+04", "9.2394E+03"}, 
         {"1.7200E+04", "9.5831E+03"}, 
         {"1.7700E+04", "9.9164E+03"}, 
         {"1.8200E+04", "1.0240E+04"}, 
         {"1.8700E+04", "1.0551E+04"}, 
         {"1.9200E+04", "1.0845E+04"}, 
         {"1.9700E+04", "1.1122E+04"}, 
         {"2.0200E+04", "1.1371E+04"}, 
         {"2.0700E+04", "1.1582E+04"}, 
         {"2.1200E+04", "1.1746E+04"}, 
         {"2.1700E+04", "1.1856E+04"}, 
         {"2.2200E+04", "1.1907E+04"}, 
         {"2.2700E+04", "1.1905E+04"}, 
         {"2.3200E+04", "1.1860E+04"}, 
         {"2.3700E+04", "1.1783E+04"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("int5").set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("def").func("int5").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("def").func("int5").set("fununit", new String[]{"S/m"});
    model.component("comp1").material("mat1").propertyGroup("def").func("int5").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "mu(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"if(sigma(T)<sigma_min,sigma_min,sigma(T))", "0", "0", "0", "if(sigma(T)<sigma_min,sigma_min,sigma(T))", "0", "0", "0", "if(sigma(T)<sigma_min,sigma_min,sigma(T))"});
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.66");
    model.component("comp1").material("mat1").propertyGroup("def").set("sigma_min", "1[S/m]");
    model.component("comp1").material("mat1").propertyGroup("def").descr("sigma_min", "");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("RadiationHeatTransfer")
         .label("Radiation heat transfer");
    model.component("comp1").material("mat1").propertyGroup("RadiationHeatTransfer").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("RadiationHeatTransfer").func("int1")
         .set("funcname", "Qrad");
    model.component("comp1").material("mat1").propertyGroup("RadiationHeatTransfer").func("int1")
         .set("table", new String[][]{{"0", "0"}, 
         {"4.8251E+03", "1.3345E+01"}, 
         {"5.0578E+03", "3.7829E+01"}, 
         {"5.2777E+03", "1.1457E+02"}, 
         {"5.5109E+03", "3.4681E+02"}, 
         {"5.6892E+03", "8.0898E+02"}, 
         {"5.9350E+03", "2.2180E+03"}, 
         {"6.2216E+03", "6.9239E+03"}, 
         {"6.4801E+03", "1.7194E+04"}, 
         {"6.8342E+03", "6.3086E+04"}, 
         {"7.2679E+03", "2.2330E+05"}, 
         {"7.7421E+03", "8.7089E+05"}, 
         {"8.2552E+03", "2.8778E+06"}, 
         {"8.8073E+03", "8.3262E+06"}, 
         {"9.2511E+03", "1.8001E+07"}, 
         {"9.6950E+03", "3.8916E+07"}, 
         {"1.0179E+04", "8.9704E+07"}, 
         {"1.0756E+04", "1.8670E+08"}, 
         {"1.1319E+04", "4.0177E+08"}, 
         {"1.1868E+04", "7.3404E+08"}, 
         {"1.2532E+04", "1.3275E+09"}, 
         {"1.3362E+04", "2.3406E+09"}, 
         {"1.4369E+04", "4.0321E+09"}, 
         {"1.6925E+04", "5.5210E+09"}, 
         {"2.0956E+04", "1.4343E+10"}});
    model.component("comp1").material("mat1").propertyGroup("RadiationHeatTransfer").func("int1")
         .set("interp", "piecewisecubic");
    model.component("comp1").material("mat1").propertyGroup("RadiationHeatTransfer").func("int1")
         .set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("RadiationHeatTransfer").func("int1")
         .set("fununit", new String[]{"W/m^3"});
    model.component("comp1").material("mat1").propertyGroup("RadiationHeatTransfer").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("RadiationHeatTransfer").set("Qrad", "Qrad(T)");
    model.component("comp1").material("mat1").propertyGroup("RadiationHeatTransfer").addInput("temperature");
    model.component("comp1").material("mat1").selection().set(2);
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Steel AISI 4340");
    model.component("comp1").material("mat2").set("family", "steel");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat2").selection().set(1);

    model.param().set("I0", "80[A]");
    model.param().descr("I0", "\u7535\u6d41");
    model.param().set("J0", "-I0/(pi*(1.6[mm])^2)");
    model.param().descr("J0", "\u6cd5\u5411\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("U0", "3[m/s]");
    model.param().descr("U0", "\u5165\u53e3\u901f\u5ea6");

    model.component("comp1").physics("mef").feature("mi1").create("ein1", "ElectricInsulation", 1);
    model.component("comp1").physics("mef").feature("mi1").feature("ein1").selection().set(10, 11, 13);
    model.component("comp1").physics("mef").feature("mi1").create("ncd1", "NormalCurrentDensity", 1);
    model.component("comp1").physics("mef").feature("mi1").feature("ncd1").selection().set(7);
    model.component("comp1").physics("mef").feature("mi1").feature("ncd1").set("nJ", "J0");
    model.component("comp1").physics("mef").create("gfa1", "GaugeFixingA", 2);

    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat3").label("Tungsten");
    model.component("comp1").material("mat3").set("family", "custom");
    model.component("comp1").material("mat3")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat3").set("diffuse", "custom");
    model.component("comp1").material("mat3")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat3").set("ambient", "custom");
    model.component("comp1").material("mat3")
         .set("customambient", new double[]{0.7058823529411765, 0.7058823529411765, 0.7058823529411765});
    model.component("comp1").material("mat3").set("noise", true);
    model.component("comp1").material("mat3").set("fresnel", 0.9);
    model.component("comp1").material("mat3").set("roughness", 0.15);
    model.component("comp1").material("mat3").set("diffusewrap", 0);
    model.component("comp1").material("mat3").set("reflectance", 0);
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"4.5e-6[1/K]", "0", "0", "0", "4.5e-6[1/K]", "0", "0", "0", "4.5e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "17800[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "132[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"175[W/(m*K)]", "0", "0", "0", "175[W/(m*K)]", "0", "0", "0", "175[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "360[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("l", "-4.7e11[Pa]");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("m", "-7.9e11[Pa]");
    model.component("comp1").material("mat3").propertyGroup("Murnaghan").set("n", "-1.1e12[Pa]");
    model.component("comp1").material("mat3").selection().set(3);
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"20e6[S/m]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"1"});

    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("Tref", "300[K]");
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "300[K]");
    model.component("comp1").physics("ht").create("solid1", "SolidHeatTransferModel", 2);
    model.component("comp1").physics("ht").feature("solid1").set("minput_strainreferencetemperature_src", "userdef");
    model.component("comp1").physics("ht").feature("solid1").set("minput_strainreferencetemperature", "300[K]");
    model.component("comp1").physics("ht").feature("solid1").selection().set(1, 3);
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().set(7, 10, 11, 13);
    model.component("comp1").physics("ht").feature("temp1").set("T0", "300[K]");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").selection().set(2, 12);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", 200);
    model.component("comp1").physics("ht").feature("hf1").set("Text", "300[K]");
    model.component("comp1").physics("ht").create("bhs1", "BoundaryHeatSource", 1);
    model.component("comp1").physics("ht").feature("bhs1").selection().set(4, 6, 8, 9, 14);
    model.component("comp1").physics("ht").feature("bhs1").set("Qb_input", "-0.4*sigma_const*T^4");
    model.component("comp1").physics("ht").create("init2", "init", 2);
    model.component("comp1").physics("ht").feature("init2").selection().set(2);
    model.component("comp1").physics("ht").feature("init2").set("Tinit", "15e3*exp(-(r/1[mm])^2)+300");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "WeaklyCompressible");
    model.component("comp1").physics("spf").selection().set(2);
    model.component("comp1").physics("spf").create("open1", "OpenBoundary", 1);
    model.component("comp1").physics("spf").feature("open1").selection().set(11, 13);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(10);
    model.component("comp1").physics("spf").feature("inl1").set("U0in", "U0");

    model.component("comp1").multiphysics("bphs1").selection().set(4);
    model.component("comp1").multiphysics().create("bphs2", "EquilibriumDischargeBoundaryHeatSource", 1);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").multiphysics("bphs2").selection().set(6, 8, 9, 14);
    model.component("comp1").multiphysics("bphs2").set("ElectrodePolaritySelection", "cathode");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 3);
    model.component("comp1").mesh("mesh1").feature("size2").selection().set(3, 4, 6, 8, 9, 14);
    model.component("comp1").mesh("mesh1").feature("size2").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("bl1").active(false);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol1").feature("s1").feature("fc1").set("initstep", "1E-4");
    model.sol("sol1").feature("s1").feature("fc1").set("minstep", 1.0E-6);
    model.sol("sol1").feature("s1").feature("fc1").set("rstep", 1.5);
    model.sol("sol1").feature("s1").feature("fc1").set("minsteprecovery", 0.1);
    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 200);
    model.sol("sol1").feature("s1").feature("aDef").set("cachepattern", false);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").label("\u6e29\u5ea6");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("colortable", "Thermal");
    model.result("pg1").feature("surf1").set("expr", "T");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u901f\u5ea6");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "spf.U");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u5bfc\u7387");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "mef.sigmarr");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().set(2);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("\u78c1\u901a\u91cf");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "log10(mef.normB)");
    model.result("pg4").feature("surf1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("colortabletrans", "reverse");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u4e09\u7ef4\u6e29\u5ea6");
    model.result("pg5").create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("expr", "T");
    model.result("pg5").feature("vol1").set("colortable", "Thermal");
    model.result("pg5").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg5").run();

    model.title("\u76f4\u6d41\u7b49\u79bb\u5b50\u4f53\u5f27");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u5728\u70b9\u5bf9\u9762\u914d\u7f6e\u4e2d\u521b\u5efa\u7684\u76f4\u6d41\u7b49\u79bb\u5b50\u4f53\u5f27\u7684\u7535\u6c14\u548c\u70ed\u7279\u6027\uff0c\u5176\u4e2d\u5047\u8bbe\u653e\u7535\u5904\u4e8e\u5c40\u90e8\u70ed\u529b\u5b66\u5e73\u8861\u72b6\u6001\uff0c\u5c06\u7b49\u79bb\u5b50\u4f53\u4f5c\u4e3a\u4e00\u79cd\u5bfc\u7535\u6d41\u4f53\u4ecb\u8d28\uff0c\u5e76\u4f7f\u7528\u78c1\u6d41\u4f53\u52a8\u529b\u5b66\u65b9\u6cd5\u8fdb\u884c\u5efa\u6a21\u4eff\u771f\u3002\u6a21\u578b\u6f14\u793a\u4e86\u5982\u4f55\u4f7f\u7528\u201c\u78c1\u6d41\u4f53\u52a8\u529b\u5b66\uff0c\u9762\u5185\u7535\u6d41\u201d\u63a5\u53e3\u6765\u6a21\u62df\u76f4\u6d41\u7535\u5f27\u4e2d\u4ea7\u751f\u7684\u7b49\u79bb\u5b50\u4f53\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("plasma_dc_arc.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
