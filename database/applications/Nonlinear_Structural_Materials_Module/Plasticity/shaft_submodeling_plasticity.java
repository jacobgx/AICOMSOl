/*
 * shaft_submodeling_plasticity.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:35 by COMSOL 6.3.0.290. */
public class shaft_submodeling_plasticity {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Plasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.geom().create("part1", "Part", 3);
    model.geom("part1").geomRep("comsol");
    model.geom("part1").lengthUnit("mm");
    model.geom("part1").insertFile("shaft_submodeling_geom_sequence.mph", "geom1");
    model.geom("part1").run("fin");
    model.geom("part1").label("Full Geometry");

    model.component("comp1").label("Full Model");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_sel1", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selshowbnd", "pi1_sel1", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel1", "none");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepbnd", "pi1_sel2", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selshowbnd", "pi1_sel2", true);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel2", "none");
    model.component("comp1").geom("geom1").run();

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.material("mat1").propertyGroup("ElastoplasticModel").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.material("mat1").propertyGroup().create("Swift", "Swift", "Swift");
    model.material("mat1").propertyGroup().create("Voce", "Voce", "Voce");
    model.material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func().create("int1", "Interpolation");
    model.material("mat1").propertyGroup().create("Norton", "Norton", "Norton");
    model.material("mat1").propertyGroup().create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.material("mat1").label("Structural steel");
    model.material("mat1").set("family", "custom");
    model.material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.material("mat1").set("diffuse", "custom");
    model.material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.material("mat1").set("ambient", "custom");
    model.material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.material("mat1").set("noise", true);
    model.material("mat1").set("fresnel", 0.9);
    model.material("mat1").set("roughness", 0.3);
    model.material("mat1").set("diffusewrap", 0);
    model.material("mat1").set("reflectance", 0);
    model.material("mat1").propertyGroup("def").set("lossfactor", "0.02");
    model.material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.material("mat1").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.material("mat1").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.material("mat1").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.material("mat1").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.material("mat1").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Enu").func("int2").set("funcnametable", new String[][]{{"int1", "1"}});
    model.material("mat1").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.material("mat1").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.material("mat1").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.material("mat1").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Enu").set("E", "E(T)");
    model.material("mat1").propertyGroup("Enu").set("nu", "nu(T)");
    model.material("mat1").propertyGroup("Enu").addInput("temperature");
    model.material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel").set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.material("mat1").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.material("mat1").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.material("mat1").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.material("mat1").propertyGroup("Ludwik").addInput("temperature");
    model.material("mat1").propertyGroup("JohnsonCook").label("Johnson-Cook");
    model.material("mat1").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.material("mat1").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.material("mat1").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.material("mat1").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.material("mat1").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.material("mat1").propertyGroup("Voce").addInput("temperature");
    model.material("mat1").propertyGroup("HockettSherby").label("Hockett-Sherby");
    model.material("mat1").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("HockettSherby").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("HockettSherby").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.material("mat1").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.material("mat1").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.material("mat1").propertyGroup("HockettSherby").addInput("temperature");
    model.material("mat1").propertyGroup("ArmstrongFrederick").label("Armstrong-Frederick");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("fununit", new String[]{"1"});
    model.material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.material("mat1").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.material("mat1").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.material("mat1").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.material("mat1").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.material("mat1").propertyGroup("Norton").set("n_nor", "4.5");
    model.material("mat1").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.material("mat1").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.material("mat1").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.component("comp1").material().create("matlnk1", "Link");

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("geom1_pi1_sel2");
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().named("geom1_pi1_sel1");
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bndl1")
         .set("force", new String[]{"-2.5[kN]", "0", "-0.9[kN]"});

    model.component("comp1").mesh("mesh1").autoMeshSize(8);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("Full Model");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("Stress (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("colortable", "Rainbow");
    model.result("pg1").feature("vol1").set("colortabletrans", "none");
    model.result("pg1").feature("vol1").set("colorscalemode", "linear");
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "Displacement field");
    model.result("pg1").run();
    model.result("pg1").label("Stress - Full Model");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("unit", "MPa");
    model.result("pg1").feature("vol1").set("resolution", "norefine");
    model.result("pg1").feature("vol1").set("smooth", "none");
    model.result("pg1").run();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("quickplane", "xz");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("Cut Plane Stress - Full Model");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "solid.mises");
    model.result("pg2").feature("surf1").set("unit", "MPa");
    model.result("pg2").feature("surf1").set("resolution", "norefine");
    model.result("pg2").feature("surf1").set("smooth", "none");
    model.result("pg2").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").label("Submodel");

    model.geom().create("part2", "Part", 3);
    model.geom("part2").geomRep("comsol");
    model.geom("part2").label("Submodel Cut");
    model.geom("part2").lengthUnit("mm");
    model.geom("part2").create("cyl1", "Cylinder");
    model.geom("part2").feature("cyl1").set("r", 7);
    model.geom("part2").feature("cyl1").set("h", 8);
    model.geom("part2").feature("cyl1").set("pos", new int[]{26, 0, 0});
    model.geom("part2").feature("cyl1").set("axistype", "x");
    model.geom("part2").run("cyl1");
    model.geom("part2").create("blk1", "Block");
    model.geom("part2").feature("blk1").set("size", new int[]{10, 14, 10});
    model.geom("part2").feature("blk1").set("pos", new int[]{26, -7, 0});
    model.geom("part2").run("blk1");
    model.geom("part2").create("int1", "Intersection");
    model.geom("part2").feature("int1").label("Submodel Cut");
    model.geom("part2").feature("int1").selection("input").set("blk1", "cyl1");
    model.geom("part2").feature("int1").set("selresult", true);
    model.geom("part2").feature("int1").set("selresultshow", "bnd");
    model.component("comp2").geom("geom2").lengthUnit("mm");
    model.component("comp2").geom("geom2").create("pi1", "PartInstance");
    model.component("comp2").geom("geom2").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp2").geom("geom2").feature("pi1").set("part", "part1");
    model.component("comp2").geom("geom2").run("pi1");
    model.component("comp2").geom("geom2").create("pi2", "PartInstance");
    model.component("comp2").geom("geom2").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp2").geom("geom2").feature("pi2").set("part", "part2");
    model.component("comp2").geom("geom2").run("pi2");
    model.component("comp2").geom("geom2").feature("pi2").setEntry("selkeepbnd", "pi2_int1.bnd", true);
    model.component("comp2").geom("geom2").feature("pi2").setEntry("selshowbnd", "pi2_int1.bnd", true);
    model.component("comp2").geom("geom2").feature("pi2").setEntry("selcontributetobnd", "pi2_int1.bnd", "none");
    model.component("comp2").geom("geom2").create("int1", "Intersection");
    model.component("comp2").geom("geom2").feature("int1").selection("input").set("pi1", "pi2");
    model.component("comp2").geom("geom2").runPre("fin");
    model.component("comp2").geom("geom2").create("wp1", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp1").set("unite", true);
    model.component("comp2").geom("geom2").feature("wp1").set("quickplane", "zx");
    model.component("comp2").geom("geom2").run("wp1");
    model.component("comp2").geom("geom2").create("par1", "Partition");
    model.component("comp2").geom("geom2").feature("par1").selection("input").set("int1");
    model.component("comp2").geom("geom2").feature("par1").set("partitionwith", "workplane");
    model.component("comp2").geom("geom2").run("fin");
    model.component("comp2").geom("geom2").create("mcd1", "MeshControlDomains");
    model.component("comp2").geom("geom2").feature("mcd1").selection("input").set("fin", 1, 2);
    model.component("comp2").geom("geom2").run("mcd1");

    model.component("comp2").material().create("matlnk2", "Link");

    model.component("comp2").physics().create("solid2", "SolidMechanics", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/solid2", true);

    model.component("comp1").cpl().create("genext1", "GeneralExtrusion");
    model.component("comp1").cpl("genext1").selection().set(1);
    model.component("comp1").cpl("genext1").set("dstmap", new String[]{"X", "Y", "Z"});
    model.component("comp1").cpl("genext1").set("srcframe", "material");

    model.component("comp2").physics("solid2").create("disp1", "Displacement2", 2);
    model.component("comp2").physics("solid2").feature("disp1").selection().named("geom2_pi2_int1_bnd");
    model.component("comp2").physics("solid2").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp2").physics("solid2").feature("disp1").setIndex("U0", "comp1.genext1(comp1.u)", 0);
    model.component("comp2").physics("solid2").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp2").physics("solid2").feature("disp1").setIndex("U0", "comp1.genext1(comp1.v)", 1);
    model.component("comp2").physics("solid2").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp2").physics("solid2").feature("disp1").setIndex("U0", "comp1.genext1(comp1.w)", 2);

    model.component("comp2").mesh("mesh2").create("map1", "Map");
    model.component("comp2").mesh("mesh2").feature("map1").selection().set(2);
    model.component("comp2").mesh("mesh2").feature("map1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").selection().set(1, 3);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("elemcount", 8);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("elemratio", 4);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("reverse", true);
    model.component("comp2").mesh("mesh2").feature("map1").create("dis2", "Distribution");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis2").selection().set(13);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis2").set("elemcount", 8);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis2").set("elemratio", 4);
    model.component("comp2").mesh("mesh2").feature("map1").create("dis3", "Distribution");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis3").selection().set(15);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis3").set("numelem", 6);
    model.component("comp2").mesh("mesh2").feature("map1").create("dis4", "Distribution");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis4").selection().set(10);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis4").set("type", "predefined");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis4").set("elemcount", 12);
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis4").set("elemratio", 2);
    model.component("comp2").mesh("mesh2").create("swe1", "Sweep");
    model.component("comp2").mesh("mesh2").feature("swe1").set("smoothcontrol", false);
    model.component("comp2").mesh("mesh2").feature("swe1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh2").feature("swe1").feature("dis1").set("numelem", 16);
    model.component("comp2").mesh("mesh2").run();
    model.component("comp2").mesh("mesh2").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std2").feature("stat").setSolveFor("/physics/solid2", true);
    model.study("std2").label("Submodel");
    model.study("std2").feature("stat").set("usesol", true);
    model.study("std2").feature("stat").set("notsolmethod", "sol");
    model.study("std2").feature("stat").set("notstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").label("Stress (solid2)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("expr", new String[]{"solid2.misesGp"});
    model.result("pg3").feature("vol1").set("threshold", "manual");
    model.result("pg3").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("vol1").set("colortable", "Rainbow");
    model.result("pg3").feature("vol1").set("colortabletrans", "none");
    model.result("pg3").feature("vol1").set("colorscalemode", "linear");
    model.result("pg3").feature("vol1").set("resolution", "custom");
    model.result("pg3").feature("vol1").set("refine", 2);
    model.result("pg3").feature("vol1").set("colortable", "Prism");
    model.result("pg3").feature("vol1").create("def", "Deform");
    model.result("pg3").feature("vol1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg3").feature("vol1").feature("def").set("descr", "Displacement field");
    model.result("pg3").run();
    model.result("pg3").label("Stress - Submodel");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").run();
    model.result("pg3").feature("vol1").set("unit", "MPa");
    model.result("pg3").feature("vol1").set("resolution", "norefine");
    model.result("pg3").feature("vol1").set("smooth", "none");
    model.result("pg3").run();
    model.result().dataset().create("cpl2", "CutPlane");
    model.result().dataset("cpl2").set("quickplane", "xz");
    model.result().dataset("cpl2").set("data", "dset3");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("Cut Plane Stress - Submodel");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").set("data", "cpl2");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "solid2.mises");
    model.result("pg4").feature("surf1").set("unit", "MPa");
    model.result("pg4").feature("surf1").set("resolution", "norefine");
    model.result("pg4").feature("surf1").set("smooth", "none");
    model.result("pg4").run();
    model.result().dataset().create("cpl3", "CutPlane");
    model.result().dataset("cpl3").set("quickx", 26);
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("Stress Comparison");
    model.result("pg5").set("data", "none");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("data", "cpl3");
    model.result("pg5").feature("surf1").set("expr", "solid.misesGp");
    model.result("pg5").feature("surf1").set("unit", "MPa");
    model.result("pg5").feature("surf1").set("resolution", "norefine");
    model.result("pg5").feature("surf1").set("smooth", "none");
    model.result("pg5").run();
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").set("data", "dset3");
    model.result().dataset("surf1").selection().set(1);
    model.result().dataset("surf1").set("param", "yz");
    model.result("pg5").run();
    model.result("pg5").create("surf2", "Surface");
    model.result("pg5").feature("surf2").set("data", "surf1");
    model.result("pg5").feature("surf2").set("expr", "solid2.misesGp");
    model.result("pg5").feature("surf2").set("unit", "MPa");
    model.result("pg5").feature("surf2").set("resolution", "norefine");
    model.result("pg5").feature("surf2").set("smooth", "none");
    model.result("pg5").feature("surf2").set("inheritplot", "surf1");
    model.result("pg5").feature("surf2").create("trn1", "Transformation");
    model.result("pg5").run();
    model.result("pg5").feature("surf2").feature("trn1").set("move", new int[]{11, 0});
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("surf3", "surf2");
    model.result("pg5").run();
    model.result("pg5").feature("surf3").set("expr", "solid2.misesGp-comp1.genext1(solid.misesGp)");
    model.result("pg5").feature("surf3").set("inheritplot", "none");
    model.result("pg5").feature("surf3").set("colortable", "Wave");
    model.result("pg5").feature("surf3").set("colorscalemode", "linearsymmetric");
    model.result("pg5").run();
    model.result("pg5").feature("surf3").feature("trn1").set("move", new int[]{22, 0});
    model.result("pg5").run();

    model.study("std1").feature("stat").setSolveFor("/physics/solid2", false);

    model.result("pg3").run();

    model.title("\u66f2\u8f74\u5b50\u6a21\u578b\u5206\u6790");

    model
         .description("\u5728\u65e0\u6cd5\u89e3\u6790\u5168\u5c40\u6a21\u578b\u4e2d\u590d\u6742\u51e0\u4f55\u7684\u6240\u6709\u7ec6\u8282\u65f6\uff0c\u53ef\u4ee5\u4f7f\u7528\u5b50\u6a21\u578b\u3002\u672c\u4f8b\u4f7f\u7528\u5168\u5c40\u6a21\u578b\u8ba1\u7b97\u4e00\u4e2a\u8fd1\u4f3c\u89e3\uff0c\u5206\u6790\u7ed3\u679c\u7528\u4e8e\u8be6\u7ec6\u5b50\u6a21\u578b\u7684\u8fb9\u754c\u6761\u4ef6\u3002\n\n\u672c\u4f8b\u4e2d\uff0c\u8fd9\u79cd\u6280\u672f\u7528\u4e8e\u5bf9\u7ed3\u6784\u529b\u5b66\u95ee\u9898\u8fdb\u884c\u7cbe\u786e\u5e94\u529b\u8ba1\u7b97\u3002");

    model.label("shaft_submodeling.mph");

    model.result("pg3").run();
    model.result("pg3").create("con1", "Contour");
    model.result("pg3").feature("con1").set("expr", "solid2.misesGp/material.ElastoplasticModel.sigmags");
    model.result("pg3").feature("con1").set("titletype", "none");
    model.result("pg3").feature("con1").set("levelmethod", "levels");
    model.result("pg3").feature("con1").set("levels", 1);
    model.result("pg3").feature("con1").set("colorlegend", false);
    model.result("pg3").feature("con1").set("coloring", "uniform");
    model.result("pg3").feature("con1").set("color", "cyan");
    model.result("pg3").feature("con1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("con1").set("inheritcolor", false);
    model.result("pg3").feature("con1").set("inheritrange", false);
    model.result("pg3").feature("con1").set("inherittransparency", false);
    model.result("pg3").feature("con1").set("inherittubescale", false);
    model.result("pg3").feature("con1").set("inheritplot", "vol1");

    model.component("comp2").view("view5").set("showgrid", false);

    model.result("pg3").run();

    model.param().set("lp", "0");
    model.param().descr("lp", "\u8f7d\u8377\u4e58\u6570");

    model.component("comp1").physics("solid").feature("bndl1")
         .set("force", new String[]{"-2.5[kN]*lp", "0", "-0.9[kN]*lp"});

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "lp", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "lp", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "0 1", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.component("comp2").physics("solid2").feature("lemm1").create("plsty1", "Plasticity", 3);

    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "lp", 0);

    return model;
  }

  public static Model run2(Model model) {
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("pname", "lp", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0.75,0.05,1)", 0);
    model.study("std2").feature("stat").set("notsolnum", "all");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("con2", "con1");
    model.result("pg3").run();
    model.result("pg3").feature("con2").set("data", "dset3");
    model.result("pg3").feature("con2").setIndex("looplevel", 1, 0);
    model.result("pg3").feature("con2").set("expr", "solid2.misesGp/material.ElastoplasticModel.sigmags/0.75");
    model.result("pg3").feature("con2").set("color", "yellow");
    model.result("pg3").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u5851\u6027\u5e94\u53d8");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").stepLast(0);
    model.result("pg6").run();
    model.result("pg6").create("vol1", "Volume");
    model.result("pg6").feature("vol1").set("expr", "solid2.epeGp");
    model.result("pg6").feature("vol1").set("colortable", "Prism");
    model.result("pg6").feature("vol1").create("filt1", "Filter");
    model.result("pg6").run();
    model.result("pg6").feature("vol1").feature("filt1").set("expr", "Y>-0.0001");
    model.result("pg6").feature("vol1").feature("filt1").set("nodespec", "all");
    model.result("pg6").run();
    model.result("pg6").set("edges", false);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").set("data", "dset3");
    model.result().numerical("int1").selection().set(1);
    model.result().numerical("int1").setIndex("expr", "-solid2.RFz/0.9[kN]/lp*2", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8868\u9762\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "none");
    model.result("pg7").create("tblp1", "Table");
    model.result("pg7").feature("tblp1").set("source", "table");
    model.result("pg7").feature("tblp1").set("table", "tbl1");
    model.result("pg7").feature("tblp1").set("linewidth", "preference");
    model.result("pg7").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").label("\u8bef\u5dee\u4f30\u8ba1");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u76f8\u5bf9\u521a\u5ea6");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg6").run();
    model.result("pg6").run();

    model.title("\u5851\u6027\u5b50\u6a21\u578b\u5efa\u6a21\u65b9\u6cd5");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u6f14\u793a\u5982\u4f55\u5728\u5b50\u6a21\u578b\u4e2d\u52a0\u5165\u5c40\u90e8\u975e\u7ebf\u6027\u3002\n\n\u5728\u672c\u4f8b\u4e2d\uff0c\u521d\u59cb\u5f39\u6027\u5206\u6790\u8868\u660e\uff0c\u7ed3\u6784\u7684\u4e00\u5c0f\u90e8\u5206\u533a\u57df\u7684\u5e94\u529b\u9ad8\u4e8e\u5c48\u670d\u6781\u9650\u3002\u4e3a\u4e86\u6539\u8fdb\u7ed3\u679c\uff0c\u6211\u4eec\u5728\u5b50\u6a21\u578b\u4e2d\u6dfb\u52a0\u4e86\u5f39\u5851\u6027\u6750\u6599\u6a21\u578b\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u7ebf\u5f39\u6027\u6765\u5206\u6790\u5168\u5c40\u6a21\u578b\uff0c\u4f46\u8fd9\u5e76\u4e0d\u662f\u5fc5\u8981\u7684\u5047\u8bbe\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("shaft_submodeling_plasticity.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
