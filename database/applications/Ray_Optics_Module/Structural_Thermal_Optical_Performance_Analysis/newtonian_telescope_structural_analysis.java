/*
 * newtonian_telescope_structural_analysis.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:22 by COMSOL 6.3.0.290. */
public class newtonian_telescope_structural_analysis {

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

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

    model.component("comp1").geom("geom1")
         .label("\u725b\u987f\u671b\u8fdc\u955c\u7ed3\u6784\u5206\u6790\u51e0\u4f55\u5e8f\u5217");
    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1")
         .insertFile("newtonian_telescope_structural_analysis_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.param().label("\u53c2\u6570 1\uff1a\u671b\u8fdc\u955c\u51e0\u4f55");
    model.param().set("theta", "45[deg]");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 2\uff1a\u6ce2\u957f\u548c\u573a");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("lam", "550[nm]", "\u4e2d\u5fc3\u6ce2\u957f");
    model.param("par2").set("N_hex", "12", "\u516d\u8fb9\u5f62\u5f84\u5411\u73af\u6570");
    model.param("par2").set("theta_x1", "0.0[arcmin]", "\u89c6\u573a\u89d2 (x)");
    model.param("par2").set("theta_y1", "0.0[arcmin]", "\u89c6\u573a\u89d2 (y)");
    model.param("par2").set("theta_x2", "2.0[arcmin]", "\u89c6\u573a\u89d2 (x)");
    model.param("par2").set("theta_y2", "0.0[arcmin]", "\u89c6\u573a\u89d2 (y)");
    model.param("par2").set("vx1", "sin(theta_x1)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cx \u5206\u91cf");
    model.param("par2")
         .set("vy1", "sin(theta+theta_y1)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cy \u5206\u91cf");
    model.param("par2")
         .set("vz1", "-sqrt(1-vx1^2-vy1^2)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cz \u5206\u91cf");
    model.param("par2").set("dx1", "-f*vx1", "\u7269\u5e73\u9762 x \u5750\u6807");
    model.param("par2").set("dy1", "-f*vy1", "\u7269\u5e73\u9762 y \u5750\u6807");
    model.param("par2")
         .set("dz1", "-f*vz1", "\u7269\u5e73\u9762 z \u5750\u6807\uff08\u76f8\u5bf9\u4e8e\u4e3b\u955c\u9876\u70b9\uff09");
    model.param("par2").set("vx2", "sin(theta_x2)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cx \u5206\u91cf");
    model.param("par2")
         .set("vy2", "sin(theta+theta_y2)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cy \u5206\u91cf");
    model.param("par2")
         .set("vz2", "-sqrt(1-vx2^2-vy2^2)", "\u5149\u7ebf\u65b9\u5411\u77e2\u91cf\uff0cz \u5206\u91cf");
    model.param("par2").set("dx2", "-f*vx2", "\u7269\u5e73\u9762 x \u5750\u6807");
    model.param("par2").set("dy2", "-f*vy2", "\u7269\u5e73\u9762 y \u5750\u6807");
    model.param("par2")
         .set("dz2", "-f*vz2", "\u7269\u5e73\u9762 z \u5750\u6807\uff08\u76f8\u5bf9\u4e8e\u4e3b\u955c\u9876\u70b9\uff09");
    model.param("par2").set("r_Airy", "f*1.22*lam/d_pupil", "\u827e\u91cc\u6591\u534a\u5f84");

    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Aluminum 6063-T83");
    model.component("comp1").material("mat1").set("family", "aluminum");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.030e7[S/m]", "0", "0", "0", "3.030e7[S/m]", "0", "0", "0", "3.030e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23.4e-6[1/K]", "0", "0", "0", "23.4e-6[1/K]", "0", "0", "0", "23.4e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"201[W/(m*K)]", "0", "0", "0", "201[W/(m*K)]", "0", "0", "0", "201[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "69[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.33");
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
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat3").label("Silica glass");
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
         .set("thermalexpansioncoefficient", new String[]{"0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]", "0", "0", "0", "0.55e-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "703[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"3.75", "0", "0", "0", "3.75", "0", "0", "0", "3.75"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "2203[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]", "0", "0", "0", "1.38[W/(m*K)]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "73.1[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.17");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat3").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1.45", "0", "0", "0", "1.45", "0", "0", "0", "1.45"});
    model.component("comp1").material("mat2").selection().set(1, 7, 10, 11, 12, 14, 16, 17, 18, 19);
    model.component("comp1").material("mat3").selection().set(9, 13);

    model.component("comp1").physics("gop").selection().set();
    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").prop("ComputeOpticalPathLength")
         .setIndex("ComputeOpticalPathLength", 1, 0);
    model.component("comp1").physics("gop").prop("CountReflections").setIndex("CountReflections", 1, 0);
    model.component("comp1").physics("gop").feature("op1").set("lambda0", "lam");
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").set("GridType", "Hexapolar");
    model.component("comp1").physics("gop").feature("relg1").set("qcc", new String[]{"dx1", "dy1", "dz1"});
    model.component("comp1").physics("gop").feature("relg1").set("rcc", new String[]{"vx1", "vy1", "vz1"});
    model.component("comp1").physics("gop").feature("relg1").set("Rc", "d_pupil/2");
    model.component("comp1").physics("gop").feature("relg1").setIndex("Ncr", "N_hex", 0);
    model.component("comp1").physics("gop").feature("relg1").set("L0", new String[]{"vx1", "vy1", "vz1"});
    model.component("comp1").physics("gop").feature().duplicate("relg2", "relg1");
    model.component("comp1").physics("gop").feature("relg2").set("qcc", new String[]{"dx2", "dy2", "dz2"});
    model.component("comp1").physics("gop").feature("relg2").set("rcc", new String[]{"vx2", "vy2", "vz2"});
    model.component("comp1").physics("gop").feature("relg2").set("L0", new String[]{"vx2", "vy2", "vz2"});
    model.component("comp1").physics("gop").create("mir1", "Mirror", 2);
    model.component("comp1").physics("gop").feature("mir1").label("\u4e3b\u955c");
    model.component("comp1").physics("gop").feature("mir1").selection().named("geom1_pi1_cylsel1");
    model.component("comp1").physics("gop").create("wall1", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall1").label("\u526f\u955c");
    model.component("comp1").physics("gop").feature("wall1").selection().named("geom1_pi2_cylsel1");
    model.component("comp1").physics("gop").feature("wall1").set("WallCondition", "SpecularReflection");
    model.component("comp1").physics("gop").feature("wall1").set("PrimaryRayCondition", "Expression");
    model.component("comp1").physics("gop").feature("wall1").set("e", "gop.Nrefl>0");
    model.component("comp1").physics("gop").feature("wall1").set("Otherwise", "Pass");
    model.component("comp1").physics("gop").create("wall2", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall2").label("\u4e3b\u955c\u969c\u788d\u7269");
    model.component("comp1").physics("gop").feature("wall2").set("WallCondition", "Disappear");
    model.component("comp1").physics("gop").feature("wall2").selection().named("geom1_csel1_bnd");
    model.component("comp1").physics("gop").create("wall3", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall3").label("\u6b21\u955c\u969c\u788d\u7269");
    model.component("comp1").physics("gop").feature("wall3").set("WallCondition", "Disappear");
    model.component("comp1").physics("gop").feature("wall3").selection().named("geom1_csel2_bnd");
    model.component("comp1").physics("gop").create("wall4", "Wall", 2);
    model.component("comp1").physics("gop").feature("wall4").label("\u50cf\u5e73\u9762");
    model.component("comp1").physics("gop").feature("wall4").selection().named("geom1_csel3_bnd");

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("geom1_pi1_cylsel1");
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("size2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("size2").selection().named("geom1_uni1_dom");
    model.component("comp1").mesh("mesh1").feature("size2").selection().set(3, 6, 8);
    model.component("comp1").mesh("mesh1").feature("size2").set("hauto", 4);
    model.component("comp1").mesh("mesh1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature("size3").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size3").selection().set(20, 121, 122, 140, 143);
    model.component("comp1").mesh("mesh1").feature("size3").set("hauto", 4);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std1").feature("rtrac").set("lunit", "mm");
    model.study("std1").feature("rtrac").set("llist", "0 2.10*f");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol1");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "gop.L");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg1").run();
    model.result("pg1").label("\u5c04\u7ebf\u56fe - \u672a\u53d8\u5f62");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "at('last',gop.rrel)");
    model.result("pg1").feature("rtrj1").feature("col1").set("unit", "um");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u70b9\u5217\u56fe - \u672a\u53d8\u5f62");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "X");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "Y");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("spot1", "SpotDiagram");
    model.result("pg2").feature("spot1").set("transverse", "userdefined");
    model.result("pg2").feature("spot1").set("transverseexpr", new String[]{"0", "cos(theta)", "0"});
    model.result("pg2").feature("spot1").setIndex("transverseexpr", "sin(theta)", 2);
    model.result("pg2").feature("spot1").set("origin", "area");
    model.result("pg2").feature("spot1").set("layout", "rectangular");
    model.result("pg2").feature("spot1").set("columns", 1);
    model.result("pg2").feature("spot1").set("spotcoordsactive", true);
    model.result("pg2").feature("spot1").set("spotcoordssystem", "global");
    model.result("pg2").feature("spot1").set("spotcoordsprecision", 6);
    model.result("pg2").feature("spot1").set("fitannotationstospot", true);
    model.result("pg2").feature("spot1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("spot1").feature("col1").set("expr", "at(0,gop.rrel)");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u50cf\u5dee\u56fe - \u672a\u53d8\u5f62");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("oab1", "OpticalAberration");
    model.result("pg3").feature("oab1").set("filterreleaseactive", true);
    model.result().dataset().create("ip1", "IntersectionPoint3D");
    model.result("pg3").feature("oab1").set("data", "ip1");
    model.result().dataset("ip1").set("data", "ray1");
    model.result().dataset("ip1").set("type", "hemisphere");
    model.result().dataset("ip1").setIndex("center", "-200.00000000000202[mm]", 0);
    model.result().dataset("ip1").setIndex("axis", "0.9999999160299629", 0);
    model.result().dataset("ip1").setIndex("center", "-530.3300858899133[mm]", 1);
    model.result().dataset("ip1").setIndex("axis", "-2.8977583353424335E-4", 1);
    model.result().dataset("ip1").setIndex("center", "530.3300858899134[mm]", 2);
    model.result().dataset("ip1").setIndex("axis", "2.897758335338053E-4", 2);
    model.result().dataset("ip1").set("radius", "50[mm]");
    model.result("pg3").feature("oab1").run();
    model.result("pg3").feature("oab1").set("colortable", "Dipole");
    model.result("pg3").feature("oab1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").feature().duplicate("oab2", "oab1");
    model.result("pg3").run();
    model.result("pg3").feature("oab2").set("filterrelease", 2);
    model.result("pg3").feature("oab2").set("posexpr", new String[]{"2.5", "0"});
    model.result("pg3").feature("oab2").set("terms", "selectindividual");
    model.result("pg3").feature("oab2").set("z3m1", true);
    model.result("pg3").feature("oab2").set("z31", true);
    model.result("pg3").feature("oab2").set("inheritplot", "oab1");
    model.result("pg3").run();

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study("std1").feature("rtrac").setSolveFor("/physics/solid", false);

    model.component("comp1").physics("solid").create("gacc1", "GravityAcceleration", -1);
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(1, 236);

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/gop", false);
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").create("rtrac", "RayTracing");
    model.study("std2").feature("rtrac").set("timestepspec", "specifylength");
    model.study("std2").feature("rtrac").set("lunit", "mm");
    model.study("std2").feature("rtrac").set("llist", "0 2.10*f");
    model.study("std2").feature("rtrac").set("geometricNonlinearity", true);
    model.study("std2").feature("rtrac").setSolveFor("/physics/solid", false);
    model.study("std2").feature("rtrac").set("useadvanceddisable", true);
    model.study("std2").feature("rtrac").set("disabledphysics", new String[]{"gop/wall4"});
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("ray2", "Ray");
    model.result().dataset("ray2").set("solution", "sol2");
    model.result().dataset("ray2").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray2").set("geom", "geom1");
    model.result().dataset("ray2").set("rgeom", "pgeom_gop");
    model.result().dataset("ray2").set("rgeomspec", "fromphysics");
    model.result().dataset("ray2").set("physicsinterface", "gop");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "ray2");
    model.result("pg4").setIndex("looplevel", 2, 0);
    model.result("pg4").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg4").create("rtrj1", "RayTrajectories");
    model.result("pg4").feature("rtrj1").set("linetype", "line");
    model.result("pg4").feature("rtrj1").create("col1", "Color");
    model.result("pg4").feature("rtrj1").feature("col1").set("expr", "gop.L");
    model.result("pg4").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 2, 0);
    model.result("pg5").label("\u5e94\u529b (solid)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg5").feature("vol1").set("threshold", "manual");
    model.result("pg5").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("vol1").set("colortable", "Rainbow");
    model.result("pg5").feature("vol1").set("colortabletrans", "none");
    model.result("pg5").feature("vol1").set("colorscalemode", "linear");
    model.result("pg5").feature("vol1").set("resolution", "custom");
    model.result("pg5").feature("vol1").set("refine", 2);
    model.result("pg5").feature("vol1").set("colortable", "Prism");
    model.result("pg5").feature("vol1").create("def", "Deform");
    model.result("pg5").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg5").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg4").run();
    model.result("pg4").label("\u5c04\u7ebf\u56fe - \u53d8\u5f62");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").run();
    model.result("pg4").feature("rtrj1").feature("col1").set("colorlegend", false);
    model.result("pg4").run();
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "solid.disp");
    model.result("pg4").feature("surf1").set("colortable", "HeatCamera");
    model.result("pg4").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg4").feature("surf1").set("unit", "um");
    model.result("pg4").feature("surf1").create("def1", "Deform");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").create("tran1", "Transparency");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 2, 0);
    model.result("pg6").label("\u4f53\u79ef\u8f7d\u8377 (solid)");
    model.result("pg6").set("showlegends", true);
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg6").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg6").feature("surf1").set("coloring", "uniform");
    model.result("pg6").feature("surf1").set("color", "gray");
    model.result("pg6").feature("surf1").set("inheritcolor", false);
    model.result("pg6").feature("surf1").set("inheritrange", false);
    model.result("pg6").feature("surf1").set("inherittransparency", false);
    model.result("pg6").feature("surf1").create("def", "Deform");
    model.result("pg6").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg6").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg6").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg6").feature("surf1").feature("def").set("scale", 1);
    model.result("pg6").feature("surf1").create("sel1", "Selection");
    model.result("pg6").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg6").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236);
    model.result("pg6").feature("surf1").create("tran1", "Transparency");
    model.result("pg6").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg6").create("arwv1", "ArrowVolume");
    model.result("pg6").feature("arwv1")
         .set("expr", new String[]{"solid.gacc1.fvx", "solid.gacc1.fvy", "solid.gacc1.fvz"});
    model.result("pg6").feature("arwv1").set("placement", "gausspoints");
    model.result("pg6").feature("arwv1").set("arrowbase", "tail");
    model.result("pg6").feature("arwv1").label("\u91cd\u529b 1");
    model.result("pg6").feature("arwv1").set("inheritplot", "none");
    model.result("pg6").feature("arwv1").create("col", "Color");
    model.result("pg6").feature("arwv1").feature("col").set("colortable", "Rainbow");
    model.result("pg6").feature("arwv1").feature("col").set("colortabletrans", "none");
    model.result("pg6").feature("arwv1").feature("col").set("colorscalemode", "linear");
    model.result("pg6").feature("arwv1").feature("col").set("colordata", "arrowlength");
    model.result("pg6").feature("arwv1").feature("col").set("coloring", "gradient");
    model.result("pg6").feature("arwv1").feature("col").set("topcolor", "red");
    model.result("pg6").feature("arwv1").feature("col").set("bottomcolor", "custom");
    model.result("pg6").feature("arwv1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg6").feature("arwv1").set("color", "magenta");
    model.result("pg6").feature("arwv1").create("def", "Deform");
    model.result("pg6").feature("arwv1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg6").feature("arwv1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg6").feature("arwv1").feature("def").set("scaleactive", true);
    model.result("pg6").feature("arwv1").feature("def").set("scale", 1);
    model.result("pg6").feature().move("surf1", 1);
    model.result("pg6").label("\u4f53\u79ef\u8f7d\u8377 (solid)");

    model.nodeGroup().create("dset2solidlgrp", "Results");
    model.nodeGroup("dset2solidlgrp").label("\u5916\u52a0\u8f7d\u8377 (solid)");
    model.nodeGroup("dset2solidlgrp").set("type", "plotgroup");
    model.nodeGroup("dset2solidlgrp").placeAfter("plotgroup", "pg6");
    model.nodeGroup("dset2solidlgrp").label("\u5916\u52a0\u8f7d\u8377 (solid)");
    model.nodeGroup("dset2solidlgrp").placeAfter("plotgroup", "pg6");
    model.nodeGroup("dset2solidlgrp").add("plotgroup", "pg6");

    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg2").run();
    model.result().duplicate("pg7", "pg2");
    model.result("pg7").run();
    model.result("pg7").label("\u70b9\u5217\u56fe - \u53d8\u5f62");
    model.result("pg7").set("data", "ray2");
    model.result("pg7").run();
    model.result("pg7").feature("spot1").set("filterreleaseactive", true);
    model.result().dataset().create("ip2", "IntersectionPoint3D");
    model.result("pg7").feature("spot1").set("data", "ip2");
    model.result().dataset("ip2").set("data", "ray2");
    model.result().dataset("ip2").set("genmethod", "threepoint");
    model.result().dataset("ip2").setIndex("genpoints", "-200.01175654745907[mm]", 0, 0);
    model.result().dataset("ip2").setIndex("genpoints", "-530.3293459735588[mm]", 0, 1);
    model.result().dataset("ip2").setIndex("genpoints", "530.3287119533961[mm]", 0, 2);
    model.result().dataset("ip2").setIndex("genpoints", "-200.01175654745458[mm]", 1, 0);
    model.result().dataset("ip2").setIndex("genpoints", "-530.329345266452[mm]", 1, 1);
    model.result().dataset("ip2").setIndex("genpoints", "530.3287126605029[mm]", 1, 2);
    model.result().dataset("ip2").setIndex("genpoints", "-200.0117565470499[mm]", 2, 0);
    model.result().dataset("ip2").setIndex("genpoints", "-530.329345266452[mm]", 2, 1);
    model.result().dataset("ip2").setIndex("genpoints", "530.3287112462893[mm]", 2, 2);
    model.result("pg7").feature("spot1").run();
    model.result().dataset("ip2").set("genmethod", "threepoint");
    model.result().dataset("ip2").setIndex("genpoints", "-200.00152433265617[mm]", 0, 0);
    model.result().dataset("ip2").setIndex("genpoints", "-530.3293494024655[mm]", 0, 1);
    model.result().dataset("ip2").setIndex("genpoints", "530.3287145301118[mm]", 0, 2);
    model.result().dataset("ip2").setIndex("genpoints", "-200.00152433265168[mm]", 1, 0);
    model.result().dataset("ip2").setIndex("genpoints", "-530.3293486953587[mm]", 1, 1);
    model.result().dataset("ip2").setIndex("genpoints", "530.3287152372186[mm]", 1, 2);
    model.result().dataset("ip2").setIndex("genpoints", "-200.00152433224702[mm]", 2, 0);
    model.result().dataset("ip2").setIndex("genpoints", "-530.3293486953587[mm]", 2, 1);
    model.result().dataset("ip2").setIndex("genpoints", "530.328713823005[mm]", 2, 2);
    model.result("pg7").feature("spot1").run();
    model.result("pg7").set("ispendingzoom", true);
    model.result("pg7").feature("spot1").set("filterreleaseactive", false);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").run();
    model.result("pg8").label("\u50cf\u5dee\u56fe - \u53d8\u5f62");
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").set("data", "ray2");
    model.result("pg8").create("oab1", "OpticalAberration");
    model.result("pg8").feature("oab1").set("filterreleaseactive", true);
    model.result().dataset().create("ip3", "IntersectionPoint3D");
    model.result("pg8").feature("oab1").set("data", "ip3");
    model.result().dataset("ip3").set("data", "ray2");
    model.result().dataset("ip3").set("type", "hemisphere");
    model.result().dataset("ip3").setIndex("center", "-200.06083879295642[mm]", 0);
    model.result().dataset("ip3").setIndex("axis", "0.9999999162830736", 0);
    model.result().dataset("ip3").setIndex("center", "-530.3293316175641[mm]", 1);
    model.result().dataset("ip3").setIndex("axis", "-2.924885284422059E-4", 1);
    model.result().dataset("ip3").setIndex("center", "530.3286979082986[mm]", 2);
    model.result().dataset("ip3").setIndex("axis", "2.8615434046562726E-4", 2);
    model.result().dataset("ip3").set("radius", "50[mm]");
    model.result("pg8").feature("oab1").run();
    model.result("pg8").feature("oab1").set("terms", "selectindividual");
    model.result("pg8").feature("oab1").set("z20", true);
    model.result("pg8").feature("oab1").set("z3m1", true);
    model.result("pg8").feature("oab1").set("z31", true);
    model.result("pg8").feature("oab1").set("colortable", "Dipole");
    model.result("pg8").feature("oab1").set("colorscalemode", "linearsymmetric");
    model.result("pg8").feature().duplicate("oab2", "oab1");
    model.result("pg8").run();
    model.result("pg8").feature("oab2").set("filterrelease", 2);
    model.result("pg8").feature("oab2").set("posexpr", new String[]{"2.5", "0"});
    model.result("pg8").feature("oab2").set("inheritplot", "oab1");
    model.result("pg8").run();

    model.title("\u725b\u987f\u671b\u8fdc\u955c\u7ed3\u6784\u5206\u6790");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u4e00\u4e2a\u7b80\u5355\u671b\u8fdc\u955c\u7684\u7ed3\u6784\u5206\u6790\uff0c\u5176\u4e2d\u7814\u7a76\u4e86\u671b\u8fdc\u955c\u7ed3\u6784\u5728\u91cd\u529b\u4f5c\u7528\u4e0b\u7684\u53d8\u5f62\uff0c\u5e76\u8bf4\u660e\u4e86\u5176\u5bf9\u6210\u50cf\u8d28\u91cf\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("newtonian_telescope_structural_analysis.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
