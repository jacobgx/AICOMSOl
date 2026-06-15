/*
 * thermal_expansion_of_a_laminated_composite_shell.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:34 by COMSOL 6.3.0.290. */
public class thermal_expansion_of_a_laminated_composite_shell {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Composite_Materials_Module\\Multiphysics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("lshell", "LayeredShell", "geom1");
    model.component("comp1").physics("lshell").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics().create("htlsh", "HeatTransferInShellsLM", "geom1");
    model.component("comp1").physics("htlsh").prop("ShapeProperty").set("order_temperature", "1");

    model.component("comp1").multiphysics().create("tel1", "ThermalExpansionLS", 2);
    model.component("comp1").multiphysics("tel1").set("Heat_physics", "htlsh");
    model.component("comp1").multiphysics("tel1").set("Lshell_physics", "lshell");
    model.component("comp1").multiphysics("tel1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/lshell", true);
    model.study("std1").feature("stat").setSolveFor("/physics/htlsh", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/tel1", true);

    model.param().label("\u53c2\u6570\uff1a\u901a\u7528");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("a", "25[cm]", "\u8fb9\u957f");
    model.param().set("th", "0.125[mm]", "\u8584\u5c42\u539a\u5ea6");
    model.param().set("P0", "10[W]", "\u6c89\u79ef\u5149\u675f\u529f\u7387");
    model.param().set("yp", "0[m]", "\u5149\u675f\u4f4d\u7f6e");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570\uff1a\u7ed3\u6784");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2")
         .set("E1_f", "230[GPa]", "\u7ea4\u7ef4\u6768\u6c0f\u6a21\u91cf\uff0c\u7ea4\u7ef4\u65b9\u5411");
    model.param("par2").set("E_m", "4[GPa]", "\u57fa\u4f53\u6768\u6c0f\u6a21\u91cf");
    model.param("par2").set("D_11", "141.34[GPa]", "\u8584\u5c42\u5f39\u6027\u77e9\u9635\uff0c11 \u5206\u91cf");
    model.param("par2").set("D_12", "3.35[GPa]", "\u8584\u5c42\u5f39\u6027\u77e9\u9635\uff0c12 \u5206\u91cf");
    model.param("par2").set("D_13", "D_12", "\u8584\u5c42\u5f39\u6027\u77e9\u9635\uff0c13 \u5206\u91cf");
    model.param("par2").set("D_22", "10.25[GPa]", "\u8584\u5c42\u5f39\u6027\u77e9\u9635\uff0c22 \u5206\u91cf");
    model.param("par2").set("D_23", "2.83[GPa]", "\u8584\u5c42\u5f39\u6027\u77e9\u9635\uff0c23 \u5206\u91cf");
    model.param("par2").set("D_33", "D_22", "\u8584\u5c42\u5f39\u6027\u77e9\u9635\uff0c33 \u5206\u91cf");
    model.param("par2").set("D_44", "4.52[GPa]", "\u8584\u5c42\u5f39\u6027\u77e9\u9635\uff0c44 \u5206\u91cf");
    model.param("par2").set("D_55", "2.95[GPa]", "\u8584\u5c42\u5f39\u6027\u77e9\u9635\uff0c55 \u5206\u91cf");
    model.param("par2").set("D_66", "D_44", "\u8584\u5c42\u5f39\u6027\u77e9\u9635\uff0c66 \u5206\u91cf");
    model.param().create("par3");
    model.param("par3").label("\u53c2\u6570\uff1a\u70ed");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("v_f", "0.6", "\u7ea4\u7ef4\u7684\u4f53\u79ef\u5206\u6570");
    model.param("par3").set("v_m", "0.4", "\u57fa\u4f53\u7684\u4f53\u79ef\u5206\u6570");
    model.param("par3").set("ht", "20[W/(m^2*K)]", "\u70ed\u4f20\u5bfc\u7cfb\u6570");
    model.param("par3").set("k1", "6.2[W/(m*K)]", "\u8584\u5c42\u5bfc\u70ed\u7cfb\u6570\uff0c11 \u65b9\u5411");
    model.param("par3").set("k2", "0.5[W/(m*K)]", "\u8584\u5c42\u5bfc\u70ed\u7cfb\u6570\uff0c22 \u65b9\u5411");
    model.param("par3").set("k3", "k2", "\u8584\u5c42\u5bfc\u70ed\u7cfb\u6570\uff0c33 \u65b9\u5411");
    model.param("par3")
         .set("alpha1_f", "-0.6e-6[1/K]", "\u7ea4\u7ef4\u70ed\u81a8\u80c0\u7cfb\u6570\uff0c\u7ea4\u7ef4\u65b9\u5411");
    model.param("par3")
         .set("alpha2_f", "8.5e-6[1/K]", "\u7ea4\u7ef4\u70ed\u81a8\u80c0\u7cfb\u6570\uff0c\u5782\u76f4\u7ea4\u7ef4\u65b9\u5411");
    model.param("par3").set("alpha_m", "55e-6[1/K]", "\u57fa\u4f53\u70ed\u81a8\u80c0\u7cfb\u6570");
    model.param("par3")
         .set("alpha1", "(v_f*alpha1_f*E1_f+v_m*alpha_m*E_m)/(v_f*E1_f+v_m*E_m)", "\u8584\u5c42\u70ed\u81a8\u80c0\u7cfb\u6570\uff0c1 \u65b9\u5411");
    model.param("par3")
         .set("alpha2", "v_f*alpha2_f+v_m*alpha_m", "\u8584\u5c42\u70ed\u81a8\u80c0\u7cfb\u6570\uff0c22 \u65b9\u5411");
    model.param("par3").set("alpha3", "alpha2", "\u8584\u5c42\u70ed\u81a8\u80c0\u7cfb\u6570\uff0c33 \u65b9\u5411");
    model.param("par3")
         .comments("The homogenized coefficients of thermal expansion are computed using the Voigt-Reuss mixture rule.");

