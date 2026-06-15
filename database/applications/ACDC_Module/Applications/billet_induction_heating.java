/*
 * billet_induction_heating.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:14 by COMSOL 6.3.0.290. */
public class billet_induction_heating {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");
    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid");
    model.component("comp1").physics("mf").feature("als1").selection().all();
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.component("comp1").multiphysics().create("emh1", "ElectromagneticHeating", 3);
    model.component("comp1").multiphysics("emh1").set("EMHeat_physics", "mf");
    model.component("comp1").multiphysics("emh1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("emh1").selection().all();

    model.study().create("std1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("h_coil", "15[cm]", "Length of the coil");
    model.param().set("r_coil", "10[cm]", "Inner coil radius");
    model.param().set("R_coil", "13[cm]", "Outer coil radius");
    model.param().set("T_in", "400[degC]", "Initial billet temperature");
    model.param().set("N_coil", "10000", "Number of coil turns");
    model.param().set("u_trans", "4[cm/s]", "Billet translational velocity");
    model.param().set("T_amb", "20[degC]", "Ambient temperature");
    model.param().set("emissivity", "0.5", "Billet surface emissivity");
    model.param().set("dx_part", "0[m]", "Displacement (x)");
    model.param().set("dy_part", "0[m]", "Displacement (y)");
    model.param().set("rot_part", "0", "Rotation");
    model.param().set("f0", "50[Hz]", "AC current frequency");

    model.geom().create("part1", "Part", 2);
    model.geom("part1").label("Rectangular");
    model.geom("part1").inputParam().set("a", "12[cm]");
    model.geom("part1").inputParam().descr("a", "Width");
    model.geom("part1").inputParam().set("b", "12[cm]");
    model.geom("part1").inputParam().descr("b", "Height");
    model.geom("part1").create("r1", "Rectangle");
    model.geom("part1").feature("r1").set("size", new String[]{"a", "b"});
    model.geom("part1").feature("r1").set("base", "center");
    model.geom("part1").feature("r1").set("selresult", true);
    model.geom("part1").feature("r1").set("color", "custom");
    model.geom("part1").feature("r1")
         .set("customcolor", new double[]{0.3529411852359772, 0.3529411852359772, 0.3529411852359772});
    model.geom().create("part2", "Part", 2);
    model.geom("part2").label("Circular");
    model.geom("part2").inputParam().set("r", "6[cm]");
    model.geom("part2").inputParam().descr("r", "Radius");
    model.geom("part2").create("c1", "Circle");
    model.geom("part2").feature("c1").set("r", "r");
    model.geom("part2").feature("c1").set("selresult", true);
    model.geom("part2").feature("c1").set("color", "custom");
    model.geom("part2").feature("c1")
         .set("customcolor", new double[]{0.3529411852359772, 0.3529411852359772, 0.3529411852359772});
    model.geom().create("part3", "Part", 3);
    model.geom("part3").geomRep("comsol");
    model.geom("part3").label("Coil");
    model.geom("part3").create("cyl1", "Cylinder");
    model.geom("part3").feature("cyl1").set("r", "r_coil");
    model.geom("part3").feature("cyl1").set("h", "h_coil");
    model.geom("part3").feature("cyl1").set("axistype", "x");
    model.geom("part3").run("cyl1");
    model.geom("part3").create("cyl2", "Cylinder");
    model.geom("part3").feature("cyl2").set("r", "R_coil");
    model.geom("part3").feature("cyl2").set("h", "h_coil");
    model.geom("part3").feature("cyl2").set("axistype", "x");
    model.geom("part3").run("cyl2");
    model.geom("part3").create("dif1", "Difference");
    model.geom("part3").feature("dif1").selection("input").set("cyl2");
    model.geom("part3").feature("dif1").selection("input2").set("cyl1");
    model.geom("part3").feature("dif1").set("selresult", true);
    model.geom("part3").feature("dif1").set("color", "custom");
    model.geom("part3").feature("dif1")
         .set("customcolor", new double[]{0.8823529481887817, 0.8823529481887817, 0.8823529481887817});
    model.geom("part3").feature("dif1").label("Coil");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "147.5[cm]");
    model.component("comp1").geom("geom1").feature("sph1").set("pos", new String[]{"(175/2)[cm]", "0", "0"});
    model.component("comp1").geom("geom1").feature("sph1").setIndex("layer", "25[cm]", 0);
    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pi1").label("Part");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pi1")
         .set("displ", new String[]{"dx_part", "dy_part"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pi1").set("rot", "rot_part");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pi1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").selection("input").set("pi1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "20[cm]", 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "35[cm]", 1);
    model.component("comp1").geom("geom1").feature("ext1").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "65[cm]", 2);
    model.component("comp1").geom("geom1").feature("ext1")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "80[cm]", 3);
    model.component("comp1").geom("geom1").feature("ext1")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "110[cm]", 4);
    model.component("comp1").geom("geom1").feature("ext1")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0", "0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "125[cm]", 5);
    model.component("comp1").geom("geom1").feature("ext1")
         .set("displ", new String[][]{{"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1")
         .set("scale", new String[][]{{"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0", "0", "0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "175[cm]", 6);
    model.component("comp1").geom("geom1").feature("ext1")
         .set("displ", new String[][]{{"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}, 
         {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1")
         .set("scale", new String[][]{{"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}, 
         {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1")
         .set("twist", new String[]{"0", "0", "0", "0", "0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("ext1").set("color", "custom");
    model.component("comp1").geom("geom1").feature("ext1")
         .set("customcolor", new double[]{0.3529411852359772, 0.3529411852359772, 0.3529411852359772});
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("Billet");
    model.component("comp1").geom("geom1").feature("ext1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part3");
    model.component("comp1").geom("geom1").feature("pi1").set("displ", new String[]{"20[cm]", "0", "0"});
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selkeepdom", "pi1_dif1.dom", true);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("Coils");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetodom", "pi1_dif1.dom", "csel2");
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part3");
    model.component("comp1").geom("geom1").feature("pi2")
         .set("displ", new String[]{"20[cm]+h_coil+30[cm]", "0", "0"});
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepdom", "pi2_dif1.dom", true);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selcontributetodom", "pi2_dif1.dom", "csel2");
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").create("pi3", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi3").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi3").set("part", "part3");
    model.component("comp1").geom("geom1").feature("pi3")
         .set("displ", new String[]{"20[cm]+h_coil+30[cm]+h_coil+30[cm]", "0", "0"});
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selkeepdom", "pi3_dif1.dom", true);
    model.component("comp1").geom("geom1").feature("pi3").setEntry("selcontributetodom", "pi3_dif1.dom", "csel2");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(13);
    model.component("comp1").selection("sel1").label("Inlet Boundary");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(94);
    model.component("comp1").selection("sel2").label("Outlet Boundary");
    model.component("comp1").selection().create("int1", "Intersection");
    model.component("comp1").selection("int1").label("Billet Inlet");
    model.component("comp1").selection("int1").set("entitydim", 2);
    model.component("comp1").selection("int1").set("input", new String[]{"sel1", "geom1_csel1_bnd"});
    model.component("comp1").selection().create("int2", "Intersection");
    model.component("comp1").selection("int2").label("Billet Outlet");
    model.component("comp1").selection("int2").set("entitydim", 2);
    model.component("comp1").selection("int2").set("input", new String[]{"sel2", "geom1_csel1_bnd"});
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("Billet Exterior Boundaries");
    model.component("comp1").selection("adj1").set("input", new String[]{"geom1_csel1_dom"});
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("Radiating Boundaries");
    model.component("comp1").selection("dif1").set("entitydim", 2);
    model.component("comp1").selection("dif1").set("add", new String[]{"adj1"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"sel1", "int2"});
    model.component("comp1").selection().create("box1", "Box");
    model.component("comp1").selection("box1").label("All Domains");
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("Billet and Coils");
    model.component("comp1").selection("uni1").set("input", new String[]{"geom1_csel1_dom", "geom1_csel2_dom"});
    model.component("comp1").selection().create("dif2", "Difference");
    model.component("comp1").selection("dif2").set("add", new String[]{"box1"});
    model.component("comp1").selection("dif2").set("subtract", new String[]{"uni1"});
    model.component("comp1").selection("dif2").label("Hidden Domains");
    model.component("comp1").selection().create("adj2", "Adjacent");
    model.component("comp1").selection("adj2").set("outputdim", 3);
    model.component("comp1").selection("adj2").set("input", new String[]{"uni1"});
    model.component("comp1").selection("adj2").label("Air domain");
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").set("input", new String[]{"uni1", "adj2"});
    model.component("comp1").selection("uni2").label("Free tetrahedral domains");
    model.component("comp1").selection().create("adj3", "Adjacent");
    model.component("comp1").selection("adj3").label("Swept Mesh Destination Boundaries");
    model.component("comp1").selection("adj3").set("input", new String[]{"uni1", "dif2"});
    model.component("comp1").selection().create("adj4", "Adjacent");
    model.component("comp1").selection("adj4").label("Sphere Inner Boundaries");
    model.component("comp1").selection("adj4").set("entitydim", 2);
    model.component("comp1").selection("adj4").set("input", new String[]{"adj3"});
    model.component("comp1").selection().create("adj5", "Adjacent");
    model.component("comp1").selection("adj5").label("Swept Mesh Source Boundaries");
    model.component("comp1").selection("adj5").set("entitydim", 2);
    model.component("comp1").selection("adj5").set("input", new String[]{"adj3", "adj4"});
    model.component("comp1").selection().create("adj6", "Adjacent");
    model.component("comp1").selection("adj6").label("IE domains");
    model.component("comp1").selection("adj6").set("entitydim", 2);
    model.component("comp1").selection("adj6").set("outputdim", 3);
    model.component("comp1").selection("adj6").set("input", new String[]{"adj3"});

    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");
    model.component("comp1").coordSystem("ie1").selection().named("adj6");
    model.component("comp1").coordSystem("ie1").set("ScalingType", "Spherical");

    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").named("dif2");

    model.component("comp1").physics("mf").feature("als1").selection().named("geom1_csel1_dom");
    model.component("comp1").physics("mf").create("coil1", "Coil", 3);
    model.component("comp1").physics("mf").feature("coil1").selection().named("geom1_pi1_dif1_dom");
    model.component("comp1").physics("mf").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil1").set("CoilType", "UserDefined");
    model.component("comp1").physics("mf").feature("coil1").set("ICoil", "40[A]");
    model.component("comp1").physics("mf").feature("coil1").set("N", "N_coil");
    model.component("comp1").physics("mf").feature("coil1").feature("cg1")
         .set("eCoil", new String[]{"0", "z/sqrt(y^2+z^2)", "-y/sqrt(y^2+z^2)"});
    model.component("comp1").physics("mf").feature("coil1").feature("cg1").set("length", "(R_coil+r_coil)*pi");
    model.component("comp1").physics("mf").feature().duplicate("coil2", "coil1");
    model.component("comp1").physics("mf").feature("coil2").selection().named("geom1_pi2_dif1_dom");
    model.component("comp1").physics("mf").feature("coil2").set("ICoil", "30[A]");
    model.component("comp1").physics("mf").feature().duplicate("coil3", "coil2");
    model.component("comp1").physics("mf").feature("coil3").selection().named("geom1_pi3_dif1_dom");
    model.component("comp1").physics("ht").create("solidtrm1", "SolidWithTranslationalMotion", 3);
    model.component("comp1").physics("ht").feature("solidtrm1").selection().all();
    model.component("comp1").physics("ht").feature("solidtrm1").feature("trm1")
         .set("u", new String[]{"u_trans", "0", "0"});
    model.component("comp1").physics("ht").feature("init1").set("Tinit", "T_in");
    model.component("comp1").physics("ht").selection().named("geom1_csel1_dom");
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().named("dif1");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", 5);
    model.component("comp1").physics("ht").feature("hf1").set("Text", "T_amb");
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp1").selection().named("int1");
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T_in");
    model.component("comp1").physics("ht").create("sar1", "SurfaceToAmbientRadiation", 2);
    model.component("comp1").physics("ht").feature("sar1").selection().named("dif1");
    model.component("comp1").physics("ht").feature("sar1").set("Tamb", "T_amb");
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad_mat", "userdef");
    model.component("comp1").physics("ht").feature("sar1").set("epsilon_rad", "emissivity");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat1").label("Copper");
    model.component("comp1").material("mat1").set("family", "copper");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Steel AISI 4340");
    model.component("comp1").material("mat2").set("family", "steel");
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
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat1").selection().named("geom1_csel2_dom");
    model.component("comp1").material("mat2").selection().named("geom1_csel1_dom");

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("geom1_csel1_dom");
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "r_coil/4");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().named("uni2");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection("sourceface").named("adj5");
    model.component("comp1").mesh("mesh1").feature("swe1").selection("targetface").named("adj3");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").label("Billet Cross Section");

    model.component("comp2").geom("geom2").create("pi1", "PartInstance");
    model.component("comp2").geom("geom2").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp2").geom("geom2").feature("pi1").set("part", "part1");
    model.component("comp2").geom("geom2").feature("pi1").label("Part");
    model.component("comp2").geom("geom2").feature("pi1").set("displ", new String[]{"dx_part", "dy_part"});
    model.component("comp2").geom("geom2").feature("pi1").set("rot", "rot_part");
    model.component("comp2").geom("geom2").feature("pi1").setEntry("selkeepdom", "pi1_r1.dom", true);
    model.component("comp2").geom("geom2").run("pi1");
    model.component("comp2").geom("geom2").create("c1", "Circle");
    model.component("comp2").geom("geom2").feature("c1").set("r", "r_coil");
    model.component("comp2").geom("geom2").run("c1");
    model.component("comp2").geom("geom2").create("c2", "Circle");
    model.component("comp2").geom("geom2").feature("c2").set("r", "R_coil");
    model.component("comp2").geom("geom2").run("c2");
    model.component("comp2").geom("geom2").create("dif1", "Difference");
    model.component("comp2").geom("geom2").feature("dif1").selection("input").set("c2");
    model.component("comp2").geom("geom2").feature("dif1").selection("input2").set("c1");
    model.component("comp2").geom("geom2").feature("dif1").set("selresult", true);
    model.component("comp2").geom("geom2").feature("dif1").set("color", "custom");
    model.component("comp2").geom("geom2").feature("dif1")
         .set("customcolor", new double[]{0.8823529481887817, 0.8823529481887817, 0.8823529481887817});
    model.component("comp2").geom("geom2").run("fin");

    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mf", false);
    model.study("std1").showAutoSequences("all");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"temperature", "Temperature", "K", "K"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "\u00b0C", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").selection().named("int2");
    model.result().dataset("dset1").selection().geom("geom1", 3);
    model.result().dataset("dset1").selection().named("uni1");
    model.result().dataset("dset2").selection().geom("geom1", 3);
    model.result().dataset("dset2").selection().named("uni1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("Temperature (Slices)");
    model.result("pg1").set("edgecolor", "gray");
    model.result("pg1").set("view", "new");
    model.result("pg1").set("titletype", "custom");
    model.result("pg1").set("typeintitle", false);
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", "T");
    model.result("pg1").feature("mslc1").set("descr", "Temperature");
    model.result("pg1").feature("mslc1").set("xnumber", "8");
    model.result("pg1").feature("mslc1").set("znumber", "0");
    model.result("pg1").feature("mslc1").set("colortable", "HeatCamera");
    model.result("pg1").run();
    model.result("pg1").create("mmv1", "MaxMinVolume");
    model.result("pg1").feature("mmv1").set("expr", "T");
    model.result("pg1").feature("mmv1").set("descr", "Temperature");
    model.result("pg1").feature("mmv1").set("titletype", "none");

    model.view("view7").set("showgrid", false);
    model.view("view7").set("showaxisorientation", false);

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("Current Density Norm");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("edgecolor", "gray");
    model.result("pg2").set("view", "view7");
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").set("solutionintitle", false);
    model.result("pg2").set("typeintitle", false);
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", "mf.normJ");
    model.result("pg2").feature("mslc1").set("descr", "Current density norm");
    model.result("pg2").feature("mslc1").set("xnumber", "8");
    model.result("pg2").feature("mslc1").set("znumber", "0");
    model.result("pg2").feature("mslc1").create("sel1", "Selection");
    model.result("pg2").feature("mslc1").feature("sel1").selection().named("geom1_csel1_dom");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("Temperature, Cross-Section at Outlet");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").set("typeintitle", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "T");
    model.result("pg3").feature("surf1").set("descr", "Temperature");
    model.result("pg3").feature("surf1").set("colortable", "HeatCamera");
    model.result("pg3").run();
    model.result("pg3").create("mms1", "MaxMinSurface");
    model.result("pg3").feature("mms1").set("expr", "T");
    model.result("pg3").feature("mms1").set("descr", "Temperature");
    model.result("pg3").feature("mms1").set("color", "gray");
    model.result("pg3").feature("mms1").set("backgroundcolor", "black");
    model.result("pg3").feature("mms1").set("titletype", "none");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("Temperature (Surface)");
    model.result("pg4").set("edges", false);
    model.result("pg4").set("view", "view7");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "T");
    model.result("pg4").feature("surf1").set("colortable", "HeatCamera");
    model.result("pg4").run();
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", "1");
    model.result("pg4").feature("vol1").set("titletype", "none");
    model.result("pg4").feature("vol1").set("coloring", "uniform");
    model.result("pg4").feature("vol1").set("color", "custom");
    model.result("pg4").feature("vol1")
         .set("customcolor", new double[]{0.8823529481887817, 0.8823529481887817, 0.8823529481887817});
    model.result("pg4").feature("vol1").create("sel1", "Selection");
    model.result("pg4").feature("vol1").feature("sel1").selection().named("geom1_csel2_dom");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg4").run();
    model.result("pg4").set("titletype", "custom");
    model.result("pg4").set("typeintitle", false);
    model.result().numerical().create("av1", "AvSurface");
    model.result().numerical("av1").set("intvolume", true);
    model.result().numerical("av1").set("data", "surf1");
    model.result().numerical("av1").label("Average Outlet Temperature");
    model.result().numerical("av1").setIndex("unit", "degC", 0);
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("Total Coil Power");
    model.result().numerical("gev1").setIndex("expr", "mf.PCoil_1+mf.PCoil_2+mf.PCoil_3", 0);
    model.result().numerical("gev1").setIndex("unit", "kW", 0);
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical().create("max1", "MaxVolume");
    model.result().numerical("max1").label("Maximum Billet Temperature");
    model.result().numerical("max1").selection().named("geom1_csel1_dom");
    model.result().numerical("max1").set("expr", new String[]{"T"});
    model.result().numerical("max1").set("descr", new String[]{"Temperature"});
    model.result().numerical("max1").set("unit", new String[]{"\u00b0C"});
    model.result().numerical("max1").setIndex("unit", "degC", 0);
    model.result().numerical().create("min1", "MinVolume");
    model.result().numerical("min1").label("Minimum Billet Temperature");
    model.result().numerical("min1").selection().named("geom1_csel1_dom");
    model.result().numerical("min1").set("expr", new String[]{"T"});
    model.result().numerical("min1").set("descr", new String[]{"Temperature"});
    model.result().numerical("min1").set("unit", new String[]{"\u00b0C"});
    model.result().numerical("min1").setIndex("unit", "degC", 0);
    model.result().numerical().create("max2", "MaxSurface");
    model.result().numerical("max2").label("Maximum Outlet Temperature");
    model.result().numerical("max2").selection().named("int2");
    model.result().numerical("max2").set("expr", new String[]{"T"});
    model.result().numerical("max2").set("descr", new String[]{"Temperature"});
    model.result().numerical("max2").set("unit", new String[]{"\u00b0C"});
    model.result().numerical("max2").setIndex("unit", "degC", 0);
    model.result().numerical().create("min2", "MinSurface");
    model.result().numerical("min2").label("Minimum Outlet Temperature");
    model.result().numerical("min2").selection().named("int2");
    model.result().numerical("min2").set("expr", new String[]{"T"});
    model.result().numerical("min2").set("descr", new String[]{"Temperature"});
    model.result().numerical("min2").set("unit", new String[]{"\u00b0C"});
    model.result().numerical("min2").setIndex("unit", "degC", 0);
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").label("Inward Power");
    model.result().numerical("int1").selection().named("int1");
    model.result().numerical("int1").setIndex("expr", "-ht.ntflux", 0);
    model.result().numerical("int1").setIndex("unit", "kW", 0);
    model.result().numerical().create("int2", "IntSurface");
    model.result().numerical("int2").set("intvolume", true);
    model.result().numerical("int2").label("Outward Power");
    model.result().numerical("int2").selection().named("int2");
    model.result().numerical("int2").setIndex("expr", "ht.ntflux", 0);
    model.result().numerical("int2").setIndex("unit", "kW", 0);
    model.result().numerical().create("int3", "IntSurface");
    model.result().numerical("int3").set("intvolume", true);
    model.result().numerical("int3").label("Dissipated Power");
    model.result().numerical("int3").selection().named("dif1");
    model.result().numerical("int3").set("expr", new String[]{"ht.ntflux"});
    model.result().numerical("int3").set("descr", new String[]{"Normal total heat flux"});
    model.result().numerical("int3").set("unit", new String[]{"W"});
    model.result().numerical("int3").setIndex("unit", "kW", 0);
    model.result("pg4").run();
    model.result("pg4").run();

    model.title(null);

    model.description("");

    model.label("billet_induction_heating_embedded.mph");

    model.result("pg4").run();

    model.setExpectedComputationTime("30 s");

    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1").label("\u94a2\u576f\u611f\u5e94\u52a0\u70ed");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u8f6f\u4ef6\u4fe1\u606f");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("\u8f93\u5165");
    model.result().report("rpt1").feature("sec2").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 7, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 8, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 9, 1);
    model.result().report("rpt1").feature("sec2").feature("param1").setIndex("children", false, 10, 1);
    model.result().report("rpt1").feature("sec2").feature().create("geom1", "Geometry");
    model.result().report("rpt1").feature("sec2").feature("geom1").set("includeunits", false);
    model.result().report("rpt1").feature("sec2").feature("geom1").set("includestats", false);
    model.result().report("rpt1").feature("sec2").feature("geom1").set("noderef", "geom2");
    model.result().report("rpt1").feature("sec2").feature("geom1").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec2").feature("geom1").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec2").feature("geom1").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec2").feature("geom1").setIndex("children", false, 3, 1);
    model.result().report("rpt1").feature("sec2").feature().create("geom2", "Geometry");
    model.result().report("rpt1").feature("sec2").feature("geom2").set("includeunits", false);
    model.result().report("rpt1").feature("sec2").feature("geom2").set("includestats", false);
    model.result().report("rpt1").feature("sec2").feature("geom2").setIndex("children", false, 0, 1);
    model.result().report("rpt1").feature("sec2").feature("geom2").setIndex("children", false, 1, 1);
    model.result().report("rpt1").feature("sec2").feature("geom2").setIndex("children", false, 2, 1);
    model.result().report("rpt1").feature("sec2").feature("geom2").setIndex("children", false, 6, 1);
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("\u7ed3\u679c");
    model.result().report("rpt1").feature("sec3").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature().create("pg2", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("pg2").set("noderef", "pg2");
    model.result().report("rpt1").feature("sec3").feature().create("pg3", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("pg3").set("noderef", "pg3");
    model.result().report("rpt1").feature("sec3").feature().create("pg4", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("pg4").set("noderef", "pg4");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").feature("sec2").feature().create("field1", "IntegerDataField");
    model.result().report("rpt1").feature("sec2").feature().create("field2", "StringArrayDataField");
    model.result().report("rpt1").feature("sec2").feature("field2").setIndex("include", false, 0);
    model.result().report("rpt1").feature("sec2").feature("field2").setIndex("include", false, 1);
    model.result().report("rpt1").feature("sec2").feature("field2").setIndex("include", false, 2);
    model.result().report("rpt1").feature("sec2").feature("field2").setIndex("include", false, 3);
    model.result().report("rpt1").feature("sec2").feature("field2").setIndex("include", false, 5);
    model.result().report("rpt1").feature("sec2").feature().move("field1", 2);
    model.result().report("rpt1").feature("sec2").feature().move("field1", 1);
    model.result().report("rpt1").feature("sec2").feature().move("field2", 3);
    model.result().report("rpt1").feature("sec2").feature().move("field2", 2);

    model.title("\u94a2\u576f\u611f\u5e94\u52a0\u70ed");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u51e0\u4f55\u96f6\u4ef6\u548c\u53c2\u6570\u5316\u51e0\u4f55\n\u2022 \u4f7f\u7528\u8868\u683c\u4f5c\u4e3a\u7528\u6237\u8f93\u5165\u53c2\u6570\n\u2022 \u4e09\u7ef4\u51e0\u4f55\u7684\u4e8c\u7ef4\u6a2a\u622a\u9762\u7684\u53ef\u89c6\u5316\n\u2022 \u9690\u85cf\u51e0\u4f55\u5bf9\u8c61\uff08\u7a7a\u6c14\u5bf9\u8c61\uff09\u65f6\uff0c\u53ef\u89c6\u5316\u548c\u7528\u6237\u4f53\u9a8c\u5f97\u5230\u6539\u5584\u3002\n\n\u611f\u5e94\u52a0\u70ed\u662f\u4e00\u79cd\u7528\u6765\u52a0\u70ed\u91d1\u5c5e\u4ee5\u4f9b\u953b\u9020\u548c\u5176\u4ed6\u5e94\u7528\u7684\u65b9\u6cd5\u3002\u4e0e\u71c3\u6c14\u7089\u6216\u7535\u963b\u7089\u7b49\u66f4\u4f20\u7edf\u7684\u52a0\u70ed\u65b9\u6cd5\u76f8\u6bd4\uff0c\u611f\u5e94\u52a0\u70ed\u4ee5\u4e00\u79cd\u66f4\u53ef\u63a7\u7684\u65b9\u5f0f\u5c06\u52a0\u70ed\u529f\u7387\u76f4\u63a5\u4f20\u9012\u7ed9\u5de5\u4ef6\uff0c\u4ece\u800c\u7f29\u77ed\u4e86\u5904\u7406\u65f6\u95f4\u3002\n\n\u8be5 App \u7528\u4e8e\u8bbe\u8ba1\u7b80\u5355\u7684\u94a2\u576f\u611f\u5e94\u52a0\u70ed\u7cfb\u7edf\uff0c\u8be5\u7cfb\u7edf\u7531\u4e00\u4e2a\u6216\u591a\u4e2a\u7535\u78c1\u7ebf\u5708\u7ec4\u6210\uff0c\u94a2\u576f\u5728\u5176\u4e2d\u505a\u5300\u901f\u8fd0\u52a8\u3002\u8fd9\u4e9b\u7ebf\u5708\u901a\u5165\u4ea4\u6d41\u7535\u540e\uff0c\u4f1a\u5728\u91d1\u5c5e\u576f\u4e2d\u611f\u5e94\u51fa\u6da1\u6d41\uff0c\u4ece\u800c\u901a\u8fc7\u7126\u8033\u70ed\u6548\u5e94\u4ea7\u751f\u70ed\u91cf\u3002\u94a2\u576f\u6a2a\u622a\u9762\u3001\u7ebf\u5708\u6570\u3001\u4f4d\u7f6e\u3001\u5c3a\u5bf8\u3001\u521d\u59cb\u6e29\u5ea6\u548c\u73af\u5883\u6e29\u5ea6\u4ee5\u53ca\u5355\u4e2a\u7ebf\u5708\u7535\u6d41\u90fd\u53ef\u4ee5\u6307\u5b9a\u4e3a\u8be5 App \u7684\u8f93\u5165\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("billet_induction_heating.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
