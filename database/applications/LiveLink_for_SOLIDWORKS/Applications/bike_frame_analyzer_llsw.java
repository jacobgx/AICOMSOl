/*
 * bike_frame_analyzer_llsw.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:47 by COMSOL 6.3.0.290. */
public class bike_frame_analyzer_llsw {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\LiveLink_for_SOLIDWORKS\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("cad1", "LiveLinkSOLIDWORKS");
    model.component("comp1").geom("geom1").feature("cad1").updateCadParamTable(false, false);
    model.component("comp1").geom("geom1").feature("cad1").importData();
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("Frame");

    model.param().set("hasCutPlanes", "1");
    model.param().descr("hasCutPlanes", "Cut planes boolean detection");
    model.param().set("useSymmetry", "0");
    model.param().descr("useSymmetry", "Symmetry activation condition");
    model.param().set("E_user", "70e9[Pa]");
    model.param().descr("E_user", "User defined Young's modulus");
    model.param().set("nu_user", "0.33");
    model.param().descr("nu_user", "User defined Poisson's ratio");
    model.param().set("rho_user", "2700[kg/m^3]");
    model.param().descr("rho_user", "User defined density");
    model.param().set("sigma_yield", "250[MPa]");
    model.param().descr("sigma_yield", "Yield strength");
    model.param().set("sigma_yield_user", "250[MPa]");
    model.param().descr("sigma_yield_user", "User defined yield strength");
    model.param().set("rho", "2700[kg/m^3]");
    model.param().descr("rho", "Density for weight evaluation");
    model.param().set("F_fork", "0[N]");
    model.param().descr("F_fork", "Fork load");
    model.param().set("L_crank", "170[mm]");
    model.param().descr("L_crank", "Crank length");
    model.param().set("H_crank", "35[mm]");
    model.param().descr("H_crank", "Crank thickness");
    model.param().set("F_pedal", "500[N]");
    model.param().descr("F_pedal", "Pedal load");
    model.param().set("crank_angle", "0[deg]");
    model.param().descr("crank_angle", "Crank position angle");
    model.param().set("L_seat", "100[mm]");
    model.param().descr("L_seat", "Seat post length");
    model.param().set("F_seat", "350[N]");
    model.param().descr("F_seat", "Seat load");
    model.param().set("L_stem", "60[mm]");
    model.param().descr("L_stem", "Stem length");
    model.param().set("H_stem", "10[mm]");
    model.param().descr("H_stem", "Stem height");
    model.param().set("W_handlebar", "400[mm]");
    model.param().descr("W_handlebar", "Handlebar width");
    model.param().set("F_stem", "150[N]");
    model.param().descr("F_stem", "Stem load");
    model.param().set("isStemLoadSymmetric", "1");
    model.param().descr("isStemLoadSymmetric", "Symmetry stem load detection");
    model.param().set("mesh_scale", "1");
    model.param().descr("mesh_scale", "Mesh scale factor");
    model.param().set("crank_angle_step", "3");
    model.param().descr("crank_angle_step", "Crank angle step");
    model.param().set("max_stress_scale", "0.5");
    model.param().descr("max_stress_scale", "Maximum allowable stress factor");

    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("if1", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif1", "EndIf", "if1");
    model.component("comp1").geom("geom1").feature("if1").label("If has cut planes");
    model.component("comp1").geom("geom1").feature("if1").set("condition", "hasCutPlanes==1");
    model.component("comp1").geom("geom1").create("pard1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard1").selection("domain").named("boxsel1");
    model.component("comp1").geom("geom1").feature("pard1").set("partitionwith", "objects");
    model.component("comp1").geom("geom1").feature("pard1").selection("object").named("cad1_Cutplanes");
    model.component("comp1").geom("geom1").feature("pard1").set("repairtoltype", "absolute");
    model.component("comp1").geom("geom1").feature("pard1").set("absrepairtol", "LL_I/1e4");
    model.component("comp1").geom("geom1").run("pard1");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").label("Seat tube top");
    model.component("comp1").geom("geom1").feature("adjsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel1").set("selshow", false);
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"cad1_Seat"});
    model.component("comp1").geom("geom1").feature("adjsel1").set("outputdim", 3);
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("adjsel2", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel2").set("input", new String[]{"adjsel1"});
    model.component("comp1").geom("geom1").feature("adjsel2").set("outputdim", 3);
    model.component("comp1").geom("geom1").feature("adjsel2").label("Connection");
    model.component("comp1").geom("geom1").run("adjsel2");
    model.component("comp1").geom("geom1").create("adjsel3", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel3").label("Connection tubes");
    model.component("comp1").geom("geom1").feature("adjsel3").set("selshow", false);
    model.component("comp1").geom("geom1").feature("adjsel3").set("input", new String[]{"adjsel2"});
    model.component("comp1").geom("geom1").feature("adjsel3").set("outputdim", 3);
    model.component("comp1").geom("geom1").run("adjsel3");
    model.component("comp1").geom("geom1").create("adjsel4", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel4").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel4").set("input", new String[]{"cad1_Dropout"});
    model.component("comp1").geom("geom1").feature("adjsel4").label("Dropout");
    model.component("comp1").geom("geom1").feature("adjsel4").set("outputdim", 3);
    model.component("comp1").geom("geom1").run("adjsel4");
    model.component("comp1").geom("geom1").create("adjsel5", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel5").label("Stays");
    model.component("comp1").geom("geom1").feature("adjsel5").set("selshow", false);
    model.component("comp1").geom("geom1").feature("adjsel5").set("input", new String[]{"adjsel4"});
    model.component("comp1").geom("geom1").feature("adjsel5").set("outputdim", 3);
    model.component("comp1").geom("geom1").run("adjsel5");
    model.component("comp1").geom("geom1").create("intsel1", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel1").set("input", new String[]{"adjsel3", "adjsel5"});
    model.component("comp1").geom("geom1").feature("intsel1").label("Seat stays");
    model.component("comp1").geom("geom1").run("intsel1");
    model.component("comp1").geom("geom1").create("adjsel6", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel6").label("Bottom bracket");
    model.component("comp1").geom("geom1").feature("adjsel6").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel6").set("input", new String[]{"cad1_Bottom_bracket"});
    model.component("comp1").geom("geom1").feature("adjsel6").set("outputdim", 3);
    model.component("comp1").geom("geom1").run("adjsel6");
    model.component("comp1").geom("geom1").create("adjsel7", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel7").label("Bottom bracket tubes");
    model.component("comp1").geom("geom1").feature("adjsel7").set("selshow", false);
    model.component("comp1").geom("geom1").feature("adjsel7").set("input", new String[]{"adjsel6"});
    model.component("comp1").geom("geom1").feature("adjsel7").set("outputdim", 3);
    model.component("comp1").geom("geom1").run("adjsel7");
    model.component("comp1").geom("geom1").create("intsel2", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel2").set("selshow", false);
    model.component("comp1").geom("geom1").feature("intsel2").set("input", new String[]{"adjsel3", "adjsel7"});
    model.component("comp1").geom("geom1").feature("intsel2").label("Seat tube bottom");
    model.component("comp1").geom("geom1").run("intsel2");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("Seat tubes");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"adjsel1", "intsel2"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("intsel3", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel3").label("Chain stays");
    model.component("comp1").geom("geom1").feature("intsel3").set("input", new String[]{"adjsel5", "adjsel7"});
    model.component("comp1").geom("geom1").run("intsel3");
    model.component("comp1").geom("geom1").create("adjsel8", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel8").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel8").set("selshow", false);
    model.component("comp1").geom("geom1").feature("adjsel8").set("input", new String[]{"cad1_Head_tube"});
    model.component("comp1").geom("geom1").feature("adjsel8").set("outputdim", 3);
    model.component("comp1").geom("geom1").feature("adjsel8").label("Head tubes");
    model.component("comp1").geom("geom1").run("adjsel8");
    model.component("comp1").geom("geom1").create("adjsel9", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel9").label("Top/Down tubes");
    model.component("comp1").geom("geom1").feature("adjsel9").set("input", new String[]{"adjsel8"});
    model.component("comp1").geom("geom1").feature("adjsel9").set("outputdim", 3);
    model.component("comp1").geom("geom1").run("adjsel9");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init();
    model.component("comp1").geom("geom1").feature("del1").selection("input").named("cad1_Cutplanes");
    model.component("comp1").geom("geom1").run("endif1");
    model.component("comp1").geom("geom1").create("if2", "If");
    model.component("comp1").geom("geom1").feature().createAfter("endif2", "EndIf", "if2");
    model.component("comp1").geom("geom1").feature("if2").label("If symmetry");
    model.component("comp1").geom("geom1").feature("if2").set("condition", "useSymmetry == 1");
    model.component("comp1").geom("geom1").run("if2");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp1").set("selresultshow", "edg");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("pard2", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard2").selection("domain").named("boxsel1");
    model.component("comp1").geom("geom1").feature("pard2").set("repairtoltype", "absolute");
    model.component("comp1").geom("geom1").feature("pard2").set("absrepairtol", "LL_I/1e4");
    model.component("comp1").geom("geom1").run("pard2");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").label("Deleted domains");
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymin", 0.1);
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("del2").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del2").selection("input").named("boxsel2");
    model.component("comp1").geom("geom1").run("del2");
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3").label("Symmetry boundaries");
    model.component("comp1").geom("geom1").feature("boxsel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel3").set("ymin", -0.1);
    model.component("comp1").geom("geom1").feature("boxsel3").set("ymax", 1);
    model.component("comp1").geom("geom1").feature("boxsel3").set("condition", "inside");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("Symmetry");
    model.component("comp1").geom("geom1").feature("boxsel3").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").create("adjsel10", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel10").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel10").set("input", new String[]{"csel1"});
    model.component("comp1").geom("geom1").feature("adjsel10").set("outputdim", 1);
    model.component("comp1").geom("geom1").feature("adjsel10").label("Symmetry edges");
    model.component("comp1").geom("geom1").feature("adjsel10").set("selshow", false);
    model.component("comp1").geom("geom1").run("adjsel10");
    model.component("comp1").geom("geom1").create("adjsel11", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel11").label("Stem edges");
    model.component("comp1").geom("geom1").feature("adjsel11").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel11").set("input", new String[]{"cad1_Stem"});
    model.component("comp1").geom("geom1").feature("adjsel11").set("outputdim", 1);
    model.component("comp1").geom("geom1").feature("adjsel11").set("selshow", false);
    model.component("comp1").geom("geom1").run("adjsel11");
    model.component("comp1").geom("geom1").create("intsel4", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel4").label("Symmetry stem edges");
    model.component("comp1").geom("geom1").feature("intsel4").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("intsel4").set("selshow", false);
    model.component("comp1").geom("geom1").feature("intsel4").set("input", new String[]{"adjsel10", "adjsel11"});
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("Stem (cog)");
    model.component("comp1").geom("geom1").feature("intsel4").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("intsel4");
    model.component("comp1").geom("geom1").create("adjsel12", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel12").label("Seat edges");
    model.component("comp1").geom("geom1").feature("adjsel12").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel12").set("input", new String[]{"cad1_Seat"});
    model.component("comp1").geom("geom1").feature("adjsel12").set("outputdim", 1);
    model.component("comp1").geom("geom1").feature("adjsel12").set("selshow", false);
    model.component("comp1").geom("geom1").run("adjsel12");
    model.component("comp1").geom("geom1").create("intsel5", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel5").label("Symmetry seat edges");
    model.component("comp1").geom("geom1").feature("intsel5").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("intsel5").set("selshow", false);
    model.component("comp1").geom("geom1").feature("intsel5").set("input", new String[]{"adjsel10", "adjsel12"});
    model.component("comp1").geom("geom1").selection().create("csel3", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel3").label("Seat (cog)");
    model.component("comp1").geom("geom1").feature("intsel5").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("intsel5");
    model.component("comp1").geom("geom1").create("adjsel13", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel13").label("BB edges");
    model.component("comp1").geom("geom1").feature("adjsel13").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel13").set("input", new String[]{"cad1_Bottom_bracket"});
    model.component("comp1").geom("geom1").feature("adjsel13").set("outputdim", 1);
    model.component("comp1").geom("geom1").feature("adjsel13").set("selshow", false);
    model.component("comp1").geom("geom1").run("adjsel13");
    model.component("comp1").geom("geom1").create("intsel6", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel6").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("intsel6").set("selshow", false);
    model.component("comp1").geom("geom1").feature("intsel6").set("input", new String[]{"adjsel10", "adjsel13"});
    model.component("comp1").geom("geom1").feature("intsel6").label("Symmetry BB edges");
    model.component("comp1").geom("geom1").selection().create("csel4", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel4").label("BB (cog)");
    model.component("comp1").geom("geom1").feature("intsel6").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").run("intsel6");
    model.component("comp1").geom("geom1").create("else1", "Else");
    model.component("comp1").geom("geom1").run("else1");
    model.component("comp1").geom("geom1").create("adjsel14", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel14").label("Stem edges without symmetry");
    model.component("comp1").geom("geom1").feature("adjsel14").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel14").set("selshow", false);
    model.component("comp1").geom("geom1").feature("adjsel14").set("input", new String[]{"cad1_Stem"});
    model.component("comp1").geom("geom1").feature("adjsel14").set("outputdim", 1);
    model.component("comp1").geom("geom1").feature("adjsel14").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("adjsel14");
    model.component("comp1").geom("geom1").create("adjsel15", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel15").label("Seat edges without symmetry");
    model.component("comp1").geom("geom1").feature("adjsel15").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel15").set("selshow", false);
    model.component("comp1").geom("geom1").feature("adjsel15").set("input", new String[]{"cad1_Seat"});
    model.component("comp1").geom("geom1").feature("adjsel15").set("outputdim", 1);
    model.component("comp1").geom("geom1").feature("adjsel15").set("contributeto", "csel3");
    model.component("comp1").geom("geom1").run("adjsel15");
    model.component("comp1").geom("geom1").create("adjsel16", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel16").label("BB edges without symmetry");
    model.component("comp1").geom("geom1").feature("adjsel16").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel16").set("selshow", false);
    model.component("comp1").geom("geom1").feature("adjsel16").set("input", new String[]{"cad1_Bottom_bracket"});
    model.component("comp1").geom("geom1").feature("adjsel16").set("outputdim", 1);
    model.component("comp1").geom("geom1").feature("adjsel16").set("contributeto", "csel4");
    model.component("comp1").geom("geom1").run("endif2");
    model.component("comp1").geom("geom1").create("boxsel4", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel4").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("boxsel4").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("boxsel4").label("All points");
    model.component("comp1").geom("geom1").feature("boxsel4").set("selshow", false);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("cmf1", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf1").label("Form Composite Fillet");
    model.component("comp1").geom("geom1").feature("cmf1").selection("input").named("cad1_Fillet");
    model.component("comp1").geom("geom1").run("cmf1");
    model.component("comp1").geom("geom1").create("rmd1", "RemoveDetails");
    model.component("comp1").geom("geom1").run("rmd1");
    model.component("comp1").geom("geom1").create("cmf2", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf2").selection("input").named("cad1_Composite");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.material("mat1").label("Aluminum");
    model.material("mat1").set("family", "aluminum");
    model.material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.material("mat1").propertyGroup("Enu").set("E", "70[GPa]");
    model.material("mat1").propertyGroup("Enu").set("nu", "0.33");
    model.material("mat1").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.material("mat1").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.material("mat1").propertyGroup("Murnaghan").set("n", "-350[GPa]");
    model.material().create("mat2", "Common", "");
    model.material("mat2").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat2").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.material("mat2").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.material("mat2").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.material("mat2").propertyGroup("ElastoplasticModel").func().create("int1", "Interpolation");
    model.material("mat2").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.material("mat2").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.material("mat2").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.material("mat2").propertyGroup().create("Swift", "Swift", "Swift");
    model.material("mat2").propertyGroup().create("Voce", "Voce", "Voce");
    model.material("mat2").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.material("mat2").propertyGroup().create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.material("mat2").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.material("mat2").propertyGroup().create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.material("mat2").propertyGroup("ArmstrongFrederick").func().create("int1", "Interpolation");
    model.material("mat2").propertyGroup().create("Norton", "Norton", "Norton");
    model.material("mat2").propertyGroup().create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.material("mat2").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.material("mat2").label("Structural steel");
    model.material("mat2").set("family", "custom");
    model.material("mat2")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.material("mat2").set("diffuse", "custom");
    model.material("mat2")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.material("mat2").set("ambient", "custom");
    model.material("mat2")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.material("mat2").set("noise", true);
    model.material("mat2").set("fresnel", 0.9);
    model.material("mat2").set("roughness", 0.3);
    model.material("mat2").set("diffusewrap", 0);
    model.material("mat2").set("reflectance", 0);
    model.material("mat2").propertyGroup("def").set("lossfactor", "0.02");
    model.material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.material("mat2").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.material("mat2").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.material("mat2").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.material("mat2").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.material("mat2").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.material("mat2").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.material("mat2").propertyGroup("Enu").func("int2").set("funcnametable", new String[][]{{"int1", "1"}});
    model.material("mat2").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.material("mat2").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.material("mat2").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.material("mat2").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.material("mat2").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.material("mat2").propertyGroup("Enu").set("E", "E(T)");
    model.material("mat2").propertyGroup("Enu").set("nu", "nu(T)");
    model.material("mat2").propertyGroup("Enu").addInput("temperature");
    model.material("mat2").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.material("mat2").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.material("mat2").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.material("mat2").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat2").propertyGroup("ElastoplasticModel").func("int1").set("fununit", new String[]{"1"});
    model.material("mat2").propertyGroup("ElastoplasticModel").func("int1").set("argunit", new String[]{"K"});
    model.material("mat2").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.material("mat2").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.material("mat2").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.material("mat2").propertyGroup("ElastoplasticModel").set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.material("mat2").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.material("mat2").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.material("mat2").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.material("mat2").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.material("mat2").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.material("mat2").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat2").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.material("mat2").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.material("mat2").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.material("mat2").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.material("mat2").propertyGroup("Ludwik").addInput("temperature");
    model.material("mat2").propertyGroup("JohnsonCook").label("Johnson-Cook");
    model.material("mat2").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.material("mat2").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.material("mat2").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.material("mat2").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.material("mat2").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.material("mat2").propertyGroup("Swift").set("e0_swi", "0.021");
    model.material("mat2").propertyGroup("Swift").set("n_swi", "0.2");
    model.material("mat2").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.material("mat2").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat2").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.material("mat2").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});

    return model;
  }

  public static Model run2(Model model) {
    model.material("mat2").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.material("mat2").propertyGroup("Voce").set("beta_voc", "9.3");
    model.material("mat2").propertyGroup("Voce").addInput("temperature");
    model.material("mat2").propertyGroup("HockettSherby").label("Hockett-Sherby");
    model.material("mat2").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat2").propertyGroup("HockettSherby").func("int1").set("fununit", new String[]{"1"});
    model.material("mat2").propertyGroup("HockettSherby").func("int1").set("argunit", new String[]{"K"});
    model.material("mat2").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.material("mat2").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.material("mat2").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.material("mat2").propertyGroup("HockettSherby").addInput("temperature");
    model.material("mat2").propertyGroup("ArmstrongFrederick").label("Armstrong-Frederick");
    model.material("mat2").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.material("mat2").propertyGroup("ArmstrongFrederick").func("int1").set("fununit", new String[]{"1"});
    model.material("mat2").propertyGroup("ArmstrongFrederick").func("int1").set("argunit", new String[]{"K"});
    model.material("mat2").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.material("mat2").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.material("mat2").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.material("mat2").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.material("mat2").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.material("mat2").propertyGroup("Norton").set("n_nor", "4.5");
    model.material("mat2").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.material("mat2").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.material("mat2").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.material("mat2").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.material("mat2").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.material("mat2").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.material().create("mat3", "Common", "");
    model.material("mat3").propertyGroup().create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.material("mat3").label("Titanium beta-21S");
    model.material("mat3").set("family", "titanium");
    model.material("mat3").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat3").propertyGroup("def")
         .set("electricconductivity", new String[]{"7.407e5[S/m]", "0", "0", "0", "7.407e5[S/m]", "0", "0", "0", "7.407e5[S/m]"});
    model.material("mat3").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"7.06e-6[1/K]", "0", "0", "0", "7.06e-6[1/K]", "0", "0", "0", "7.06e-6[1/K]"});
    model.material("mat3").propertyGroup("def").set("heatcapacity", "710[J/(kg*K)]");
    model.material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat3").propertyGroup("def").set("density", "4940[kg/m^3]");
    model.material("mat3").propertyGroup("def")
         .set("thermalconductivity", new String[]{"7.5[W/(m*K)]", "0", "0", "0", "7.5[W/(m*K)]", "0", "0", "0", "7.5[W/(m*K)]"});
    model.material("mat3").propertyGroup("Enu").set("E", "105[GPa]");
    model.material("mat3").propertyGroup("Enu").set("nu", "0.33");
    model.material().create("mat4", "Common", "");
    model.material("mat4").propertyGroup("def").set("density", "");
    model.material("mat4").propertyGroup("def").set("poissonsratio", "");
    model.material("mat4").propertyGroup("def").set("youngsmodulus", "");
    model.material("mat4").propertyGroup("def").set("density", new String[]{"rho_user"});
    model.material("mat4").propertyGroup("def").set("poissonsratio", new String[]{"nu_user"});
    model.material("mat4").propertyGroup("def").set("youngsmodulus", new String[]{"E_user"});

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("matlnk1", "Link");

    model.component("comp1").physics("solid").selection().named("geom1_boxsel1");
    model.component("comp1").physics("solid").create("rig1", "RigidConnector", 2);
    model.component("comp1").physics("solid").feature("rig1").label("Dropout");
    model.component("comp1").physics("solid").feature("rig1").selection().named("geom1_cad1_Dropout_bnd");
    model.component("comp1").physics("solid").feature("rig1").setIndex("Direction", true, 2);
    model.component("comp1").physics("solid").feature("rig1").set("RotationType", "ConstrainedRotationGroup");
    model.component("comp1").physics("solid").create("rig2", "RigidConnector", 2);
    model.component("comp1").physics("solid").feature("rig2").label("Fork");
    model.component("comp1").physics("solid").feature("rig2").selection().named("geom1_cad1_Head_tube_bnd");
    model.component("comp1").physics("solid").feature("rig2").set("CenterOfRotationType", "userDefined");
    model.component("comp1").physics("solid").feature("rig2")
         .set("xc", new String[]{"LL_F-sqrt(LL_E^2-LL_D^2)", "0", "LL_D"});
    model.component("comp1").physics("solid").feature("rig2").setIndex("Direction", true, 0);
    model.component("comp1").physics("solid").feature("rig2").setIndex("Direction", true, 1);
    model.component("comp1").physics("solid").feature("rig2").setIndex("Direction", true, 2);
    model.component("comp1").physics("solid").feature("rig2").set("RotationType", "ConstrainedRotationGroup");
    model.component("comp1").physics("solid").feature("rig2").setIndex("ConstrainedRotation", true, 0);
    model.component("comp1").physics("solid").feature("rig2").setIndex("ConstrainedRotation", true, 1);
    model.component("comp1").physics("solid").feature("rig2").setIndex("ConstrainedRotation", true, 2);
    model.component("comp1").physics("solid").feature("rig2").create("rf1", "RigidBodyForce", -1);
    model.component("comp1").physics("solid").feature("rig2").feature("rf1")
         .set("Ft", new String[]{"-F_fork*(2-useSymmetry)/2", "0", "0"});
    model.component("comp1").physics("solid").create("rig3", "RigidConnector", 2);
    model.component("comp1").physics("solid").feature("rig3").label("Bottom bracket");
    model.component("comp1").physics("solid").feature("rig3").selection().named("geom1_cad1_Bottom_bracket_bnd");
    model.component("comp1").physics("solid").feature("rig3")
         .set("CenterOfRotationType", "CentroidOfSelectedEntities");
    model.component("comp1").physics("solid").feature("rig3").set("EntityLevel", "Edge");
    model.component("comp1").physics("solid").feature("rig3").set("RotationType", "ConstrainedRotationGroup");
    model.component("comp1").physics("solid").feature("rig3").feature("cre1").selection().named("geom1_csel4_edg");
    model.component("comp1").physics("solid").feature("rig3").create("rf1", "RigidBodyForce", -1);
    model.component("comp1").physics("solid").feature("rig3").feature("rf1").label("Applied Force Right");
    model.component("comp1").physics("solid").feature("rig3").feature("rf1").set("Include_offset", true);
    model.component("comp1").physics("solid").feature("rig3").feature("rf1")
         .set("x_off", new String[]{"L_crank*cos(crank_angle)", "-LL_H/2-H_crank", "L_crank*sin(crank_angle+pi)"});
    model.component("comp1").physics("solid").feature("rig3").feature("rf1")
         .set("Ft", new String[]{"0", "0", "-F_pedal*(sin(crank_angle)+1)/2"});
    model.component("comp1").physics("solid").feature("rig3").feature().duplicate("rf2", "rf1");
    model.component("comp1").physics("solid").feature("rig3").feature("rf2").label("Applied Force Left");
    model.component("comp1").physics("solid").feature("rig3").feature("rf2")
         .set("x_off", new String[]{"L_crank*cos(crank_angle+pi)", "LL_H/2+H_crank", "L_crank*sin(crank_angle)"});
    model.component("comp1").physics("solid").feature("rig3").feature("rf2")
         .set("Ft", new String[]{"0", "0", "-F_pedal*(sin(crank_angle+pi)+1)/2*(1-useSymmetry)"});
    model.component("comp1").physics("solid").create("rig4", "RigidConnector", 2);
    model.component("comp1").physics("solid").feature("rig4").label("Seat");
    model.component("comp1").physics("solid").feature("rig4").selection().named("geom1_cad1_Seat_bnd");
    model.component("comp1").physics("solid").feature("rig4")
         .set("CenterOfRotationType", "CentroidOfSelectedEntities");
    model.component("comp1").physics("solid").feature("rig4").set("EntityLevel", "Edge");
    model.component("comp1").physics("solid").feature("rig4").feature("cre1").selection().named("geom1_csel3_edg");
    model.component("comp1").physics("solid").feature("rig4").create("rf1", "RigidBodyForce", -1);
    model.component("comp1").physics("solid").feature("rig4").feature("rf1").set("Include_offset", true);
    model.component("comp1").physics("solid").feature("rig4").feature("rf1")
         .set("x_off", new String[]{"-L_seat*cos(LL_B)", "0", "L_seat*sin(LL_B)"});
    model.component("comp1").physics("solid").feature("rig4").feature("rf1")
         .set("Ft", new String[]{"0", "0", "-F_seat*(2-useSymmetry)/2"});
    model.component("comp1").physics("solid").create("rig5", "RigidConnector", 2);
    model.component("comp1").physics("solid").feature("rig5").label("Stem");
    model.component("comp1").physics("solid").feature("rig5").selection().named("geom1_cad1_Stem_bnd");
    model.component("comp1").physics("solid").feature("rig5")
         .set("CenterOfRotationType", "CentroidOfSelectedEntities");
    model.component("comp1").physics("solid").feature("rig5").set("EntityLevel", "Edge");
    model.component("comp1").physics("solid").feature("rig5").set("RotationType", "ConstrainedRotationGroup");
    model.component("comp1").physics("solid").feature("rig5").feature("cre1").selection().named("geom1_csel2_edg");
    model.component("comp1").physics("solid").feature("rig5").create("rf1", "RigidBodyForce", -1);
    model.component("comp1").physics("solid").feature("rig5").feature("rf1").set("Include_offset", true);
    model.component("comp1").physics("solid").feature("rig5").feature("rf1")
         .set("x_off", new String[]{"L_stem", "-W_handlebar/2", "H_stem"});
    model.component("comp1").physics("solid").feature("rig5").feature("rf1")
         .set("Ft", new String[]{"0", "0", "-F_stem"});
    model.component("comp1").physics("solid").feature("rig5").feature("rf1").label("Applied Force Right");
    model.component("comp1").physics("solid").feature("rig5").feature().duplicate("rf2", "rf1");
    model.component("comp1").physics("solid").feature("rig5").feature("rf2").label("Applied Force Left");
    model.component("comp1").physics("solid").feature("rig5").feature("rf2")
         .set("x_off", new String[]{"L_stem", "W_handlebar/2", "H_stem"});
    model.component("comp1").physics("solid").feature("rig5").feature("rf2")
         .set("Ft", new String[]{"0", "0", "F_stem*if(isStemLoadSymmetric,-1,1)*(1-useSymmetry)"});
    model.component("comp1").physics("solid").create("sym1", "SymmetrySolid", 2);
    model.component("comp1").physics("solid").feature("sym1").selection().named("geom1_csel1_bnd");

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "2*LL_I*mesh_scale");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "LL_I*mesh_scale");
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.2);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().named("geom1_adjsel2");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection()
         .named("geom1_cad1_Fillet_bnd");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "0.65*LL_I*mesh_scale");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmin", "0.25*LL_I*mesh_scale");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hcurve", 0.2);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("geom1_intsel1");
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmax", "3*LL_I*mesh_scale");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmin", "3*LL_I*mesh_scale");
    model.component("comp1").mesh("mesh1").create("ftet2", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet2").selection().named("geom1_adjsel4");
    model.component("comp1").mesh("mesh1").create("swe2", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe2").selection().named("geom1_intsel3");
    model.component("comp1").mesh("mesh1").feature("swe2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("size1").set("hmax", "3*LL_I*mesh_scale");
    model.component("comp1").mesh("mesh1").feature("swe2").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("swe2").feature("size1").set("hmin", "3*LL_I*mesh_scale");
    model.component("comp1").mesh("mesh1").create("swe3", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe3").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe3").selection().named("geom1_unisel1");
    model.component("comp1").mesh("mesh1").feature("swe3").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe3").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe3").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe3").feature("size1").set("hmax", "5*LL_I*mesh_scale");
    model.component("comp1").mesh("mesh1").feature("swe3").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("swe3").feature("size1").set("hmin", "5*LL_I*mesh_scale");
    model.component("comp1").mesh("mesh1").create("ftet3", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet3").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet3").selection().named("geom1_adjsel6");
    model.component("comp1").mesh("mesh1").feature("ftet3").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet3").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet3").feature("size1").selection()
         .named("geom1_cad1_Fillet_bnd");
    model.component("comp1").mesh("mesh1").feature("ftet3").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet3").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet3").feature("size1").set("hmax", "LL_I*mesh_scale");
    model.component("comp1").mesh("mesh1").feature("ftet3").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet3").feature("size1").set("hmin", "0.5*LL_I*mesh_scale");
    model.component("comp1").mesh("mesh1").feature("ftet3").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet3").feature("size1").set("hcurve", 0.3);
    model.component("comp1").mesh("mesh1").create("swe4", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe4").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe4").selection().named("geom1_adjsel9");
    model.component("comp1").mesh("mesh1").feature("swe4").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe4").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe4").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe4").feature("size1").set("hmax", "5*LL_I*mesh_scale");
    model.component("comp1").mesh("mesh1").feature("swe4").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("swe4").feature("size1").set("hmin", "5*LL_I*mesh_scale");
    model.component("comp1").mesh("mesh1").create("ftet4", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet4").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet4").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet4").feature("size1").selection()
         .named("geom1_cad1_Fillet_bnd");
    model.component("comp1").mesh("mesh1").feature("ftet4").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet4").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet4").feature("size1").set("hmax", "LL_I*mesh_scale");
    model.component("comp1").mesh("mesh1").feature("ftet4").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet4").feature("size1").set("hmin", "0.5*LL_I*mesh_scale");
    model.component("comp1").mesh("mesh1").feature("ftet4").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet4").feature("size1").set("hcurve", 0.3);
    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").contribute("geom/detail", true);
    model.component("comp1").mesh("mesh2").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh2").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("size").set("hmax", "2*LL_I*mesh_scale");
    model.component("comp1").mesh("mesh2").feature("size").set("hmin", "LL_I*mesh_scale");
    model.component("comp1").mesh("mesh2").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").selection()
         .named("geom1_cad1_Fillet_bnd");
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").set("hmax", "LL_I*mesh_scale");
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").set("hmin", "0.5*LL_I*mesh_scale");
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").set("hcurveactive", true);
    model.component("comp1").mesh("mesh2").feature("ftet1").feature("size1").set("hcurve", 0.3);

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "LL_A", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "rad", 0);
    model.study("std1").feature("stat").setIndex("pname", "LL_A", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "rad", 0);
    model.study("std1").feature("stat").setIndex("pname", "crank_angle", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,pi/(crank_angle_step-1),pi)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_u").set("scaleval", "1e-6");
    model.sol("sol1").feature("s1").feature("fc1").set("linsolver", "i1");

    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").selection().named("geom1_boxsel1");
    model.component("comp1").cpl().create("minop1", "Minimum");
    model.component("comp1").cpl("minop1").selection().named("geom1_boxsel1");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 3, 0);
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
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "von Mises stress (MPa) -- Crank angle = eval(crank_angle*180/pi)<sup>o</sup>");
    model.result("pg1").set("paramindicator", "");
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("unit", "MPa");
    model.result("pg1").create("pttraj1", "PointTrajectories");
    model.result("pg1").feature("pttraj1").label("BB load orientation, right");
    model.result("pg1").feature("pttraj1").set("expr", new String[]{"solid.rig3.rf1.xpax", "0", "0"});
    model.result("pg1").feature("pttraj1").setIndex("expr", "solid.rig3.rf1.xpay", 1);
    model.result("pg1").feature("pttraj1").setIndex("expr", "solid.rig3.rf1.xpaz", 2);
    model.result("pg1").feature("pttraj1").set("linecolor", "gray");
    model.result("pg1").feature("pttraj1").set("pointtype", "arrow");
    model.result("pg1").feature("pttraj1").set("arrowexpr", new String[]{"solid.rig3.rf1.Fx", "0", "0"});
    model.result("pg1").feature("pttraj1").setIndex("arrowexpr", "solid.rig3.rf1.Fy", 1);
    model.result("pg1").feature("pttraj1").setIndex("arrowexpr", "solid.rig3.rf1.Fz", 2);
    model.result("pg1").feature("pttraj1").set("arrowbase", "head");
    model.result("pg1").run();
    model.result("pg1").create("ann1", "Annotation");
    model.result("pg1").feature("ann1").label("BB load value, right");
    model.result("pg1").feature("ann1").set("text", "eval(abs(solid.rig3.rf1.Fz))[N]");
    model.result("pg1").feature("ann1").set("level", "global");
    model.result("pg1").feature("ann1").set("posxexpr", "solid.rig3.rf1.xpax");
    model.result("pg1").feature("ann1").set("posyexpr", "solid.rig3.rf1.xpay");
    model.result("pg1").feature("ann1").set("poszexpr", "solid.rig3.rf1.xpaz");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("pttraj2", "pttraj1");
    model.result("pg1").feature().duplicate("ann2", "ann1");
    model.result("pg1").run();
    model.result("pg1").feature("pttraj2").label("BB load orientation, left");
    model.result("pg1").feature("pttraj2").setIndex("expr", "solid.rig3.rf2.xpax", 0);
    model.result("pg1").feature("pttraj2").setIndex("expr", "solid.rig3.rf2.xpay", 1);
    model.result("pg1").feature("pttraj2").setIndex("expr", "solid.rig3.rf2.xpaz", 2);
    model.result("pg1").feature("pttraj2").setIndex("arrowexpr", "solid.rig3.rf2.Fx", 0);
    model.result("pg1").feature("pttraj2").setIndex("arrowexpr", "solid.rig3.rf2.Fy", 1);
    model.result("pg1").feature("pttraj2").setIndex("arrowexpr", "solid.rig3.rf2.Fz", 2);
    model.result("pg1").feature("pttraj2").create("filt1", "PointTrajectoriesFilter");
    model.result("pg1").run();
    model.result("pg1").feature("pttraj2").feature("filt1").set("type", "logical");
    model.result("pg1").feature("pttraj2").feature("filt1").set("logicalexpr", "!useSymmetry");
    model.result("pg1").run();
    model.result("pg1").feature("ann2").set("text", "eval(abs(solid.rig3.rf2.Fz))[N]");
    model.result("pg1").feature("ann2").set("posxexpr", "solid.rig3.rf2.xpax");
    model.result("pg1").feature("ann2").set("posyexpr", "solid.rig3.rf2.xpay");
    model.result("pg1").feature("ann2").set("poszexpr", "solid.rig3.rf2.xpaz");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("pttraj3", "pttraj1");
    model.result("pg1").feature().duplicate("ann3", "ann1");
    model.result("pg1").feature().duplicate("pttraj4", "pttraj2");
    model.result("pg1").feature().duplicate("ann4", "ann2");
    model.result("pg1").run();
    model.result("pg1").feature("pttraj3").label("Stem load orientation, right");
    model.result("pg1").feature("pttraj3").setIndex("expr", "solid.rig5.rf1.xpax", 0);
    model.result("pg1").feature("pttraj3").setIndex("expr", "solid.rig5.rf1.xpay", 1);
    model.result("pg1").feature("pttraj3").setIndex("expr", "solid.rig5.rf1.xpaz", 2);
    model.result("pg1").feature("pttraj3").setIndex("arrowexpr", "solid.rig5.rf1.Fx", 0);
    model.result("pg1").feature("pttraj3").setIndex("arrowexpr", "solid.rig5.rf1.Fy", 1);
    model.result("pg1").feature("pttraj3").setIndex("arrowexpr", "solid.rig5.rf1.Fz", 2);
    model.result("pg1").run();
    model.result("pg1").feature("ann3").label("Stem load value, right");
    model.result("pg1").feature("ann3").set("text", "eval(abs(solid.rig5.rf1.Fz))[N]");
    model.result("pg1").feature("ann3").set("posxexpr", "solid.rig5.rf1.xpax");
    model.result("pg1").feature("ann3").set("posyexpr", "solid.rig5.rf1.xpay");
    model.result("pg1").feature("ann3").set("poszexpr", "solid.rig5.rf1.xpaz");
    model.result("pg1").run();
    model.result("pg1").feature("pttraj4").label("Stem load orientation, left");
    model.result("pg1").feature("pttraj4").setIndex("expr", "solid.rig5.rf2.xpax", 0);
    model.result("pg1").feature("pttraj4").setIndex("expr", "solid.rig5.rf2.xpay", 1);
    model.result("pg1").feature("pttraj4").setIndex("expr", "solid.rig5.rf2.xpaz", 2);
    model.result("pg1").feature("pttraj4").setIndex("arrowexpr", "solid.rig5.rf2.Fx", 0);
    model.result("pg1").feature("pttraj4").setIndex("arrowexpr", "solid.rig5.rf2.Fy", 1);
    model.result("pg1").feature("pttraj4").setIndex("arrowexpr", "solid.rig5.rf2.Fz", 2);
    model.result("pg1").run();
    model.result("pg1").feature("ann4").label("Stem load value, left");
    model.result("pg1").feature("ann4").set("text", "eval(abs(solid.rig5.rf2.Fz))[N]");
    model.result("pg1").feature("ann4").set("posxexpr", "solid.rig5.rf2.xpax");
    model.result("pg1").feature("ann4").set("posyexpr", "solid.rig5.rf2.xpay");
    model.result("pg1").feature("ann4").set("poszexpr", "solid.rig5.rf2.xpaz");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("pttraj5", "pttraj3");
    model.result("pg1").feature().duplicate("ann5", "ann3");
    model.result("pg1").run();
    model.result("pg1").feature("pttraj5").label("Seat load orientation");
    model.result("pg1").feature("pttraj5").setIndex("expr", "solid.rig4.rf1.xpax", 0);
    model.result("pg1").feature("pttraj5").setIndex("expr", "solid.rig4.rf1.xpay", 1);
    model.result("pg1").feature("pttraj5").setIndex("expr", "solid.rig4.rf1.xpaz", 2);
    model.result("pg1").feature("pttraj5").setIndex("arrowexpr", "solid.rig4.rf1.Fx", 0);
    model.result("pg1").feature("pttraj5").setIndex("arrowexpr", "solid.rig4.rf1.Fy", 1);
    model.result("pg1").feature("pttraj5").setIndex("arrowexpr", "solid.rig4.rf1.Fz", 2);
    model.result("pg1").run();
    model.result("pg1").feature("ann5").label("Seat load value");
    model.result("pg1").feature("ann5").set("text", "eval(abs(solid.rig4.rf1.Fz))[N]");
    model.result("pg1").feature("ann5").set("posxexpr", "solid.rig4.rf1.xpax");
    model.result("pg1").feature("ann5").set("posyexpr", "solid.rig4.rf1.xpay");
    model.result("pg1").feature("ann5").set("poszexpr", "solid.rig4.rf1.xpaz");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("pttraj6", "pttraj5");
    model.result("pg1").feature().duplicate("ann6", "ann5");
    model.result("pg1").run();
    model.result("pg1").feature("pttraj6").label("Fork load orientation");
    model.result("pg1").feature("pttraj6").setIndex("expr", "solid.xcx_rig2", 0);
    model.result("pg1").feature("pttraj6").setIndex("expr", "solid.xcy_rig2", 1);
    model.result("pg1").feature("pttraj6").setIndex("expr", "solid.xcz_rig2", 2);
    model.result("pg1").feature("pttraj6").setIndex("arrowexpr", "solid.rig2.rf1.Fx", 0);
    model.result("pg1").feature("pttraj6").setIndex("arrowexpr", "solid.rig2.rf1.Fy", 1);
    model.result("pg1").feature("pttraj6").setIndex("arrowexpr", "solid.rig2.rf1.Fz", 2);
    model.result("pg1").run();
    model.result("pg1").feature("ann6").label("Fork load value");
    model.result("pg1").feature("ann6").set("text", "eval(abs(solid.rig2.rf1.Fx))[N]");
    model.result("pg1").feature("ann6").set("posxexpr", "solid.xcx_rig2");
    model.result("pg1").feature("ann6").set("posyexpr", "solid.xcy_rig2");
    model.result("pg1").feature("ann6").set("poszexpr", "solid.xcz_rig2");
    model.result("pg1").run();
    model.result("pg1").create("mmv1", "MaxMinVolume");
    model.result("pg1").feature("mmv1").set("expr", "solid.mises");
    model.result("pg1").feature("mmv1").set("unit", "MPa");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg1").feature("mmv1").set("inheritplot", "vol1");
    model.result("pg1").feature("mmv1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("mesh1", "Mesh");
    model.result("pg1").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg1").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg1").feature("mesh1").set("elemcolor", "none");
    model.result("pg1").feature("mesh1").set("inheritplot", "vol1");
    model.result("pg1").feature("mesh1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("pttraj1").active(false);
    model.result("pg1").feature("ann1").active(false);
    model.result("pg1").feature("pttraj2").active(false);
    model.result("pg1").feature("ann2").active(false);
    model.result("pg1").feature("pttraj3").active(false);
    model.result("pg1").feature("ann3").active(false);
    model.result("pg1").feature("pttraj4").active(false);
    model.result("pg1").feature("ann4").active(false);
    model.result("pg1").feature("pttraj5").active(false);
    model.result("pg1").feature("ann5").active(false);
    model.result("pg1").feature("pttraj6").active(false);
    model.result("pg1").feature("ann6").active(false);
    model.result("pg1").feature("mmv1").active(false);
    model.result("pg1").feature("mesh1").active(false);
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("Displacement (solid)");
    model.result("pg2").set("title", "Displacement (mm) -- Crank angle = eval(crank_angle*180/pi)<sup>o</sup>");
    model.result("pg2").run();
    model.result("pg2").feature("vol1").set("expr", "solid.disp");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("Maximum stress");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "maxop1(solid.mises)", 0);
    model.result("pg3").feature("glob1").setIndex("unit", "MPa", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "Maximum stress", 0);
    model.result("pg3").feature("glob1").set("legend", false);
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("Maximum stress range");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").setIndex("expr", "maxop1(solid.sp1)-minop1(solid.sp3)", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "Maximum stress range", 0);
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("Maximum allowable stress verification");
    model.result().numerical("gev1").setIndex("expr", "maxop1(solid.mises)<=(sigma_yield*max_stress_scale)", 0);
    model.result().numerical("gev1").set("dataseries", "maximum");

    model.setExpectedComputationTime("1 min 30 s");

    model.result("pg1").run();

    model.title("\u8f66\u67b6\u5206\u6790\u5668");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u5c06 App \u8fde\u63a5\u5230 SOLIDWORKS\u00ae \u4f1a\u8bdd\n\u2022 \u8bbe\u7f6e\u4e0e\u89e3\u8fdb\u884c\u6bd4\u8f83\u7684\u6700\u5927\u5141\u8bb8\u503c\n\u2022 \u9009\u62e9\u9884\u5b9a\u4e49\u6216\u7528\u6237\u5b9a\u4e49\u7684\u6750\u6599\n\u2022 \u4f7f\u7528\u65b9\u6cd5\u901a\u8fc7\u7ec4\u5408\u6846\u66f4\u6539\u8fb9\u754c\u6761\u4ef6\n\n\u8be5 App \u6839\u636e\u7528\u6237\u53ef\u914d\u7f6e\u7684\u8f7d\u8377\u548c\u7ea6\u675f\u6765\u8ba1\u7b97\u8f66\u67b6\u7684\u5e94\u529b\u5206\u5e03\u548c\u53d8\u5f62\uff0c\u5176\u4e2d\u5229\u7528 LiveLink\u2122 for SOLIDWORKS\u00ae \u6765\u52a0\u8f7d\u51e0\u4f55\uff0c\u5e76\u66f4\u65b0\u8f66\u67b6\u5c3a\u5bf8\u4ee5\u7814\u7a76\u5b83\u4eec\u5bf9\u7ed3\u679c\u7684\u5f71\u54cd\u3002");

    model.label("bike_frame_analyzer_llsw.mph");

    model.result("pg1").run();

    model.title("\u8f66\u67b6\u5206\u6790\u5668");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u5c06 App \u8fde\u63a5\u5230 SOLIDWORKS\u00ae \u4f1a\u8bdd\n\u2022 \u8bbe\u7f6e\u4e0e\u89e3\u8fdb\u884c\u6bd4\u8f83\u7684\u6700\u5927\u5141\u8bb8\u503c\n\u2022 \u9009\u62e9\u9884\u5b9a\u4e49\u6216\u7528\u6237\u5b9a\u4e49\u7684\u6750\u6599\n\u2022 \u4f7f\u7528\u65b9\u6cd5\u901a\u8fc7\u7ec4\u5408\u6846\u66f4\u6539\u8fb9\u754c\u6761\u4ef6\n\n\u8be5 App \u6839\u636e\u7528\u6237\u53ef\u914d\u7f6e\u7684\u8f7d\u8377\u548c\u7ea6\u675f\u6765\u8ba1\u7b97\u8f66\u67b6\u7684\u5e94\u529b\u5206\u5e03\u548c\u53d8\u5f62\uff0c\u5176\u4e2d\u5229\u7528 LiveLink\u2122 for SOLIDWORKS\u00ae \u6765\u52a0\u8f7d\u51e0\u4f55\uff0c\u5e76\u66f4\u65b0\u8f66\u67b6\u5c3a\u5bf8\u4ee5\u7814\u7a76\u5b83\u4eec\u5bf9\u7ed3\u679c\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("bike_frame_analyzer_llsw.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