    model.material().create("mat1", "Common", "");
    model.material("mat1").label("\u6750\u6599\uff1a\u78b3-\u73af\u6c27");
    model.material().create("lmat1", "LayeredMaterial", "");
    model.material("lmat1").label("\u591a\u5c42\u6750\u6599\uff1a[30/-45/75]_as");
    model.material("lmat1").setIndex("rotation", 30, 0);
    model.material("lmat1").setIndex("thickness", "th", 0);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat1", 1);
    model.material("lmat1").setIndex("rotation", 30, 1);
    model.material("lmat1").setIndex("thickness", "th", 1);
    model.material("lmat1").setIndex("meshPoints", 2, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("layername", "\u5c42 2", 1);
    model.material("lmat1").setIndex("link", "mat1", 1);
    model.material("lmat1").setIndex("rotation", 30, 1);
    model.material("lmat1").setIndex("thickness", "th", 1);
    model.material("lmat1").setIndex("meshPoints", 2, 1);
    model.material("lmat1").setIndex("tag", "lmat1_2", 1);
    model.material("lmat1").setIndex("rotation", -45, 1);
    model.material("lmat1").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat1").setIndex("link", "mat1", 2);
    model.material("lmat1").setIndex("rotation", -45, 2);
    model.material("lmat1").setIndex("thickness", "th", 2);
    model.material("lmat1").setIndex("meshPoints", 2, 2);
    model.material("lmat1").setIndex("tag", "lmat1_3", 2);
    model.material("lmat1").setIndex("layername", "\u5c42 3", 2);
    model.material("lmat1").setIndex("link", "mat1", 2);
    model.material("lmat1").setIndex("rotation", -45, 2);
    model.material("lmat1").setIndex("thickness", "th", 2);
    model.material("lmat1").setIndex("meshPoints", 2, 2);
    model.material("lmat1").setIndex("tag", "lmat1_3", 2);
    model.material("lmat1").setIndex("rotation", 75, 2);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", "a");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"0.2*a", "0.6*a"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"0", "0.2*a"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy1").set("displx", "0.8*a");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("copy1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2")
         .set("copy1", "r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("scenelight", false);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u56fa\u5b9a\u8fb9");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(1, 4, 11, 12);

