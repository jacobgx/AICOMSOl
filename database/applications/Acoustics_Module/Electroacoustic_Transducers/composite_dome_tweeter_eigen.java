/*
 * composite_dome_tweeter_eigen.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class composite_dome_tweeter_eigen {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Electroacoustic_Transducers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("solid2", "SolidMechanics", "geom1");

    model.param().set("th", "0.1[mm]");
    model.param().descr("th", "Thickness of diaphragm");
    model.param().set("rd", "65[mm]");
    model.param().descr("rd", "Radius of diaphragm");

    model.geom()
         .load(new String[]{"part1"}, "COMSOL_Multiphysics\\Unit_Cells_and_RVEs\\Fiber_Composites\\bidirectional_non_crimp_fiber.mph", new String[]{"part1"});
    model.geom()
         .load(new String[]{"part2"}, "COMSOL_Multiphysics\\Unit_Cells_and_RVEs\\Fiber_Composites\\bidirectional_spread_tow_fiber.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "wfx", "0.9[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "hfx", "0.024[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "wfy", "0.9[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "hfy", "0.024[mm]");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "hm", "0.06[mm]");
    model.component("comp1").geom("geom1").run("pi1");
    model.component("comp1").geom("geom1").create("pi2", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi2").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi2").set("part", "part2");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "wfx", "0.42[mm]");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "hfx", "0.024[mm]");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "wfy", "0.42[mm]");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "hfy", "0.024[mm]");
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "hm", "0.06[mm]");
    model.component("comp1").geom("geom1").feature("pi2").set("displ", new String[]{"0", "2[mm]", "0"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("pi2").active(false);
    model.component("comp1").geom("geom1").run("pi1");

    model.component("comp1").view("view1").set("transparency", true);
    model.component("comp1").view("view1").set("showgrid", false);

    model.component("comp1").geom("geom1").feature("pi1").active(false);
    model.component("comp1").geom("geom1").feature("pi2").active(true);
    model.component("comp1").geom("geom1").run("pi2");
    model.component("comp1").geom("geom1").feature("pi1").active(true);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("transparency", false);

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("TransverseIsotropic", "TransverseIsotropic", "Transversely isotropic");
    model.component("comp1").material("mat1").label("AS-4 carbon fiber");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "1810[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("density", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"-0.5E-6[1/K]", "0", "0", "0", "15E-6[1/K]", "0", "0", "0", "15E-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "");
    model.component("comp1").material("mat1").propertyGroup("TransverseIsotropic").label("Transversely isotropic");
    model.component("comp1").material("mat1").propertyGroup("TransverseIsotropic")
         .set("Evect", new String[]{"235[GPa]", "15[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Evect", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.component("comp1").material("mat1").propertyGroup("TransverseIsotropic")
         .set("nuvect", new String[]{"0.2", "0.0714"});
    model.component("comp1").material("mat1").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("nuvect", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.component("comp1").material("mat1").propertyGroup("TransverseIsotropic").set("Gvect1", "27[GPa]");
    model.component("comp1").material("mat1").propertyGroup("TransverseIsotropic")
         .setPropertyInfo("Gvect1", "Reference: I. M. Daniel and O. Ishai, Engineering Mechanics of Composite Materials, Oxford University Press, Second Edition, 2006.");
    model.component("comp1").material("mat1").selection().named("geom1_pi1_uni1_dom");
    model.component("comp1").material().duplicate("mat2", "mat1");
    model.component("comp1").material("mat2").selection().named("geom1_pi2_uni5_dom");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup()
         .create("IsotropicStrengthParameters", "IsotropicStrengthParameters", "Isotropic strength parameters");
    model.component("comp1").material("mat3").label("Epoxy polymer");
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "1250[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("density", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"75E-6[1/K]", "0", "0", "0", "75E-6[1/K]", "0", "0", "0", "75E-6[1/K]"});
    model.component("comp1").material("mat3").propertyGroup("def")
         .setPropertyInfo("thermalexpansioncoefficient", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat3").propertyGroup("def").set("heatcapacity", "796[J/(kg*K)]");
    model.component("comp1").material("mat3").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", "3.25[GPa]");
    model.component("comp1").material("mat3").propertyGroup("Enu")
         .setPropertyInfo("E", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", "0.265");
    model.component("comp1").material("mat3").propertyGroup("Enu")
         .setPropertyInfo("nu", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat3").propertyGroup("IsotropicStrengthParameters")
         .label("Isotropic strength parameters");
    model.component("comp1").material("mat3").propertyGroup("IsotropicStrengthParameters")
         .set("sigmat", "87.5[MPa]");
    model.component("comp1").material("mat3").propertyGroup("IsotropicStrengthParameters")
         .setPropertyInfo("sigmat", "Reference: K. K. Chawla, Composite Materials: Science and Engineering, Springer, Third Edition, 2011.");
    model.component("comp1").material("mat3").selection().named("geom1_pi1_dif1_dom");
    model.component("comp1").material().duplicate("mat4", "mat3");
    model.component("comp1").material("mat4").selection().named("geom1_pi2_difsel1");

    model.nodeGroup().create("grp1", "Material", "comp1");
    model.nodeGroup("grp1").placeAfter(null);
    model.nodeGroup("grp1").add("mat1");
    model.nodeGroup("grp1").add("mat3");
    model.nodeGroup().create("grp2", "Material", "comp1");
    model.nodeGroup("grp2").placeAfter(null);
    model.nodeGroup("grp2").add("mat2");
    model.nodeGroup("grp2").add("mat4");

    model.component("comp1").coordSystem().create("sys2", "VectorBase");
    model.component("comp1").coordSystem("sys2").setIndex("base", 0, 0, 0);
    model.component("comp1").coordSystem("sys2").setIndex("base", 1, 0, 1);
    model.component("comp1").coordSystem("sys2").setIndex("base", -1, 1, 0);
    model.component("comp1").coordSystem("sys2").setIndex("base", 0, 1, 1);
    model.component("comp1").coordSystem("sys2").set("orthonormal", true);

    model.component("comp1").physics("solid").selection().named("geom1_pi1_boxsel1");
    model.component("comp1").physics("solid").feature("lemm1").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("solid").feature().duplicate("lemm2", "lemm1");
    model.component("comp1").physics("solid").feature("lemm2").selection().set(7);
    model.component("comp1").physics("solid").feature("lemm2").set("coordinateSystem", "sys2");
    model.component("comp1").physics("solid").create("cp1", "CellPeriodicity", 3);
    model.component("comp1").physics("solid").feature("cp1").set("BoundaryExpansion", "PrescribedStrain");
    model.component("comp1").physics("solid").feature("cp1").set("computeDensity", true);
    model.component("comp1").physics("solid").feature("cp1").set("computeElasticityMatrixStandard", true);
    model.component("comp1").physics("solid").feature("cp1").set("avgStrainScale", 0.01);
    model.component("comp1").physics("solid").feature("cp1").set("constraintOptions", "weakConstraints");
    model.component("comp1").physics("solid").feature("cp1").create("bp1", "BoundaryPair", 2);
    model.component("comp1").physics("solid").feature("cp1").feature("bp1").selection().named("geom1_pi1_unisel1");
    model.component("comp1").physics("solid").feature("cp1").feature("bp1").set("manualDestinationSelection", true);
    model.component("comp1").physics("solid").feature("cp1").feature("bp1").selection("destinationDomains")
         .named("geom1_pi1_sel2");
    model.component("comp1").physics("solid").feature("cp1").feature("bp1")
         .set("constraintOptions", "weakConstraints");
    model.component("comp1").physics("solid").feature("cp1").create("bp2", "BoundaryPair", 2);
    model.component("comp1").physics("solid").feature("cp1").feature("bp2").selection().named("geom1_pi1_unisel2");
    model.component("comp1").physics("solid").feature("cp1").feature("bp2").set("manualDestinationSelection", true);
    model.component("comp1").physics("solid").feature("cp1").feature("bp2").selection("destinationDomains")
         .named("geom1_pi1_sel4");
    model.component("comp1").physics("solid").feature("cp1").feature("bp2")
         .set("constraintOptions", "weakConstraints");
    model.component("comp1").physics("solid").feature("cp1").create("bp3", "BoundaryPair", 2);
    model.component("comp1").physics("solid").feature("cp1").feature("bp3").selection().named("geom1_pi1_unisel3");
    model.component("comp1").physics("solid").feature("cp1").feature("bp3").set("manualDestinationSelection", true);
    model.component("comp1").physics("solid").feature("cp1").feature("bp3").selection("destinationDomains")
         .named("geom1_pi1_sel6");
    model.component("comp1").physics("solid").feature("cp1").feature("bp3")
         .set("constraintOptions", "weakConstraints");
    model.component("comp1").physics("solid").feature("cp1").runCommand("createLoadGroupsandStudy");
    model.component("comp1").physics("solid2").selection().named("geom1_pi2_boxsel1");
    model.component("comp1").physics("solid2").feature("lemm1").set("SolidModel", "Orthotropic");
    model.component("comp1").physics("solid2").feature().duplicate("lemm2", "lemm1");
    model.component("comp1").physics("solid2").feature("lemm2").selection().set(6, 8);
    model.component("comp1").physics("solid2").feature("lemm2").set("coordinateSystem", "sys2");
    model.component("comp1").physics("solid2").create("cp1", "CellPeriodicity", 3);
    model.component("comp1").physics("solid2").feature("cp1").set("BoundaryExpansion", "PrescribedStrain");
    model.component("comp1").physics("solid2").feature("cp1").set("computeDensity", true);
    model.component("comp1").physics("solid2").feature("cp1").set("computeElasticityMatrixStandard", true);
    model.component("comp1").physics("solid2").feature("cp1").set("avgStrainScale", 0.01);
    model.component("comp1").physics("solid2").feature("cp1").set("constraintOptions", "weakConstraints");
    model.component("comp1").physics("solid2").feature("cp1").create("bp1", "BoundaryPair", 2);
    model.component("comp1").physics("solid2").feature("cp1").feature("bp1").selection().named("geom1_pi2_unisel2");
    model.component("comp1").physics("solid2").feature("cp1").feature("bp1").set("manualDestinationSelection", true);
    model.component("comp1").physics("solid2").feature("cp1").feature("bp1").selection("destinationDomains")
         .named("geom1_pi2_sel2");
    model.component("comp1").physics("solid2").feature("cp1").feature("bp1")
         .set("constraintOptions", "weakConstraints");
    model.component("comp1").physics("solid2").feature("cp1").create("bp2", "BoundaryPair", 2);
    model.component("comp1").physics("solid2").feature("cp1").feature("bp2").selection().named("geom1_pi2_unisel3");
    model.component("comp1").physics("solid2").feature("cp1").feature("bp2").set("manualDestinationSelection", true);
    model.component("comp1").physics("solid2").feature("cp1").feature("bp2").selection("destinationDomains")
         .named("geom1_pi2_sel4");
    model.component("comp1").physics("solid2").feature("cp1").feature("bp2")
         .set("constraintOptions", "weakConstraints");
    model.component("comp1").physics("solid2").feature("cp1").create("bp3", "BoundaryPair", 2);
    model.component("comp1").physics("solid2").feature("cp1").feature("bp3").selection().named("geom1_pi2_unisel4");
    model.component("comp1").physics("solid2").feature("cp1").feature("bp3").set("manualDestinationSelection", true);
    model.component("comp1").physics("solid2").feature("cp1").feature("bp3").selection("destinationDomains")
         .named("geom1_pi2_sel6");
    model.component("comp1").physics("solid2").feature("cp1").feature("bp3")
         .set("constraintOptions", "weakConstraints");
    model.component("comp1").physics("solid2").feature("cp1").runCommand("createLoadGroupsandStudy");

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "1E-4");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "2E-5");
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("solidcp1std").feature("solidcp1stat").set("useadvanceddisable", true);
    model.study("solidcp1std").feature("solidcp1stat").setSolveFor("/physics/solid2", false);
    model.study("solidcp1std").feature("solidcp1stat").set("disabledphysics", new String[]{"solid2"});
    model.study("solid2cp1std").feature("solid2cp1stat").set("useadvanceddisable", true);
    model.study("solid2cp1std").feature("solid2cp1stat").setSolveFor("/physics/solid", false);
    model.study("solid2cp1std").feature("solid2cp1stat").set("disabledphysics", new String[]{"solid"});
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

    model.study("solid2cp1std").createAutoSequences("all");

    model.sol("solid2cp1sol").runAll();

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 6, 0);
    model.result("pg2").label("\u5e94\u529b (solid2)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("expr", new String[]{"solid2.misesGp"});
    model.result("pg2").feature("vol1").set("threshold", "manual");
    model.result("pg2").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("vol1").set("colortable", "Rainbow");
    model.result("pg2").feature("vol1").set("colortabletrans", "none");
    model.result("pg2").feature("vol1").set("colorscalemode", "linear");
    model.result("pg2").feature("vol1").set("resolution", "custom");
    model.result("pg2").feature("vol1").set("refine", 2);
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").create("def", "Deform");
    model.result("pg2").feature("vol1").feature("def").set("expr", new String[]{"u2", "v2", "w2"});
    model.result("pg2").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("solid2cp1stdEg", "EvaluationGroup");
    model.result().evaluationGroup("solid2cp1stdEg").set("data", "dset2");
    model.result().evaluationGroup("solid2cp1stdEg").set("includeparameters", "off");
    model.result().evaluationGroup("solid2cp1stdEg").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("solid2cp1stdEg")
         .label("\u6709\u6548\u6750\u6599\u5c5e\u6027 (\u5355\u5143\u5468\u671f\u6027\u7814\u7a76 1)");
    model.result().evaluationGroup("solid2cp1stdEg").create("gevdcp1", "EvalGlobal");
    model.result().evaluationGroup("solid2cp1stdEg").feature("gevdcp1")
         .setIndex("expr", "root.comp1.solid2.cp1.rho", 0);
    model.result().evaluationGroup("solid2cp1stdEg").feature("gevdcp1").set("descr", "\u5bc6\u5ea6");
    model.result().evaluationGroup("solid2cp1stdEg").create("gmevescp1", "EvalGlobalMatrix");
    model.result().evaluationGroup("solid2cp1stdEg").feature("gmevescp1").set("expr", "root.comp1.solid2.cp1.D");
    model.result().evaluationGroup("solid2cp1stdEg").feature("gmevescp1").set("descr", "\u5f39\u6027\u77e9\u9635");
    model.result().evaluationGroup("solid2cp1stdEg").run();
    model.result("pg2").run();
    model.result("pg1").run();
    model.result("pg1").selection().geom("geom1", 3);
    model.result("pg1").selection().named("geom1_pi1_boxsel1");
    model.result("pg1").set("applyselectiontodatasetedges", true);
    model.result("pg1").set("view", "new");
    model.result("pg1").run();
    model.result("pg2").set("applyselectiontodatasetedges", false);
    model.result("pg2").run();
    model.result("pg2").selection().geom("geom1", 3);
    model.result("pg2").selection().named("geom1_pi2_boxsel1");
    model.result("pg2").set("applyselectiontodatasetedges", true);
    model.result("pg2").set("view", "new");
    model.result("pg2").run();
    model.result("pg1").run();
    model.result("pg1").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("vol1").feature("def").set("scale", 1);
    model.result("pg2").run();
    model.result("pg2").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("vol1").feature("def").set("scale", 1);

    model.component("comp1").physics("solid").feature("cp1").runCommand("createMaterialbyValue");
    model.component("comp1").physics("solid2").feature("cp1").runCommand("createMaterialbyValue");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").geom("geom2").lengthUnit("mm");
    model.component("comp2").geom("geom2").create("wp1", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp1").set("unite", true);
    model.component("comp2").geom("geom2").feature("wp1").set("quickplane", "yz");
    model.component("comp2").geom("geom2").feature("wp1").geom().create("ca1", "CircularArc");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("ca1").set("center", new int[]{0, -30});
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("ca1").set("r", "rd");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("ca1").set("angle1", 90);
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("ca1").set("angle2", 51.85535);
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("ca1").set("clockwise", true);
    model.component("comp2").geom("geom2").feature("wp1").geom().run("ca1");
    model.component("comp2").geom("geom2").feature("wp1").geom().create("qb1", "QuadraticBezier");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("qb1")
         .setIndex("p", 40.147180487314166, 0, 0);
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("qb1")
         .setIndex("p", 21.119506051203402, 1, 0);
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("qb1").setIndex("p", 40.7, 0, 1);
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("qb1")
         .setIndex("p", 20.680469611083915, 1, 1);
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("qb1").setIndex("p", 40.8, 0, 2);
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("qb1")
         .setIndex("p", 21.119506051203402, 1, 2);
    model.component("comp2").geom("geom2").run("wp1");
    model.component("comp2").geom("geom2").feature().create("rev1", "Revolve");
    model.component("comp2").geom("geom2").feature("rev1").set("angtype", "full");
    model.component("comp2").geom("geom2").runPre("fin");

    model.component("comp2").view("view6").set("showgrid", false);

    model.material().create("mat5", "Common", "");
    model.material("mat5").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat5").label("Titanium beta-21S");
    model.material("mat5").set("family", "titanium");
    model.material("mat5").propertyGroup("def").label("Basic");
    model.material("mat5").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat5").propertyGroup("def")
         .set("electricconductivity", new String[]{"7.407e5[S/m]", "0", "0", "0", "7.407e5[S/m]", "0", "0", "0", "7.407e5[S/m]"});
    model.material("mat5").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.06e-6[1/K]", "0", "0", "0", "7.06e-6[1/K]", "0", "0", "0", "7.06e-6[1/K]"});
    model.material("mat5").propertyGroup("def").set("heatcapacity", "710[J/(kg*K)]");
    model.material("mat5").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat5").propertyGroup("def").set("density", "4940[kg/m^3]");
    model.material("mat5").propertyGroup("def")
         .set("thermalconductivity", new String[]{"7.5[W/(m*K)]", "0", "0", "0", "7.5[W/(m*K)]", "0", "0", "0", "7.5[W/(m*K)]"});
    model.material("mat5").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.material("mat5").propertyGroup("Enu").set("E", "105[GPa]");
    model.material("mat5").propertyGroup("Enu").set("nu", "0.33");
    model.material().create("sw1", "Switch", "");
    model.material("sw1").feature().create("lmat1", "LayeredMaterial", "");
    model.material("sw1").feature("lmat1").setIndex("link", "mat5", 0);
    model.material("sw1").feature("lmat1").setIndex("thickness", "th", 0);
    model.material("sw1").feature().duplicate("lmat2", "lmat1");
    model.material("sw1").feature("lmat2").setIndex("link", "solidcp1mat", 0);
    model.material("sw1").feature().duplicate("lmat3", "lmat2");
    model.material("sw1").feature("lmat3").setIndex("link", "solid2cp1mat", 0);

    model.component("comp2").geom("geom2").run();

    model.component("comp2").material().create("llmat1", "LayeredMaterialLink");

    model.component("comp2").coordSystem("sys3").set("mastercoordsystcomp", "1");

    model.component("comp2").physics().create("shell", "Shell", "geom2");

    model.study("solidcp1std").feature("solidcp1stat").setSolveFor("/physics/shell", true);
    model.study("solid2cp1std").feature("solid2cp1stat").setSolveFor("/physics/shell", true);

    model.component("comp2").physics().create("lshell", "LayeredShell", "geom2");

    model.study("solidcp1std").feature("solidcp1stat").setSolveFor("/physics/lshell", true);
    model.study("solid2cp1std").feature("solid2cp1stat").setSolveFor("/physics/lshell", true);

    model.component("comp2").physics("shell").create("llem1", "LayeredElastic", 2);
    model.component("comp2").physics("shell").feature("llem1").selection().all();
    model.component("comp2").physics("shell").feature("llem1").set("SolidModel", "Anisotropic");
    model.component("comp2").physics("shell").create("fix1", "Fixed", 1);
    model.component("comp2").physics("shell").feature("fix1").selection().set(2, 3, 8, 15);
    model.component("comp2").physics("lshell").feature("lemm1").set("SolidModel", "Anisotropic");
    model.component("comp2").physics("lshell").create("fix1", "Fixed", 1);
    model.component("comp2").physics("lshell").feature("fix1").selection().set(2, 3, 8, 15);

    model.component("comp2").mesh("mesh2").autoMeshSize(4);
    model.component("comp2").mesh("mesh2").automatic(false);
    model.component("comp2").mesh("mesh2").feature("ftri1").selection().set(7, 8);
    model.component("comp2").mesh("mesh2").run("ftri1");
    model.component("comp2").mesh("mesh2").create("cpf1", "CopyFace");
    model.component("comp2").mesh("mesh2").feature("cpf1").selection("source").geom(2);
    model.component("comp2").mesh("mesh2").feature("cpf1").selection("destination").geom(2);
    model.component("comp2").mesh("mesh2").feature("cpf1").selection("source").set(7, 8);
    model.component("comp2").mesh("mesh2").feature("cpf1").selection("destination").set(2, 4);
    model.component("comp2").mesh("mesh2").run("cpf1");
    model.component("comp2").mesh("mesh2").feature().duplicate("cpf2", "cpf1");
    model.component("comp2").mesh("mesh2").feature("cpf2").selection("source").set(2, 4, 7, 8);
    model.component("comp2").mesh("mesh2").feature("cpf2").selection("destination").set(1, 3, 5, 6);
    model.component("comp2").mesh("mesh2").run();

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("plotgroup", "Default");
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std1").feature("eig").setSolveFor("/physics/solid2", true);
    model.study("std1").feature("eig").setSolveFor("/physics/shell", true);
    model.study("std1").feature("eig").setSolveFor("/physics/lshell", true);
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
    model.study("std2").feature("eig").setSolveFor("/physics/solid", true);

    return model;
  }

  public static Model run2(Model model) {
    model.study("std2").feature("eig").setSolveFor("/physics/solid2", true);
    model.study("std2").feature("eig").setSolveFor("/physics/shell", true);
    model.study("std2").feature("eig").setSolveFor("/physics/lshell", true);
    model.study("std1").create("matsw", "MaterialSweep");
    model.study("std1").feature("matsw").setIndex("pname", "matsw.sw1", 0);
    model.study("std1").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std1").feature("matsw").setIndex("plistarr", "range(1,1,3)", 0);
    model.study("std1").feature("matsw").setIndex("pname", "matsw.sw1", 0);
    model.study("std1").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std1").feature("matsw").setIndex("plistarr", "range(1,1,3)", 0);
    model.study("std1").feature("eig").setSolveFor("/physics/solid", false);
    model.study("std1").feature("eig").setSolveFor("/physics/solid2", false);
    model.study("std1").feature("eig").setSolveFor("/physics/lshell", false);
    model.study("std2").create("matsw", "MaterialSweep");
    model.study("std2").feature("matsw").setIndex("pname", "matsw.sw1", 0);
    model.study("std2").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std2").feature("matsw").setIndex("plistarr", "range(1,1,3)", 0);
    model.study("std2").feature("matsw").setIndex("pname", "matsw.sw1", 0);
    model.study("std2").feature("matsw").setIndex("pcase", "all", 0);
    model.study("std2").feature("matsw").setIndex("plistarr", "range(1,1,3)", 0);
    model.study("std2").feature("eig").setSolveFor("/physics/solid", false);
    model.study("std2").feature("eig").setSolveFor("/physics/solid2", false);
    model.study("std2").feature("eig").setSolveFor("/physics/shell", false);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("pm1").feature("so1").set("psol", "sol2");
    model.batch("pm1").run("compute");

    model.result().dataset().create("dset6shelllshl", "LayeredMaterial");
    model.result().dataset("dset6shelllshl").set("data", "dset6");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset6shelllshl");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").setIndex("looplevel", 3, 1);
    model.result("pg3").label("\u632f\u578b (shell)");
    model.result("pg3").set("showlegends", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"shell.disp"});
    model.result("pg3").feature("surf1").set("threshold", "manual");
    model.result("pg3").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result("pg3").feature("surf1").set("inheritplot", "none");
    model.result("pg3").set("data", "dset6shelllshl");
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset6");
    model.result().evaluationGroup("std1EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 1)");
    model.result().evaluationGroup("std1EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std1EvgFrq").run();
    model.result("pg3").set("applyselectiontodatasetedges", false);
    model.result("pg3").run();

    model.study("std2").createAutoSequences("all");

    model.sol().create("sol7");
    model.sol("sol7").study("std2");
    model.sol("sol7").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("pm2").feature("so1").set("psol", "sol7");
    model.batch("pm2").run("compute");

    model.result().dataset().create("dset10lshelllshl", "LayeredMaterial");
    model.result().dataset("dset10lshelllshl").set("data", "dset10");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset10lshelllshl");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").setIndex("looplevel", 3, 1);
    model.result("pg4").label("\u632f\u578b (lshell)");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"lshell.disp"});
    model.result("pg4").feature("surf1").set("threshold", "manual");
    model.result("pg4").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colortabletrans", "none");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg4").feature("surf1").create("def", "Deform");
    model.result("pg4").feature("surf1").feature("def").set("expr", new String[]{"u4", "v4", "w4"});
    model.result("pg4").feature("surf1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result().evaluationGroup().create("std2EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std2EvgFrq").set("data", "dset10");
    model.result().evaluationGroup("std2EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 2)");
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
    model.result("pg4").run();
    model.result("pg3").run();
    model.result("pg3").set("looplevel", new int[]{1, 1});
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("paramindicator", "");
    model.result("pg3").set("view", "new");
    model.result("pg3").set("plotarrayenable", true);
    model.result("pg3").feature("surf1").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("surf2", "surf1");
    model.result("pg3").feature("surf2").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").set("data", "dset6shelllshl");
    model.result("pg3").feature("surf2").set("looplevel", new int[]{1, 2});
    model.result("pg3").feature().duplicate("surf3", "surf2");
    model.result("pg3").feature("surf3").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf3").set("looplevel", new int[]{1, 3});

    model.view("view9").set("showgrid", false);

    model.result("pg3").run();
    model.result("pg3").create("ann1", "Annotation");
    model.result("pg3").feature("ann1").set("arraydim", "1");
    model.result("pg3").feature("ann1").set("latexmarkup", true);
    model.result("pg3").feature("ann1").set("level", "global");
    model.result("pg3").feature("ann1").set("posyexpr", "-rd");
    model.result("pg3").feature("ann1").set("showpoint", false);
    model.result("pg3").feature("ann1").set("anchorpoint", "lowermiddle");
    model.result("pg3").feature("ann1").set("showframe", true);
    model.result("pg3").feature("ann1").set("manualindexing", true);
    model.result("pg3").feature().duplicate("ann2", "ann1");
    model.result("pg3").feature("ann2").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("ann2").set("data", "dset6shelllshl");
    model.result("pg3").feature("ann2").set("looplevel", new int[]{1, 2});
    model.result("pg3").feature("ann2").set("arrayindex", 1);
    model.result("pg3").feature().duplicate("ann3", "ann2");
    model.result("pg3").feature("ann3").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("ann3").set("looplevel", new int[]{1, 3});
    model.result("pg3").feature("ann3").set("arrayindex", 2);
    model.result("pg3").run();
    model.result("pg3").create("tlan1", "TableAnnotation");
    model.result("pg3").feature("tlan1").set("arraydim", "1");
    model.result("pg3").feature("tlan1").set("source", "localtable");
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "-0.05*rd", 0, 0);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "rd", 0, 1);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", 0, 0, 2);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "Titanium", 0, 3);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "1.6*rd", 1, 0);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "rd", 1, 1);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", 0, 1, 2);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "Composite Material 1", 1, 3);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "3.3*rd", 2, 0);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "rd", 2, 1);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", 0, 2, 2);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "Composite Material 2", 2, 3);
    model.result("pg3").feature("tlan1").set("showpoint", false);
    model.result("pg3").feature("tlan1").set("anchorpoint", "uppermiddle");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").set("looplevel", new int[]{1, 1});
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("paramindicator", "");
    model.result("pg4").set("view", "view9");
    model.result("pg4").set("plotarrayenable", true);
    model.result("pg4").feature("surf1").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature().duplicate("surf2", "surf1");
    model.result("pg4").feature("surf2").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("surf2").set("data", "dset10lshelllshl");
    model.result("pg4").feature("surf2").set("looplevel", new int[]{1, 2});
    model.result("pg4").feature().duplicate("surf3", "surf2");
    model.result("pg4").feature("surf3").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("surf3").set("looplevel", new int[]{1, 3});
    model.result("pg4").run();
    model.result("pg4").create("ann1", "Annotation");
    model.result("pg4").feature("ann1").set("arraydim", "1");
    model.result("pg4").feature("ann1").set("latexmarkup", true);
    model.result("pg4").feature("ann1").set("level", "global");
    model.result("pg4").feature("ann1").set("posyexpr", "-rd");
    model.result("pg4").feature("ann1").set("showpoint", false);
    model.result("pg4").feature("ann1").set("anchorpoint", "lowermiddle");
    model.result("pg4").feature("ann1").set("showframe", true);
    model.result("pg4").feature("ann1").set("manualindexing", true);
    model.result("pg4").feature().duplicate("ann2", "ann1");
    model.result("pg4").feature("ann2").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("ann2").set("data", "dset10lshelllshl");
    model.result("pg4").feature("ann2").set("looplevel", new int[]{1, 2});
    model.result("pg4").feature("ann2").set("arrayindex", 1);
    model.result("pg4").feature().duplicate("ann3", "ann2");
    model.result("pg4").feature("ann3").set("arraydim", "1");
    model.result("pg4").run();
    model.result("pg4").feature("ann3").set("looplevel", new int[]{1, 3});
    model.result("pg4").feature("ann3").set("arrayindex", 2);
    model.result("pg4").run();
    model.result("pg4").create("tlan1", "TableAnnotation");
    model.result("pg4").feature("tlan1").set("arraydim", "1");
    model.result("pg4").feature("tlan1").set("source", "localtable");
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "-0.05*rd", 0, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "rd", 0, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0, 0, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "Titanium", 0, 3);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "1.6*rd", 1, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "rd", 1, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0, 1, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "Composite Material 1", 1, 3);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "3.3*rd", 2, 0);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "rd", 2, 1);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", 0, 2, 2);
    model.result("pg4").feature("tlan1").setIndex("localtablematrix", "Composite Material 2", 2, 3);
    model.result("pg4").feature("tlan1").set("showpoint", false);
    model.result("pg4").feature("tlan1").set("anchorpoint", "uppermiddle");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().evaluationGroup("std1EvgFrq").setIndex("looplevelinput", "first", 0);
    model.result().evaluationGroup("std1EvgFrq").run();
    model.result().evaluationGroup("std2EvgFrq").setIndex("looplevelinput", "first", 0);
    model.result().evaluationGroup("std2EvgFrq").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("data", "dset6shelllshl");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "first", 0);
    model.result().evaluationGroup("eg1").create("int1", "IntVolume");
    model.result().evaluationGroup("eg1").feature("int1").setIndex("expr", "shell.rho", 0);
    model.result().evaluationGroup("eg1").feature("int1").setIndex("descr", "Mass", 0);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").set("data", "dset10lshelllshl");
    model.result().evaluationGroup("eg2").setIndex("looplevelinput", "first", 0);
    model.result().evaluationGroup("eg2").create("int1", "IntVolume");
    model.result().evaluationGroup("eg2").feature("int1").setIndex("expr", "lshell.rho", 0);
    model.result().evaluationGroup("eg2").feature("int1").setIndex("descr", "Mass", 0);
    model.result().evaluationGroup("eg2").run();

    model.study("solidcp1std").feature("solidcp1stat").setSolveFor("/physics/shell", false);
    model.study("solidcp1std").feature("solidcp1stat").setSolveFor("/physics/lshell", false);
    model.study("solidcp1std").feature("solidcp1stat").set("disabledcomponent", new String[]{"comp2"});
    model.study("solid2cp1std").feature("solid2cp1stat").setSolveFor("/physics/shell", false);
    model.study("solid2cp1std").feature("solid2cp1stat").setSolveFor("/physics/lshell", false);
    model.study("solid2cp1std").feature("solid2cp1stat").set("disabledcomponent", new String[]{"comp2"});

    model.result("pg3").run();

    model
         .title("\u5e26\u590d\u5408\u632f\u819c\u7684\u5706\u9876\u9ad8\u97f3\u626c\u58f0\u5668 - \u7279\u5f81\u9891\u7387\u5206\u6790");

    model
         .description("\u5728\u626c\u58f0\u5668\u8bbe\u8ba1\u4e2d\uff0c\u5206\u6790\u5171\u632f\u662f\u81f3\u5173\u91cd\u8981\u7684\u4e00\u73af\u3002\u8fd9\u4e9b\u5171\u632f\u53ef\u80fd\u6e90\u81ea\u626c\u58f0\u5668\u7684\u591a\u4e2a\u90e8\u5206\uff0c\u5176\u4e2d\u632f\u819c\u662f\u4e00\u4e2a\u91cd\u8981\u6765\u6e90\u3002\u5c3d\u7ba1\u6709\u591a\u79cd\u4f20\u7edf\u65b9\u6cd5\u53ef\u4ee5\u5e94\u5bf9\u632f\u819c\u7684\u5171\u632f\u95ee\u9898\uff0c\u4f46\u5b83\u4eec\u5404\u6709\u5229\u5f0a\u3002\u4e00\u79cd\u66f4\u4e3a\u5148\u8fdb\u7684\u65b9\u6cd5\u662f\u91c7\u7528\u590d\u5408\u6750\u6599\u7b49\u521b\u65b0\u6750\u6599\u3002\n\n\u672c\u4f8b\u5206\u4e3a\u4e24\u90e8\u5206\u3002\u7b2c\u4e00\u90e8\u5206\u6f14\u793a\u5982\u4f55\u4ece\u4e0d\u540c\u5fae\u89c2\u7ed3\u6784\u7684\u590d\u5408\u6750\u6599\u4e2d\u63d0\u53d6\u5747\u8d28\u6750\u6599\u5c5e\u6027\u3002\u7b2c\u4e8c\u90e8\u5206\u5219\u5bf9\u632f\u819c\u8fdb\u884c\u7279\u5f81\u9891\u7387\u5206\u6790\uff0c\u5c06\u949b\u7b49\u4f20\u7edf\u6750\u6599\u4e0e\u4ece\u7b2c\u4e00\u90e8\u5206\u5206\u6790\u4e2d\u83b7\u5f97\u5c5e\u6027\u7684\u590d\u5408\u6750\u6599\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("solidcp1sol").clearSolutionData();
    model.sol("solid2cp1sol").clearSolutionData();
    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();

    model.label("composite_dome_tweeter_eigen.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
