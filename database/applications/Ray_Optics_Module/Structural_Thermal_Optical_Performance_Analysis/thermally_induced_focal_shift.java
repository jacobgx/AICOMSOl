/*
 * thermally_induced_focal_shift.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:24 by COMSOL 6.3.0.290. */
public class thermally_induced_focal_shift {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Structural_Thermal_Optical_Performance_Analysis");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");
    model.component("comp1").physics("gop").prop("IntensityComputation")
         .set("IntensityComputation", "ComputeIntensityAndPower");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.component("comp1").multiphysics().create("rhs1", "RayHeatSource", 3);
    model.component("comp1").multiphysics("rhs1").set("RayHeatSource_physics", "gop");
    model.component("comp1").multiphysics("rhs1").set("RayHeatDestination_physics", "ht");

    model.study().create("std1");
    model.study("std1").create("bcrt", "BidirectionallyCoupledRayTracing");
    model.study("std1").feature("bcrt").setSolveFor("/physics/gop", true);
    model.study("std1").feature("bcrt").setSolveFor("/physics/ht", true);
    model.study("std1").feature("bcrt").setSolveFor("/physics/solid", true);
    model.study("std1").feature("bcrt").setSolveFor("/multiphysics/rhs1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Irms", "1[W]", "\u5149\u6e90\u7684\u5747\u65b9\u6839\u529f\u7387");
    model.param().set("T0", "293.15", "\u5ba4\u6e29");
    model.param().set("lam", "1064[nm]", "\u5149\u7ea4\u6fc0\u5149\u7684\u6ce2\u957f");
    model.param().set("d", "50[mm]", "\u5e73\u51f8\u900f\u955c\u76f4\u5f84");
    model.param().set("R", "68.8[mm]", "\u5e73\u51f8\u900f\u955c\u7684\u66f2\u7387\u534a\u5f84");
    model.param().set("Te", "3.0[mm]", "\u5e73\u51f8\u900f\u955c\u7684\u8fb9\u539a\u5ea6");
    model.param().set("Tc", "Te+R-sqrt(R^2-(d/2)^2)", "\u5e73\u51f8\u900f\u955c\u7684\u4e2d\u5fc3\u539a\u5ea6");
    model.param().set("dis", "1.5*R", "\u51c6\u76f4\u900f\u955c\u4e0e\u805a\u7126\u900f\u955c\u7684\u8ddd\u79bb");
    model.param().set("bfl", "145.0[mm]", "\u5e73\u51f8\u900f\u955c\u7684\u540e\u7126\u8ddd");
    model.param().set("fiber_pos", "-(dis/2+Tc+bfl)", "\u5149\u7ea4\u4f4d\u7f6e");
    model.param().set("fiber_NA", "0.1", "\u5149\u7ea4\u7684\u6570\u503c\u5b54\u5f84");
    model.param().set("theta", "asin(fiber_NA)", "\u5df2\u91ca\u653e\u5c04\u7ebf\u7684\u534a\u9525\u89d2");
    model.param().set("n0", "1.45", "T=T0 \u4e14 lambda=lam \u65f6\u7684\u6298\u5c04\u7387\u5b9e\u90e8");
    model.param().set("dndT", "10e-6[1/K]", "\u6298\u5c04\u7387\u5b9e\u90e8\u7684\u70ed\u53d8\u5316");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.geom()
         .load(new String[]{"part1"}, "Ray_Optics_Module\\3D\\Spherical_Lenses\\spherical_lens_3d.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "R1", "R");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "R2", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "Tc", "Tc");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d0", "d");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d1", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d2", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d1_clear", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "d2_clear", 0);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "niy", -1);
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "niz", 0);
    model.component("comp1").geom("geom1").feature("pi1").set("displ", new String[]{"0", "-dis/2", "0"});
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u901a\u5149\u5b54\u5f84");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("selcontributetobnd", "pi1_sel3", "csel1");
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 0.75);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 20);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new int[]{0, -58, 10});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("cyl1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 120);
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "y");
    model.component("comp1").geom("geom1").feature("rot1").set("keep", true);
    model.component("comp1").geom("geom1").feature().duplicate("rot2", "rot1");
    model.component("comp1").geom("geom1").feature("rot2").set("rot", -120);
    model.component("comp1").geom("geom1").run("rot2");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").init();
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("pi1");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").set("cyl1", "rot1", "rot2");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("par1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u66b4\u9732\u7684\u900f\u955c\u8868\u9762");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("uni1", 1, 2, 3, 4, 7, 8);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("comsel1", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel1").label("\u56fa\u5b9a\u7684\u900f\u955c\u8868\u9762");
    model.component("comp1").geom("geom1").feature("comsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("comsel1").set("input", new String[]{"sel1"});
    model.component("comp1").geom("geom1").run("comsel1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("pos", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{0, -1, 0});
    model.geom()
         .load(new String[]{"part2"}, "Ray_Optics_Module\\3D\\Apertures_and_Obstructions\\circular_planar_annulus.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").label("\u76ee\u6807");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d0", "1.0[mm]");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "d1", 0);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "niy", 1);
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "niz", 0);
    model.component("comp1").geom("geom1").feature("pi2").set("displ", new String[]{"0", "dis/2+Tc+bfl", "0"});
    model.component("comp1").geom("geom1").feature("pi2").setEntry("selkeepbnd", "pi2_sel1", true);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("n_r", "n0+dndT*(T-T0)", "\u6298\u5c04\u7387\u5b9e\u90e8");
    model.component("comp1").variable("var1").set("n_i", "3e-8", "\u6298\u5c04\u7387\u865a\u90e8");
    model.component("comp1").variable("var1").set("n", "n_r-i*n_i", "\u900f\u955c\u6298\u5c04\u7387");

    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").feature("matd1")
         .set("ThinDielectricFilmsOnBoundary", "AntiReflectiveCoating");
    model.component("comp1").physics("gop").feature("op1").set("lambda0", "lam");
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").setIndex("x0", "fiber_pos", 1);
    model.component("comp1").physics("gop").feature("relg1").set("RayDirectionVector", "Conical");
    model.component("comp1").physics("gop").feature("relg1").set("ConicalDistribution", "Hexapolar");
    model.component("comp1").physics("gop").feature("relg1").setIndex("Nctheta", 18, 0);
    model.component("comp1").physics("gop").feature("relg1").set("cax", new int[]{0, 1, 0});
    model.component("comp1").physics("gop").feature("relg1").set("alphac", "theta");
    model.component("comp1").physics("gop").feature("relg1").set("Psrc", "Irms");
    model.component("comp1").physics("gop").create("wall1", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall1").selection().named("geom1_pi2_sel1");
    model.component("comp1").physics("gop").feature("wall1").set("WallCondition", "Pass");
    model.component("comp1").physics("gop").feature("wall1").create("bacc1", "BoundaryAccumulator", 2);
    model.component("comp1").physics("gop").feature("wall1").feature("bacc1").set("R", "gop.Q");
    model.component("comp1").physics("gop").feature("wall1").feature("bacc1")
         .set("CustomDependentVariableUnit", "1");
    model.component("comp1").physics("gop").feature("wall1").feature("bacc1")
         .set("DependentVariableQuantity", "none");
    model.component("comp1").physics("gop").feature("wall1").feature("bacc1")
         .setIndex("CustomDependentVariableUnit", "W/m^2", 0, 0);
    model.component("comp1").physics("ht").prop("ShapeProperty").set("order_temperature", 3);
    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 2);
    model.component("comp1").physics("ht").feature("hf1").selection().named("geom1_sel1");
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", 10);
    model.component("comp1").physics("ht").feature("hf1").set("Text", "T0");
    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", 3);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("geom1_comsel1");

    model.component("comp1").multiphysics().create("te1", "ThermalExpansion", 3);
    model.component("comp1").multiphysics("te1").selection().set(1, 2);

    model.common("cminpt").set("modified", new String[][]{{"strainreferencetemperature", "T0"}});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").label("Silica glass");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.99);
    model.component("comp1").material("mat1").set("roughness", 0.02);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-14[S/m]", "0", "0", "0", "1e-14[S/m]", "0", "0", "0", "1e-14[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "703[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"3.75", "0", "0", "0", "3.75", "0", "0", "0", "3.75"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2203[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "73.1[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.17");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n"});

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("geom1_pi2_sel1");
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", 0.025);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("geom1_comsel1");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 0.5);
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().named("geom1_csel1_bnd");
    model.component("comp1").mesh("mesh1").feature("ftri2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmax", "3.0");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("bcrt").set("timestepspec", "specifylength");
    model.study("std1").feature("bcrt").set("lunit", "mm");
    model.study("std1").feature("bcrt").set("llist", "0 400 range(414,0.2,419)");
    model.study("std1").feature("bcrt").set("geometricNonlinearity", true);
    model.study("std1").feature("bcrt").set("iter", 3);
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "Irms", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "W", 0);
    model.study("std1").feature("param").setIndex("pname", "Irms", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "W", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1 3000", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v2").feature("comp1_u").set("scalemethod", "manual");

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol2");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 28, 0);
    model.result("pg1").setIndex("looplevel", 2, 1);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.I");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6e29\u5ea6 (ht)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 28, 0);
    model.result("pg2").setIndex("looplevel", 2, 1);
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("expr", "T");
    model.result("pg2").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("vol1").set("smooth", "internal");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 28, 0);
    model.result("pg3").setIndex("looplevel", 2, 1);
    model.result("pg3").label("\u5e94\u529b (solid)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg3").feature("vol1").set("threshold", "manual");
    model.result("pg3").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("vol1").set("colortable", "Rainbow");
    model.result("pg3").feature("vol1").set("colortabletrans", "none");
    model.result("pg3").feature("vol1").set("colorscalemode", "linear");
    model.result("pg3").feature("vol1").set("resolution", "custom");
    model.result("pg3").feature("vol1").set("refine", 2);
    model.result("pg3").feature("vol1").set("colortable", "Prism");
    model.result("pg3").feature("vol1").create("def", "Deform");
    model.result("pg3").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().named("geom1_csel1_bnd");
    model.result("pg1").run();
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.Q");
    model.result("pg1").feature("rtrj1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("rtrj1").feature("col1").set("colortable", "GrayPrint");
    model.result("pg1").feature("rtrj1").feature("col1").set("colortabletrans", "reverse");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("filt1").set("evaluate", "fraction");
    model.result("pg1").feature("rtrj1").feature("filt1").set("fraction", 0.1);
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "T");
    model.result("pg1").feature("surf1").set("descr", "\u6e29\u5ea6");
    model.result("pg1").feature("surf1").set("colortable", "GrayBody");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").set("edges", false);
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").run();
    model.result("pg3").feature("vol1").set("unit", "MPa");
    model.result("pg3").feature("vol1").set("rangecoloractive", true);
    model.result("pg3").feature("vol1").set("rangecolormax", 10);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u6c89\u79ef\u5c04\u7ebf\u529f\u7387\uff08\u900f\u955c\uff09");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("edges", false);
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", "rhs1.Qsrc");
    model.result("pg4").feature("vol1").set("descr", "\u70ed\u6e90");
    model.result("pg4").feature("vol1").set("unit", "mW/mm^3");
    model.result("pg4").feature("vol1").set("colortable", "GrayBody");
    model.result("pg4").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("vol1").set("colorcalibration", 1.5);
    model.result("pg4").feature("vol1").set("resolution", "norefine");
    model.result("pg4").run();
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").set("data", "dset2");
    model.result().dataset("surf1").selection().named("geom1_pi2_sel1");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").run();
    model.result("pg5").label("\u6c89\u79ef\u5c04\u7ebf\u529f\u7387\uff08\u76ee\u6807\uff09");
    model.result("pg5").set("data", "surf1");
    model.result("pg5").set("edges", false);
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "gop.wall1.bacc1.rpb");
    model.result("pg5").feature("surf1").set("descr", "\u7d2f\u79ef\u53d8\u91cf rpb");
    model.result("pg5").feature("surf1").set("unit", "kW/mm^2");
    model.result("pg5").feature("surf1").set("colortable", "GrayBody");
    model.result("pg5").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("surf1").set("colorcalibration", -1);
    model.result("pg5").run();
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").set("data", "dset2");
    model.result().dataset("surf2").selection().set(7);
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").label("\u6298\u5c04\u7387");
    model.result("pg6").set("data", "surf2");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "n_r-n0");
    model.result("pg6").feature("surf1").set("colortable", "Viridis");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u5149\u6591\u5c3a\u5bf8");
    model.result("pg7").set("data", "ray1");
    model.result("pg7").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg7").setIndex("looplevelindices", "range(3,1,28)", 0);
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "gop.rrms", 0);
    model.result("pg7").feature("glob1").setIndex("unit", "um", 0);
    model.result("pg7").feature("glob1").set("titletype", "none");
    model.result("pg7").feature("glob1").set("xdata", "expr");
    model.result("pg7").feature("glob1").set("xdataexpr", "gop.qavey");
    model.result("pg7").run();
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("xlabel", "\u7126\u70b9\uff08y \u4f4d\u7f6e\uff0cmm\uff09");
    model.result("pg7").set("ylabel", "RMS \u70b9\u534a\u5f84 (um)");
    model.result("pg7").set("legendpos", "uppermiddle");
    model.result("pg7").run();
    model.result().dataset().duplicate("ray2", "ray1");
    model.result().dataset("ray2").set("solution", "sol3");
    model.result().dataset().duplicate("ray3", "ray2");
    model.result().dataset("ray3").set("solution", "sol4");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").run();
    model.result("pg8").label("\u70b9\u5217\u56fe");
    model.result("pg8").set("data", "none");
    model.result("pg8").set("showlegends", false);
    model.result("pg8").create("spot1", "SpotDiagram");
    model.result("pg8").feature("spot1").set("data", "ray2");
    model.result("pg8").run();
    model.result().dataset().create("ip1", "IntersectionPoint3D");
    model.result("pg8").feature("spot1").set("data", "ip1");
    model.result().dataset("ip1").set("data", "ray2");
    model.result().dataset("ip1").set("genmethod", "pointnormal");
    model.result().dataset("ip1").setIndex("genpnpoint", "3.6428251716231672E-6[mm]", 0);
    model.result().dataset("ip1").setIndex("genpnpoint", "206.463698968333[mm]", 1);
    model.result().dataset("ip1").setIndex("genpnpoint", "5.320994066728442E-7[mm]", 2);
    model.result().dataset("ip1")
         .set("genpnvec", new double[]{1.4637664821658364E-8, 0.9999999999999998, 5.290159244099718E-9});
    model.result("pg8").feature("spot1").run();
    model.result().dataset("ip1").set("genmethod", "pointnormal");
    model.result().dataset("ip1").setIndex("genpnpoint", "3.6723096572287846E-6[mm]", 0);
    model.result().dataset("ip1").setIndex("genpnpoint", "206.45925150721885[mm]", 1);
    model.result().dataset("ip1").setIndex("genpnpoint", "5.381380916521167E-7[mm]", 2);
    model.result().dataset("ip1")
         .set("genpnvec", new double[]{1.463766482203771E-8, 0.9999999999999998, 5.290159241503564E-9});
    model.result("pg8").feature("spot1").run();
    model.result("pg8").set("ispendingzoom", true);
    model.result("pg8").feature("spot1").set("spotcoordsactive", true);
    model.result("pg8").feature("spot1").set("spotcoordssystem", "global");
    model.result("pg8").feature("spot1").set("spotcoordsprecision", 6);
    model.result("pg8").feature("spot1").create("col1", "Color");
    model.result("pg8").run();
    model.result("pg8").feature("spot1").feature("col1").set("expr", "at(0,gop.phic)");
    model.result("pg8").feature("spot1").feature("col1").set("unit", "\u00b0");
    model.result("pg8").feature("spot1").feature("col1").set("colortable", "Viridis");
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("spot2", "spot1");
    model.result("pg8").run();
    model.result("pg8").feature("spot2").set("data", "ray3");
    model.result("pg8").run();
    model.result().dataset().create("ip2", "IntersectionPoint3D");
    model.result("pg8").feature("spot2").set("data", "ip2");
    model.result().dataset("ip2").set("data", "ray3");
    model.result().dataset("ip2").set("genmethod", "pointnormal");
    model.result().dataset("ip2").setIndex("genpnpoint", "1.2785459694523863E-5[mm]", 0);
    model.result().dataset("ip2").setIndex("genpnpoint", "204.23250815516067[mm]", 1);
    model.result().dataset("ip2").setIndex("genpnpoint", "-2.7983674933031653E-5[mm]", 2);
    model.result().dataset("ip2")
         .set("genpnvec", new double[]{1.3023065936715427E-7, 0.9999999999999836, -1.2669376670512304E-7});
    model.result("pg8").feature("spot2").run();
    model.result().dataset("ip2").set("genmethod", "pointnormal");
    model.result().dataset("ip2").setIndex("genpnpoint", "1.2838778548164398E-5[mm]", 0);
    model.result().dataset("ip2").setIndex("genpnpoint", "204.22641147024197[mm]", 1);
    model.result().dataset("ip2").setIndex("genpnpoint", "-2.791343035770322E-5[mm]", 2);
    model.result().dataset("ip2")
         .set("genpnvec", new double[]{1.3023065936669362E-7, 0.9999999999999836, -1.2669376670425E-7});
    model.result("pg8").feature("spot2").run();
    model.result("pg8").set("ispendingzoom", true);
    model.result("pg8").feature("spot2").set("posexpr", new String[]{"0.35", "0"});
    model.result("pg8").run();
    model.result("pg1").run();

    model.title("\u5927\u529f\u7387\u6fc0\u5149\u805a\u7126\u7cfb\u7edf\u4e2d\u7684\u70ed\u81f4\u7126\u79fb");

    model
         .description("\u73b0\u4ee3\u5927\u529f\u7387\u5de5\u4e1a\u5149\u7ea4\u6fc0\u5149\u7cfb\u7edf\u53ef\u4ee5\u5728\u5f85\u5207\u5272\u3001\u94bb\u5b54\u3001\u710a\u63a5\u6216\u6253\u6807\u7684\u8868\u9762\u4e0a\u8f93\u51fa\u9ad8\u8fbe 3\u00a0kW \u7684\u5355\u6a21\u6fc0\u5149\u8f90\u5c04\u3002\u5373\u4fbf\u4f7f\u7528\u9ad8\u900f\u5c04\u7387\u7684\u6750\u6599\uff0c\u7528\u4e8e\u5c06\u6fc0\u5149\u675f\u805a\u7126\u5230\u76ee\u6807\u8868\u9762\u4e0a\u7684\u5149\u5b66\u5143\u4ef6\u4e5f\u4f1a\u53d7\u5230\u5149\u643a\u5e26\u7684\u5927\u91cf\u529f\u7387\u7684\u5f71\u54cd\u3002\u968f\u7740\u6fc0\u5149\u675f\u901a\u8fc7\u5149\u5b66\u5143\u4ef6\uff0c\u5149\u5b66\u6750\u6599\u7684\u70ed\u81a8\u80c0\u548c\u6298\u5c04\u7387\u7684\u53d8\u5316\u4f1a\u6539\u53d8\u7cfb\u7edf\u7684\u7126\u70b9\u3002\u672c\u4f8b\u8ba1\u7b97\u5927\u529f\u7387\u6fc0\u5149\u7cfb\u7edf\u4e2d\u7531\u70ed\u53d8\u5f62\u5f15\u8d77\u7684\u7126\u79fb\u3002");

    return model;
  }

  public static Model run2(Model model) {

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("thermally_induced_focal_shift.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