    model.component("comp1").material().create("llmat1", "LayeredMaterialLink");
    model.component("comp1").material("llmat1").set("transform", "antisymmetry");
    model.component("comp1").material("llmat1").set("orientDist", 0.15);
    model.component("comp1").material("llmat1").set("widthRatio", 0.6);

    model.component("comp1").view("view1").set("showgrid", true);

    model.component("comp1").coordSystem("sys1").set("mastercoordsystcomp", "1");

    model.component("comp1").physics("lshell").feature("lemm1").set("SolidModel", "Anisotropic");

    model.material("mat1").propertyGroup().create("Anisotropic", "Anisotropic", "Anisotropic");
    model.material("mat1").propertyGroup("Anisotropic")
         .set("D", new String[]{"D_11", "D_12", "D_22", "D_13", "D_23", "D_33", "0", "0", "0", "D_44", 
         "0", "0", "0", "0", "D_55", "0", "0", "0", "0", "0", 
         "D_66"});
    model.material("mat1").propertyGroup("def").set("density", new String[]{"1"});
    model.material("mat1").propertyGroup("def").set("thermalconductivity", new String[]{"k1", "k2", "k3"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"1"});
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha1", "alpha2", "alpha3"});

    model.component("comp1").physics("htlsh").feature("sls1").set("LayerType", "General");
    model.component("comp1").physics("htlsh").create("dbpi1", "DepositedBeamPowerInterface", 2);
    model.component("comp1").physics("htlsh").feature("dbpi1").selection().set(1);
    model.component("comp1").physics("htlsh").feature("dbpi1").set("applyTo", "select");
    model.component("comp1").physics("htlsh").feature("dbpi1").setIndex("shelllist_lCheck", 0, 0, 0);
    model.component("comp1").physics("htlsh").feature("dbpi1").setIndex("shelllist_lCheck", 0, 1, 0);
    model.component("comp1").physics("htlsh").feature("dbpi1").setIndex("shelllist_lCheck", 0, 2, 0);
    model.component("comp1").physics("htlsh").feature("dbpi1").setIndex("shelllist_lCheck", 0, 3, 0);
    model.component("comp1").physics("htlsh").feature("dbpi1").setIndex("shelllist_lCheck", 0, 4, 0);
    model.component("comp1").physics("htlsh").feature("dbpi1").setIndex("shelllist_lCheck", 0, 5, 0);
    model.component("comp1").physics("htlsh").feature("dbpi1").set("e", new int[]{0, 0, -1});
    model.component("comp1").physics("htlsh").feature("dbpi1").set("P0", "P0");
    model.component("comp1").physics("htlsh").feature("dbpi1").set("O", new String[]{"a/2", "yp", "a"});
    model.component("comp1").physics("htlsh").feature("dbpi1").set("sigma", "a/10");
    model.component("comp1").physics("htlsh").create("hfi1", "HeatFluxInterface", 2);
    model.component("comp1").physics("htlsh").feature("hfi1").selection().set(1);
    model.component("comp1").physics("htlsh").feature("hfi1").set("applyTo", "select");
    model.component("comp1").physics("htlsh").feature("hfi1").setIndex("shelllist_lCheck", 0, 6, 0);
    model.component("comp1").physics("htlsh").feature("hfi1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("htlsh").feature("hfi1").set("h", "ht");
    model.component("comp1").physics("htlsh").create("ltemp1", "LineTemperature", 1);
    model.component("comp1").physics("htlsh").feature("ltemp1").selection().named("sel1");
    model.component("comp1").physics("lshell").create("fix1", "Fixed", 1);
    model.component("comp1").physics("lshell").feature("fix1").selection().named("sel1");
    model.component("comp1").physics("lshell").prop("z").set("z", 0);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "a", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "a", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "yp", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.1*a,a)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").create("fc1", "FullyCoupled");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset1lshelllshl", "LayeredMaterial");
    model.result().dataset("dset1lshelllshl").set("data", "dset1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1lshelllshl");
    model.result("pg1").setIndex("looplevel", 11, 0);
    model.result("pg1").label("\u5e94\u529b (lshell)");
    model.result("pg1").set("showlegends", true);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"lshell.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("surf1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result().dataset().create("lshl1", "LayeredMaterial");
    model.result().dataset("lshl1").set("data", "dset1");
    model.result().dataset("lshl1").selection().geom("geom1", 2);
    model.result().dataset("lshl1").selection().set(1);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6e29\u5ea6\uff0c\u58f3 (htlsh)");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("data", "lshl1");
    model.result("pg2").feature("vol1").setIndex("looplevel", 11, 0);
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("expr", "T");
    model.result("pg2").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg2").feature("vol1").set("smooth", "internal");
    model.result("pg2").feature("vol1").set("data", "lshl1");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result().dataset("dset1lshelllshl").set("scale", 10);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 6, 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 6, 0);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 11, 0);
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
    model.result("pg3").feature("lss1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("lss1").feature("def")
         .set("descr", "\u4f4d\u79fb\u573a \uff08\u6750\u6599\u548c\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg3").label("\u5e94\u529b\uff0c\u5207\u9762 (lshell)");
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 6, 0);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").set("showlegends", true);
    model.result("pg4").create("thr1", "ThroughThickness");
    model.result("pg4").feature("thr1").set("expr", new String[]{"lshell.misesGp"});
    model.result("pg4").feature("thr1").set("legend", true);
    model.result("pg4").feature("thr1").set("posentry", "selection");
    model.result("pg4").feature("thr1").selection().geom("geom1", 0);
    model.result("pg4").feature("thr1").selection().set(1);
    model.result("pg4").label("\u5e94\u529b\uff0c\u5168\u539a\u5ea6 (lshell)");
    model.result("pg4").setIndex("looplevelinput", "last", 0);
    model.result("pg4").label("\u5e94\u529b\uff0c\u5168\u539a\u5ea6 (lshell)");
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevelinput", "manual", 0);
    model.result("pg4").setIndex("looplevel", new int[]{6}, 0);
    model.result("pg4").run();
    model.result("pg4").feature("thr1").selection().set(5, 6, 7, 8);
    model.result("pg4").feature("thr1").set("linestyle", "cycle");
    model.result("pg4").run();
    model.result().dataset().create("dset1lshelllshlge", "LayeredMaterial");
    model.result().dataset("dset1lshelllshlge").label("\u591a\u5c42\u6750\u6599 2 (\u58f3\u51e0\u4f55\u7ed3\u6784)");
    model.result().dataset("dset1lshelllshlge").set("data", "dset1");
    model.result().dataset("dset1lshelllshlge").set("scale", "50*0.03535533905932738");
    model.result().dataset("dset1lshelllshlge")
         .set("defaultPlotIDs", new String[]{"shellGeometry|lshell", "plyAngle|lshell"});
    model.result().dataset("dset1lshelllshlge").label("\u591a\u5c42\u6750\u6599 2 (\u58f3\u51e0\u4f55\u7ed3\u6784)");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1lshelllshlge");
    model.result("pg5").setIndex("looplevel", 11, 0);
    model.result("pg5").label("\u58f3\u51e0\u4f55\u7ed3\u6784 (lshell)");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("showlegends", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg5").feature("surf1").set("threshold", "manual");
    model.result("pg5").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").feature("surf1").set("colortabletrans", "none");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").set("coloring", "uniform");
    model.result("pg5").feature("surf1").set("color", "gray");
    model.result("pg5").label("\u58f3\u51e0\u4f55\u7ed3\u6784 (lshell)");
    model.result("pg5").run();
    model.result().dataset().create("dset1lshelllshlfi", "LayeredMaterial");
    model.result().dataset("dset1lshelllshlfi").label("\u591a\u5c42\u6750\u6599 2 (\u6750\u6599\u65b9\u5411)");
    model.result().dataset("dset1lshelllshlfi").set("data", "dset1");
    model.result().dataset("dset1lshelllshlfi").set("evaluatein", "layermidplanes");
    model.result().dataset("dset1lshelllshlfi").set("scale", "200*0.03535533905932738");
    model.result().dataset("dset1lshelllshlfi")
         .set("defaultPlotIDs", new String[]{"firstPrincipalMaterialDirection|lshell"});
    model.result().dataset("dset1lshelllshlfi").label("\u591a\u5c42\u6750\u6599 2 (\u6750\u6599\u65b9\u5411)");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset1lshelllshlfi");
    model.result("pg6").setIndex("looplevel", 11, 0);
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
    model.result().dataset("dset1lshelllshlge").set("scale", 50);
    model.result().dataset("dset1lshelllshlfi").set("scale", 200);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", "a/2", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", "a", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "a/2", 1, 1);
    model.result().dataset().duplicate("cln2", "cln1");
    model.result().dataset("cln2").setIndex("genpoints", "a/2", 0, 0);
    model.result().dataset("cln2").setIndex("genpoints", 0, 0, 1);
    model.result().dataset("cln2").setIndex("genpoints", "a/2", 1, 0);
    model.result().dataset("cln2").setIndex("genpoints", "a", 1, 1);
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u6cbf X \u8f74\u7684\u6e29\u5ea6\u5206\u5e03");
    model.result("pg7").set("data", "cln1");
    model.result("pg7").set("titletype", "manual");
    model.result("pg7")
         .set("title", "\u7ebf\u7ed3\u679c\u56fe\uff1a\u4e0d\u540c\u5149\u675f\u4f4d\u7f6e\u7684\u6e29\u5ea6\u5206\u5e03");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").set("expr", "T");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "X");
    model.result("pg7").feature("lngr1").set("linestyle", "cycle");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u6cbf Y \u8f74\u7684\u6e29\u5ea6\u5206\u5e03");
    model.result("pg8").set("data", "cln2");
    model.result("pg8").run();
    model.result("pg8").feature("lngr1").set("xdataexpr", "Y");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").run();
    model.result("pg9").label("\u5e94\u529b\uff1avon Mises");
    model.result("pg9").setIndex("looplevel", 6, 0);
    model.result("pg9").set("view", "new");
    model.result("pg9").run();
    model.result("pg9").create("lss1", "LayeredMaterialSlice");
    model.result("pg9").feature("lss1").set("expr", "lshell.mises");
    model.result("pg9").feature("lss1").set("locdef", "layermidplanes");
    model.result("pg9").feature("lss1").set("slicedisplacement", "rectangular");
    model.result("pg9").feature("lss1").set("xseparation", 0.2);
    model.result("pg9").feature("lss1").set("yseparation", 0.2);
    model.result("pg9").feature("lss1").set("showdescriptions", true);
    model.result("pg9").feature("lss1").set("descriptionseparation", 0.35);
    model.result("pg9").feature("lss1").set("colortable", "RainbowLight");
    model.result("pg9").feature("lss1").set("rangecoloractive", true);
    model.result("pg9").feature("lss1").set("rangecolormax", 25);
    model.result("pg9").run();

    model.view("view4").set("showgrid", false);

    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("\u5c42\u5408\u677f\u5e94\u529b\uff1aSlm11");
    model.result("pg10").run();
    model.result("pg10").feature("lss1").set("expr", "lshell.Slm11");
    model.result("pg10").feature("lss1").set("descractive", true);
    model.result("pg10").feature("lss1")
         .set("descr", "\u7b2c\u4e8c\u7c7b\u76ae\u5965\u62c9-\u57fa\u5c14\u970d\u592b\u5e94\u529b (Slm11)");
    model.result("pg10").feature("lss1").set("rangecolormin", -5);
    model.result("pg10").feature("lss1").set("rangecolormax", 5);
    model.result("pg10").run();
    model.result("pg10").run();
    model.result().duplicate("pg11", "pg10");
    model.result("pg11").run();
    model.result("pg11").label("\u5c42\u5408\u677f\u5e94\u529b\uff1aSlm22");
    model.result("pg11").run();
    model.result("pg11").feature("lss1").set("expr", "lshell.Slm22");
    model.result("pg11").feature("lss1")
         .set("descr", "\u7b2c\u4e8c\u7c7b\u76ae\u5965\u62c9-\u57fa\u5c14\u970d\u592b\u5e94\u529b (Slm22)");
    model.result("pg11").run();
    model.result("pg11").run();
    model.result().duplicate("pg12", "pg11");
    model.result("pg12").run();
    model.result("pg12").label("\u5c42\u5408\u677f\u5e94\u529b\uff1aSlm12");
    model.result("pg12").run();
    model.result("pg12").feature("lss1").set("expr", "lshell.Slm12");
    model.result("pg12").feature("lss1")
         .set("descr", "\u7b2c\u4e8c\u7c7b\u76ae\u5965\u62c9-\u57fa\u5c14\u970d\u592b\u5e94\u529b (Slm12)");
    model.result("pg12").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg2");
    model.result().export("anim1").label("\u52a8\u753b\uff1a\u6e29\u5ea6");
    model.result().export("anim1").set("frametime", 0.3);
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("maxframes", 11);
    model.result().export().duplicate("anim2", "anim1");
    model.result().export("anim2").showFrame();
    model.result().export("anim2").set("plotgroup", "pg3");
    model.result().export("anim2").label("\u52a8\u753b\uff1a\u5e94\u529b");
    model.result().export("anim2").showFrame();
    model.result().export().duplicate("anim3", "anim2");
    model.result().export("anim3").showFrame();
    model.result().export("anim3").label("\u52a8\u753b\uff1a\u5c42\u5408\u677f\u5e94\u529b\uff0cSml11");
    model.result().export("anim3").set("plotgroup", "pg10");
    model.result().export("anim3").showFrame();
    model.result("pg12").run();

    model.title("\u5c42\u538b\u590d\u5408\u58f3\u7684\u70ed\u81a8\u80c0");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5bf9\u53d7\u5230\u6c89\u79ef\u5149\u675f\u529f\u7387\u70ed\u6e90\u5f71\u54cd\u7684\u5305\u542b\u516d\u5c42\u53cd\u5bf9\u79f0\u89d2\u94fa\u8bbe\u5c42\u7684\u5c42\u538b\u590d\u5408\u58f3\u8fdb\u884c\u70ed\u5e94\u529b\u5206\u6790\uff0c\u5176\u4e2d\u4f7f\u7528\u5177\u6709\u6b63\u4ea4\u5404\u5411\u5f02\u6027\u6750\u6599\u5c5e\u6027\u7684\u78b3-\u73af\u6c27\u6811\u8102\u6750\u6599\u4f5c\u4e3a\u8584\u5c42\u6750\u6599\uff0c\u5e76\u4f7f\u7528\u57fa\u4e8e\u5206\u5c42\u7406\u8bba\u7684\u65b9\u6cd5\u5bf9\u590d\u5408\u58f3\u7684\u7ed3\u6784\u90e8\u5206\u8fdb\u884c\u5efa\u6a21\u4eff\u771f\u3002\n\n\u6b64\u5916\uff0c\u8fd8\u6f14\u793a\u5982\u4f55\u8ba1\u7b97\u8584\u5c42\u7684\u5747\u5300\u70ed\u81a8\u80c0\u7cfb\u6570\uff0c\u5e76\u5206\u6790\u70ed\u6e90\u4f4d\u7f6e\u5bf9\u5e94\u529b\u548c\u53d8\u5f62\u5206\u5e03\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("thermal_expansion_of_a_laminated_composite_shell.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
