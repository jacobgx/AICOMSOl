/*
 * composite_cylinder_micromechanics_and_stress_analysis.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:34 by COMSOL 6.3.0.290. */
public class composite_cylinder_micromechanics_and_stress_analysis {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Composite_Materials_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L", "1[mm]", "\u57fa\u672c\u5355\u5143\u957f\u5ea6");
    model.param().set("V", "L^3", "\u57fa\u672c\u5355\u5143\u4f53\u79ef");
    model.param().set("v_f", "0.6", "\u7ea4\u7ef4\u4f53\u79ef\u5206\u6570");
    model.param().set("V_f", "v_f*V", "\u7ea4\u7ef4\u4f53\u79ef");
    model.param().set("th", "1[mm]", "\u8584\u5c42\u539a\u5ea6");
    model.param().set("rc", "100[mm]", "\u5706\u67f1\u534a\u5f84");
    model.param().set("hc", "500[mm]", "\u5706\u67f1\u9ad8\u5ea6");
    model.param().set("Ftot", "1000[N]", "\u603b\u8f7d\u8377");

    model.geom()
         .load(new String[]{"part1"}, "COMSOL_Multiphysics\\Unit_Cells_and_RVEs\\Fiber_Composites\\unidirectional_fiber_square_packing.mph", new String[]{"part2"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "vf", "v_f");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "wm", "L");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "dm", "L");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "hm", "L");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("showgrid", false);

    model.component("comp1").physics("solid").feature("lemm1").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("solid").create("cp1", "CellPeriodicity", 3);
    model.component("comp1").physics("solid").feature("cp1").set("BoundaryExpansion", "PrescribedStrain");
    model.component("comp1").physics("solid").feature("cp1").set("computeDensity", true);
    model.component("comp1").physics("solid").feature("cp1").set("computeElasticityMatrixStandard", true);
    model.component("comp1").physics("solid").feature("cp1").create("bp1", "BoundaryPair", 2);
    model.component("comp1").physics("solid").feature("cp1").feature("bp1").selection().set(1, 5, 11, 12);
    model.component("comp1").physics("solid").feature("cp1").feature().duplicate("bp2", "bp1");
    model.component("comp1").physics("solid").feature("cp1").feature("bp2").selection().set(2, 10);
    model.component("comp1").physics("solid").feature("cp1").feature().duplicate("bp3", "bp2");
    model.component("comp1").physics("solid").feature("cp1").feature("bp3").selection().set(3, 4);
    model.component("comp1").physics("solid").feature("cp1").runCommand("createLoadGroupsandStudy");

    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material("matlnk1").label("\u6750\u6599\u94fe\u63a5 1\uff1a\u57fa\u4f53");
    model.component("comp1").material("matlnk1").selection().named("geom1_pi1_unisel13");
    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.material("mat1").label("Epoxy polymer");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").set("density", "1250[kg/m^3]");
    model.material("mat1").propertyGroup("def")
         .setPropertyInfo("density", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"75E-6[1/K]", "0", "0", "0", "75E-6[1/K]", "0", "0", "0", "75E-6[1/K]"});
    model.material("mat1").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.material("mat1").propertyGroup("def").set("heatcapacity", "796[J/(kg*K)]");
    model.material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup("Enu").set("E", "3.25[GPa]");
    model.material("mat1").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.material("mat1").propertyGroup("Enu").set("nu", "0.265");
    model.material("mat1").propertyGroup("Enu")
         .setPropertyInfo("nu", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.material("mat1").propertyGroup("IsotropicStrengthParameters").label("Isotropic strength parameters");
    model.material("mat1").propertyGroup("IsotropicStrengthParameters").set("sigmat", "87.5[MPa]");
    model.material("mat1").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("matlnk1").set("link", "mat1");
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").label("\u6750\u6599\u94fe\u63a5 2\uff1a\u7ea4\u7ef4");
    model.component("comp1").material("matlnk2").selection().named("geom1_pi1_unisel12");
    model.material().create("mat2", "Common", "");
    model.material("mat2").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely isotropic");
    model.material("mat2").label("T-300 carbon fiber");
    model.material("mat2").propertyGroup("def").label("Basic");
    model.material("mat2").propertyGroup("def").set("density", "1760[kg/m^3]");
    model.material("mat2").propertyGroup("def")
         .setPropertyInfo("density", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"-0.7E-6[1/K]", "0", "0", "0", "12E-6[1/K]", "0", "0", "0", "12E-6[1/K]"});
    model.material("mat2").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat2").propertyGroup("def").set("heatcapacity", "");
    model.material("mat2").propertyGroup("TransverseIsotropic").label("Transversely isotropic");
    model.material("mat2").propertyGroup("TransverseIsotropic").set("Evect", new String[]{"230[GPa]", "15[GPa]"});
    model.material("mat2").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Evect", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat2").propertyGroup("TransverseIsotropic").set("nuvect", new String[]{"0.2", "0.0714"});
    model.material("mat2").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("nuvect", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.material("mat2").propertyGroup("TransverseIsotropic").set("Gvect1", "27[GPa]");
    model.material("mat2").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Gvect1", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.component("comp1").material("matlnk2").set("link", "mat2");

    model.component("comp1").mesh("mesh1").create("id1", "IdenticalMesh");
    model.component("comp1").mesh("mesh1").feature("id1").selection("group1").named("geom1_pi1_unisel1");
    model.component("comp1").mesh("mesh1").feature("id1").selection("group2").named("geom1_pi1_unisel2");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("geom1_pi1_unisel1");
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").run();

    model.study("solidcp1std").createAutoSequences("all");

    model.sol("solidcp1sol").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 6, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
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
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("solidcp1stdEg", "EvaluationGroup");
    model.result().evaluationGroup("solidcp1stdEg").set("data", "dset1");
    model.result().evaluationGroup("solidcp1stdEg").set("includeparameters", "off");
    model.result().evaluationGroup("solidcp1stdEg").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("solidcp1stdEg")
         .label("\u6709\u6548\u6750\u6599\u5c5e\u6027 (\u5355\u5143\u5468\u671f\u6027\u7814\u7a76)");
    model.result().evaluationGroup("solidcp1stdEg").create("gevdcp1", "EvalGlobal");
    model.result().evaluationGroup("solidcp1stdEg").feature("gevdcp1")
         .setIndex("expr", "root.comp1.solid.cp1.rho", 0);
    model.result().evaluationGroup("solidcp1stdEg").feature("gevdcp1").set("descr", "\u5bc6\u5ea6");
    model.result().evaluationGroup("solidcp1stdEg").create("gmevescp1", "EvalGlobalMatrix");
    model.result().evaluationGroup("solidcp1stdEg").feature("gmevescp1").set("expr", "root.comp1.solid.cp1.D");
    model.result().evaluationGroup("solidcp1stdEg").feature("gmevescp1").set("descr", "\u5f39\u6027\u77e9\u9635");
    model.result().evaluationGroup("solidcp1stdEg").run();
    model.result("pg1").run();
    model.result("pg1").label("\u5e94\u529b\uff0c\u57fa\u672c\u5355\u5143");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 5, 0);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 6, 0);
    model.result("pg1").run();

    model.component("comp1").physics("solid").feature("cp1").runCommand("createMaterialbyValue");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").geom("geom2").create("cyl1", "Cylinder");
    model.component("comp2").geom("geom2").feature("cyl1").set("type", "surface");
    model.component("comp2").geom("geom2").feature("cyl1").set("r", "rc");
    model.component("comp2").geom("geom2").feature("cyl1").set("h", "hc");
    model.component("comp2").geom("geom2").feature("cyl1").set("axistype", "x");
    model.component("comp2").geom("geom2").run("cyl1");

    model.component("comp2").view("view4").set("showgrid", false);

    model.component("comp2").geom("geom2").run();

    model.component("comp2").coordSystem("sys2").set("mastercoordsystcomp", "1");

    model.component("comp2").physics().create("lshell", "LayeredShell", "geom2");

    model.study("solidcp1std").feature("solidcp1stat").setSolveFor("/physics/lshell", false);

    model.material().create("lmat1", "LayeredMaterial", "");
    model.material("lmat1").label("\u591a\u5c42\u6750\u6599\uff1a[0/45/90/-45/0]");
    model.material("lmat1").setIndex("link", "solidcp1mat", 0);
    model.material("lmat1").setIndex("rotation", 0, 0);
    model.material("lmat1").setIndex("thickness", "th", 0);
    model.material("lmat1").setIndex("meshPoints", 1, 0);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "solidcp1mat", 1);
    model.material("lmat1").setIndex("rotation", 0, 1);
    model.material("lmat1").setIndex("thickness", "th", 1);
    model.material("lmat1").setIndex("meshPoints", 1, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "solidcp1mat", 1);
    model.material("lmat1").setIndex("rotation", 0, 1);
    model.material("lmat1").setIndex("thickness", "th", 1);
    model.material("lmat1").setIndex("meshPoints", 1, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat1").setIndex("link", "solidcp1mat", 2);
    model.material("lmat1").setIndex("rotation", 0, 2);
    model.material("lmat1").setIndex("thickness", "th", 2);
    model.material("lmat1").setIndex("meshPoints", 1, 2);
    model.material("lmat1").setIndex("tag", "lmat1_3", 2);
    model.material("lmat1").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat1").setIndex("link", "solidcp1mat", 2);
    model.material("lmat1").setIndex("rotation", 0, 2);
    model.material("lmat1").setIndex("thickness", "th", 2);
    model.material("lmat1").setIndex("meshPoints", 1, 2);
    model.material("lmat1").setIndex("tag", "lmat1_3", 2);
    model.material("lmat1").setIndex("rotation", 45, 1);
    model.material("lmat1").setIndex("rotation", 90, 2);
    model.component("comp2").material().create("llmat1", "LayeredMaterialLink");
    model.component("comp2").material("llmat1").set("transform", "antisymmetry");
    model.component("comp2").material("llmat1").set("merge", true);
    model.component("comp2").material("llmat1").set("widthRatio", 0.4);

    model.component("comp2").physics("lshell").feature("lemm1").set("SolidModel", "Anisotropic");
    model.component("comp2").physics("lshell").create("fix1", "Fixed", 1);
    model.component("comp2").physics("lshell").feature("fix1").selection().set(1, 2, 4, 6);
    model.component("comp2").physics("lshell").create("roll1", "Roller", 1);
    model.component("comp2").physics("lshell").feature("roll1").selection().set(9, 10, 11, 12);
    model.component("comp2").physics("lshell").create("bl1", "BodyLoad", 2);
    model.component("comp2").physics("lshell").feature("bl1").selection().set(2);
    model.component("comp2").physics("lshell").feature("bl1").set("forceType", "TotalForce");
    model.component("comp2").physics("lshell").feature("bl1").set("force", new String[]{"0", "0", "Ftot"});

    model.component("comp2").mesh("mesh2").create("map1", "Map");
    model.component("comp2").mesh("mesh2").feature("map1").selection().all();
    model.component("comp2").mesh("mesh2").feature("map1").create("dis1", "Distribution");
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").selection().all();
    model.component("comp2").mesh("mesh2").feature("map1").feature("dis1").set("numelem", 20);
    model.component("comp2").mesh("mesh2").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std1").feature("stat").setSolveFor("/physics/lshell", true);
    model.study("std1").label("\u7814\u7a76 1\uff1a\u7a33\u6001\uff08\u5206\u5c42\u7406\u8bba\uff09");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset3lshelllshl", "LayeredMaterial");
    model.result().dataset("dset3lshelllshl").set("data", "dset3");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset3lshelllshl");
    model.result("pg2").label("\u5e94\u529b (lshell)");
    model.result("pg2").set("showlegends", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"lshell.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg2").feature("surf1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg2").run();
    model.result().dataset("dset3lshelllshl").set("scale", 5);
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("data", "dset3");
    model.result().dataset("cpt1").set("pointx", "hc/2");
    model.result().dataset("cpt1").set("pointy", "rc");
    model.result().dataset("cpt1").set("pointz", "rc");
    model.result().dataset("cpt1").set("snapping", "boundary");
    model.result("pg2").run();
    model.result("pg2").label("\u5e94\u529b (mises)");
    model.result("pg2").set("edges", false);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").label("\u5e94\u529b\uff0c\u5207\u9762 (lshell)");
    model.result("pg3").set("showlegends", true);
    model.result("pg3").create("lss1", "LayeredMaterialSlice");
    model.result("pg3").feature("lss1").set("expr", new String[]{"lshell.misesGp"});
    model.result("pg3").feature("lss1").set("threshold", "manual");
    model.result("pg3").feature("lss1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("lss1").set("colortable", "Prism");
    model.result("pg3").feature("lss1").set("colortabletrans", "none");
    model.result("pg3").feature("lss1").set("colorscalemode", "linear");
    model.result("pg3").feature("lss1").set("locdef", "relative");
    model.result("pg3").feature("lss1").set("localzrel", "lshell.z");
    model.result("pg3").feature("lss1").create("def", "Deform");
    model.result("pg3").feature("lss1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg3").feature("lss1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg3").label("\u5e94\u529b\uff0c\u5207\u9762 (lshell)");
    model.result("pg3").run();
    model.result("pg3").label("\u5e94\u529b\uff0c\u5207\u9762 (mises)");
    model.result("pg3").set("edges", false);
    model.result("pg3").run();
    model.result("pg3").feature("lss1").set("locdef", "layermidplanes");
    model.result("pg3").feature("lss1").set("slicedisplacement", "rectangular");
    model.result("pg3").feature("lss1").set("orientationrectangular", "zx");
    model.result("pg3").feature("lss1").set("xseparation", 0.3);
    model.result("pg3").feature("lss1").set("zseparation", 0.7);
    model.result("pg3").feature("lss1").set("showdescriptions", true);
    model.result("pg3").feature("lss1").set("descriptionseparation", 0.6);
    model.result("pg3").feature("lss1").set("rangecoloractive", true);
    model.result("pg3").feature("lss1").set("rangecolormin", 0);
    model.result("pg3").feature("lss1").set("rangecolormax", "5e6");
    model.result("pg3").feature("lss1").set("resolution", "norefine");
    model.result("pg3").run();
    model.result("pg3").feature("lss1").feature("def").active(false);
    model.result("pg3").run();
    model.result("pg3").set("view", "new");
    model.result("pg3").run();

    model.view("view6").set("showgrid", false);

    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").set("showlegends", true);
    model.result("pg4").create("thr1", "ThroughThickness");
    model.result("pg4").feature("thr1").set("expr", new String[]{"lshell.misesGp"});
    model.result("pg4").feature("thr1").set("legend", true);
    model.result("pg4").feature("thr1").set("posentry", "selection");
    model.result("pg4").feature("thr1").selection().geom("geom2", 0);
    model.result("pg4").feature("thr1").selection().set(1);
    model.result("pg4").label("\u5e94\u529b\uff0c\u5168\u539a\u5ea6 (lshell)");
    model.result("pg4").label("\u5e94\u529b\uff0c\u5168\u539a\u5ea6 (lshell)");
    model.result("pg4").run();
    model.result("pg4").label("\u5e94\u529b\uff0c\u5168\u539a\u5ea6 (Slm11)");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "Slm11 (MPa)");
    model.result("pg4").run();
    model.result("pg4").feature("thr1").set("data", "cpt1");
    model.result("pg4").feature("thr1").set("expr", "lshell.Slm11");
    model.result("pg4").feature("thr1").set("unit", "MPa");
    model.result("pg4").feature("thr1").set("legendmethod", "manual");
    model.result("pg4").feature("thr1").setIndex("legends", "\u5206\u5c42\u7406\u8bba", 0);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").label("\u539a\u5ea6\u548c\u65b9\u5411 (lshell)");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"lshell.d"});
    model.result("pg5").feature("surf1").set("threshold", "manual");
    model.result("pg5").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").label("\u539a\u5ea6");
    model.result("pg5").create("syss1", "CoordSysSurface");
    model.result("pg5").feature("syss1").set("mode", "matrix");
    model.result("pg5").feature("syss1").set("expr", "lshell.dsdX");
    model.result("pg5").label("\u539a\u5ea6\u548c\u65b9\u5411 (lshell)");
    model.result("pg5").run();
    model.result().dataset().create("dset3lshelllshlfi", "LayeredMaterial");
    model.result().dataset("dset3lshelllshlfi").label("\u591a\u5c42\u6750\u6599 2 (\u6750\u6599\u65b9\u5411)");
    model.result().dataset("dset3lshelllshlfi").set("data", "dset3");
    model.result().dataset("dset3lshelllshlfi").set("evaluatein", "layermidplanes");
    model.result().dataset("dset3lshelllshlfi").set("scale", "200*0.057445626465380296");
    model.result().dataset("dset3lshelllshlfi")
         .set("defaultPlotIDs", new String[]{"firstPrincipalMaterialDirection|lshell"});
    model.result().dataset("dset3lshelllshlfi").label("\u591a\u5c42\u6750\u6599 2 (\u6750\u6599\u65b9\u5411)");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset3lshelllshlfi");
    model.result("pg6").label("\u7b2c\u4e00\u4e3b\u6750\u6599\u65b9\u5411 (lshell)");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("edgecolor", "white");
    model.result("pg6").set("showlegends", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"lshell.rot"});
    model.result("pg6").feature("surf1").set("threshold", "manual");
    model.result("pg6").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg6").feature("surf1").set("colortabletrans", "none");
    model.result("pg6").feature("surf1").set("colorscalemode", "linear");
    model.result("pg6").feature("surf1").set("unit", "deg");
    model.result("pg6").feature("surf1").set("colortable", "Rainbow");
    model.result("pg6").feature("surf1").set("rangecoloractive", true);
    model.result("pg6").feature("surf1").set("rangecolormin", -90);
    model.result("pg6").feature("surf1").set("rangecolormax", 90);
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("expr", new String[]{"lshell.tm11", "lshell.tm12", "lshell.tm13"});
    model.result("pg6").feature("arws1").set("placement", "elements");
    model.result("pg6").feature("arws1").set("arrowtype", "cone");
    model.result("pg6").feature("arws1").set("color", "white");
    model.result("pg6").label("\u7b2c\u4e00\u4e3b\u6750\u6599\u65b9\u5411 (lshell)");
    model.result("pg6").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").run();

    model.study().create("std2");
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("plotgroup", "Default");
    model.study("std2").feature("eig").set("storefact", false);
    model.study("std2").feature("eig").set("solnum", "auto");
    model.study("std2").feature("eig").set("notsolnum", "auto");
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/solid", false);
    model.study("std2").feature("eig").setSolveFor("/physics/lshell", true);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u7279\u5f81\u9891\u7387\uff08\u5206\u5c42\u7406\u8bba\uff09");
    model.study("std2").feature("eig").set("neigsactive", true);
    model.study("std2").feature("eig").set("neigs", 12);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("dset5lshelllshl", "LayeredMaterial");
    model.result().dataset("dset5lshelllshl").set("data", "dset5");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset5lshelllshl");
    model.result("pg7").setIndex("looplevel", 1, 0);
    model.result("pg7").label("\u632f\u578b (lshell)");
    model.result("pg7").set("showlegends", false);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"lshell.disp"});
    model.result("pg7").feature("surf1").set("threshold", "manual");
    model.result("pg7").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg7").feature("surf1").set("colortable", "Rainbow");
    model.result("pg7").feature("surf1").set("colortabletrans", "none");
    model.result("pg7").feature("surf1").set("colorscalemode", "linear");
    model.result("pg7").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg7").feature("surf1").create("def", "Deform");
    model.result("pg7").feature("surf1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg7").feature("surf1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result().evaluationGroup().create("std2EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std2EvgFrq").set("data", "dset5");
    model.result().evaluationGroup("std2EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 2\uff1a\u7279\u5f81\u9891\u7387\uff08\u5206\u5c42\u7406\u8bba\uff09)");
    model.result().evaluationGroup("std2EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std2EvgFrq").run();
    model.result("pg7").run();
    model.result("pg7").label("\u632f\u578b\uff08\u5206\u5c42\u7406\u8bba\uff09");
    model.result("pg7").set("view", "new");
    model.result("pg7").run();

    model.view("view7").set("showgrid", false);

    model.result("pg7").run();
    model.result("pg7").set("looplevel", new int[]{3});
    model.result("pg7").run();
    model.result("pg7").set("looplevel", new int[]{5});
    model.result("pg7").run();
    model.result("pg7").set("looplevel", new int[]{7});
    model.result("pg7").run();
    model.result("pg7").set("looplevel", new int[]{9});
    model.result("pg7").run();
    model.result("pg7").set("looplevel", new int[]{12});
    model.result("pg7").run();

    model.component("comp2").physics().create("shell", "Shell", "geom2");

    model.study("solidcp1std").feature("solidcp1stat").setSolveFor("/physics/shell", false);
    model.study("std1").feature("stat").setSolveFor("/physics/shell", false);
    model.study("std2").feature("eig").setSolveFor("/physics/shell", false);

    model.component("comp2").physics("shell").prop("ShellAdvancedSettings").set("UseMITCInterp", false);
    model.component("comp2").physics("shell").create("llem1", "LayeredElastic", 2);
    model.component("comp2").physics("shell").feature("llem1").selection().all();
    model.component("comp2").physics("shell").feature("llem1").set("SolidModel", "Anisotropic");
    model.component("comp2").physics("shell").create("fix1", "Fixed", 1);
    model.component("comp2").physics("shell").feature("fix1").selection().set(1, 2, 4, 6);
    model.component("comp2").physics("shell").create("sym1", "SymmetrySolid1", 1);

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp2").physics("shell").feature("sym1").selection().set(9, 10, 11, 12);
    model.component("comp2").physics("shell").create("fl1", "FaceLoad", 2);
    model.component("comp2").physics("shell").feature("fl1").selection().set(2);
    model.component("comp2").physics("shell").feature("fl1").set("forceType", "TotalForce");
    model.component("comp2").physics("shell").feature("fl1").set("force", new String[]{"0", "0", "Ftot"});

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std3").feature("stat").setSolveFor("/physics/lshell", false);
    model.study("std3").feature("stat").setSolveFor("/physics/shell", true);
    model.study("std3").label("\u7814\u7a76 3\uff1a\u7a33\u6001\uff08ESL \u7406\u8bba\uff09");
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().dataset("lshl1").set("data", "dset7");
    model.result().dataset("lshl1").set("scale", 5);
    model.result().dataset().duplicate("cpt2", "cpt1");
    model.result().dataset("cpt2").set("data", "dset7");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("surf2", "surf1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("expr", "shell.mises");
    model.result("pg2").feature("surf2").set("data", "lshl1");
    model.result("pg2").feature("surf2").set("titletype", "none");
    model.result("pg2").feature("surf2").set("inheritplot", "surf1");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").feature("def").set("expr", new String[]{"u3", "v3", "w3"});
    model.result("pg2").run();
    model.result("pg2").create("tlan1", "TableAnnotation");
    model.result("pg2").feature("tlan1").set("source", "localtable");
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "-2*hc/5", 0, 0);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 0, 0, 1);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 0, 0, 2);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "\u5206\u5c42\u7406\u8bba", 0, 3);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "-2*hc/5", 1, 0);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "hc", 1, 1);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", 0, 1, 2);
    model.result("pg2").feature("tlan1").setIndex("localtablematrix", "ESL \u7406\u8bba", 1, 3);
    model.result("pg2").feature("tlan1").set("showpoint", false);
    model.result("pg2").feature("tlan1").set("anchorpoint", "lowermiddle");
    model.result("pg2").run();
    model.result("pg2").set("plotarrayenable", true);
    model.result("pg2").set("arrayaxis", "y");
    model.result("pg2").set("relpadding", 1.2);
    model.result("pg2").run();
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("thr2", "thr1");
    model.result("pg4").run();
    model.result("pg4").feature("thr2").set("data", "cpt2");
    model.result("pg4").feature("thr2").set("expr", "shell.Sl11");
    model.result("pg4").feature("thr2").set("titletype", "none");
    model.result("pg4").feature("thr2").set("linestyle", "dashed");
    model.result("pg4").feature("thr2").setIndex("legends", "ESL \u7406\u8bba", 0);
    model.result("pg4").run();

    model.study().create("std4");
    model.study("std4").create("eig", "Eigenfrequency");
    model.study("std4").feature("eig").set("plotgroup", "Default");
    model.study("std4").feature("eig").set("storefact", false);
    model.study("std4").feature("eig").set("solnum", "auto");
    model.study("std4").feature("eig").set("notsolnum", "auto");
    model.study("std4").feature("eig").set("outputmap", new String[]{});
    model.study("std4").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std4").feature("eig").set("ngenAUX", "1");
    model.study("std4").feature("eig").set("goalngenAUX", "1");
    model.study("std4").feature("eig").set("ngenAUX", "1");
    model.study("std4").feature("eig").set("goalngenAUX", "1");
    model.study("std4").feature("eig").setSolveFor("/physics/solid", false);
    model.study("std4").feature("eig").setSolveFor("/physics/lshell", false);
    model.study("std4").feature("eig").setSolveFor("/physics/shell", true);
    model.study("std4").label("\u7814\u7a76 4\uff1a\u7279\u5f81\u9891\u7387\uff08ESL \u7406\u8bba\uff09");
    model.study("std4").setGenPlots(false);
    model.study("std4").feature("eig").set("neigsactive", true);
    model.study("std4").feature("eig").set("neigs", 12);
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().dataset().create("lshl2", "LayeredMaterial");
    model.result().dataset("lshl2").set("data", "dset9");
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u632f\u578b\uff08ESL \u7406\u8bba\uff09");
    model.result("pg8").set("data", "none");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("expr", "shell.disp");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").feature("def").set("expr", new String[]{"u3", "v3", "w3"});
    model.result("pg8").run();
    model.result("pg8").set("data", "lshl2");
    model.result("pg8").set("looplevel", new int[]{12});
    model.result("pg8").run();
    model.result().evaluationGroup().create("std4EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std4EvgFrq").set("data", "dset9");
    model.result().evaluationGroup("std4EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 4\uff1a\u7279\u5f81\u9891\u7387\uff08ESL \u7406\u8bba\uff09)");
    model.result().evaluationGroup("std4EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std4EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std4EvgFrq").run();
    model.result().evaluationGroup("std4EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 4\uff1a\u7279\u5f81\u9891\u7387\uff08ESL \u7406\u8bba\uff09)");
    model.result("pg5").run();
    model.result("pg5").set("view", "view7");
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").set("view", "view7");
    model.result("pg6").run();
    model.result("pg3").run();

    model.title("\u590d\u5408\u6750\u6599\u6c14\u74f6\u7684\u7ec6\u89c2\u529b\u5b66\u548c\u5b8f\u89c2\u529b\u5b66");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u7531\u7ea4\u7ef4\u590d\u5408\u6750\u6599\u5c42\u5408\u677f\u5236\u6210\u7684\u6c14\u74f6\u58f3\u4e2d\u7684\u5e94\u529b\uff0c\u5176\u4e2d\u5305\u542b\u4e24\u90e8\u5206\uff1a\n\n\u9996\u5148\uff0c\u6211\u4eec\u4f7f\u7528\u7ec6\u89c2\u529b\u5b66\u6a21\u578b\u786e\u5b9a\u5c42\u5408\u677f\u5355\u5c42\u7684\u5747\u5300\u5f39\u6027\u5c5e\u6027\uff0c\u5e76\u4f7f\u7528\u73af\u6c27\u6811\u8102\u4e2d\u7684\u5355\u6839\u7ea4\u7ef4\u6765\u8ba1\u7b97\u5747\u5300\u5f39\u6027\u5c5e\u6027\uff0c\u901a\u8fc7\u5c06\u786e\u5b9a\u7684\u5f39\u6027\u5c5e\u6027\u4e0e\u94fa\u5c42\u65b9\u5411\u76f8\u7ed3\u5408\uff0c\u6765\u5b9a\u4e49\u5c42\u5408\u677f\u4e2d\u7684\u5355\u4e2a\u5c42\uff08\u94fa\u5c42\uff09\u3002\n\n\u63a5\u4e0b\u6765\uff0c\u6211\u4eec\u4f7f\u7528\u5c42\u4fe1\u606f\u548c\u6307\u5b9a\u7684\u94fa\u5c42\u987a\u5e8f\u6765\u5b9a\u4e49\u6574\u4e2a\u5c42\u5408\u677f\u3002\u7136\u540e\u5bf9\u6c14\u74f6\u58f3\u6267\u884c\u5e94\u529b\u5206\u6790\uff0c\u5e76\u5c06\u4ee5\u4e0b\u4e24\u79cd\u5c42\u5408\u677f\u7406\u8bba\u8fdb\u884c\u6bd4\u8f83\uff1a\u5206\u5c42\u7406\u8bba\u548c\u7b49\u6548\u5355\u5c42 (ESL) \u7406\u8bba\u3002\u9664\u5e94\u529b\u5206\u6790\u4ee5\u5916\uff0c\u672c\u4f8b\u8fd8\u8ba1\u7b97\u4e86\u7279\u5f81\u9891\u7387\u548c\u632f\u578b\uff0c\u4ee5\u7814\u7a76\u6c14\u74f6\u58f3\u7684\u52a8\u6001\u7279\u6027\u3002");

    model.mesh().clearMeshes();

    model.sol("solidcp1sol").clearSolutionData();
    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("composite_cylinder_micromechanics_and_stress_analysis.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
