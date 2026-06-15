/*
 * orthotropic_container_shell.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:34 by COMSOL 6.3.0.290. */
public class orthotropic_container_shell {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Plasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("shell", "Shell", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/shell", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("pressure", "1[N/m^2]", "\u5185\u538b");
    model.param().set("th", "2[cm]", "\u58c1\u539a");
    model.param().set("De", "52[cm]", "\u5916\u90e8\u5706\u67f1\u76f4\u5f84");
    model.param().set("Di", "De-2*th", "\u5185\u90e8\u5706\u67f1\u76f4\u5f84");
    model.param().set("Ri", "Di/2", "\u5185\u90e8\u5706\u67f1\u534a\u5f84");
    model.param().set("sf", "3.5*th", "\u76f4\u8fb9\u6cd5\u5170\u9ad8\u5ea6");
    model.param().set("Rk", "0.1*De", "\u5185\u90e8\u8f6c\u5411\u8282\u534a\u5f84");
    model.param().set("Rc", "0.9*Di", "\u5185\u90e8\u51a0\u534a\u5f84");
    model.param().set("hi", "Rc-sqrt((Rc-Ri)*(Rc+Ri-2*Rk))", "\u5185\u90e8\u5c01\u5934\u9ad8\u5ea6");
    model.param()
         .set("alpha", "atan((Ri-Rk)/(Rc-hi))", "\u51a0\u4e0e\u8f6c\u5411\u8282\u76f8\u4ea4\u7684\u5939\u89d2");
    model.param().set("hcyl", "40[cm]", "\u5706\u67f1\u9ad8\u5ea6\u7684\u4e00\u534a");

    model.component("comp1").geom("geom1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca1").label("\u51a0");
    model.component("comp1").geom("geom1").feature("ca1").set("center", new String[]{"0", "sf-(Rc-hi)"});
    model.component("comp1").geom("geom1").feature("ca1").set("r", "Rc");
    model.component("comp1").geom("geom1").feature("ca1").set("angle1", "90-alpha");
    model.component("comp1").geom("geom1").run("ca1");
    model.component("comp1").geom("geom1").create("ca2", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca2").label("\u68f1\u7f18");
    model.component("comp1").geom("geom1").feature("ca2").set("center", new String[]{"Ri-Rk", "sf"});
    model.component("comp1").geom("geom1").feature("ca2").set("r", "Rk");
    model.component("comp1").geom("geom1").feature("ca2").set("angle2", "90-alpha");
    model.component("comp1").geom("geom1").run("ca2");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").label("\u6cd5\u5170");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"Ri", "sf"});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"Ri", "0"});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").label("\u5706\u7b52");
    model.component("comp1").geom("geom1").feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord1", new String[]{"Ri", "0"});
    model.component("comp1").geom("geom1").feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord2", new String[]{"Ri", "-hcyl"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().all();
    model.component("comp1").cpl("intop1").set("frame", "material");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1")
         .set("y_vol", "intop1(shell.llem1.xdintopall(shell.epe>0))/intop1(shell.llem1.xdintopall(1))");
    model.component("comp1").variable("var1").descr("y_vol", "\u5c48\u670d\u4f53\u79ef\u5206\u6570");

    model.component("comp1").coordSystem("sys1").set("mastercoordsystcomp", "3");
    model.component("comp1").coordSystem("sys1").set("reversenormal", true);

    model.component("comp1").physics("shell").create("llem1", "LayeredElastic", 1);
    model.component("comp1").physics("shell").feature("llem1").selection().all();
    model.component("comp1").physics("shell").feature("llem1").create("lplsty1", "LayeredPlasticity", 1);
    model.component("comp1").physics("shell").feature("llem1").feature("lplsty1").set("YieldFunction", "hill");
    model.component("comp1").physics("shell").feature("llem1").feature("lplsty1")
         .set("IsotropicHardeningModel", "PerfectlyPlastic");
    model.component("comp1").physics("shell").create("symp1", "SymmetryPlane", 0);
    model.component("comp1").physics("shell").feature("symp1").selection().set(3);
    model.component("comp1").physics("shell").create("fl1", "FaceLoad", 1);
    model.component("comp1").physics("shell").feature("fl1").selection().all();
    model.component("comp1").physics("shell").feature("fl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("shell").feature("fl1").set("pressure", "pressure");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Steel AISI 4340");
    model.component("comp1").material("mat1").set("family", "steel");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "205[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.28");
    model.component("comp1").material("mat1").propertyGroup().create("shell", "shell", "Shell");
    model.component("comp1").material("mat1").propertyGroup("shell").set("lth", new String[]{"th"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic_material_model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"381e6", "381e6", "450e6", "240e6", "240e6", "220e6"});
    model.component("comp1").material("mat1").propertyGroup("shell").set("lne", new String[]{"5"});
    model.component("comp1").material("mat1").set("middlePlane", "top");

    model.component("comp1").mesh("mesh1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("dis1").selection().set(2, 4);
    model.component("comp1").mesh("mesh1").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("dis2").selection().set(1, 3);
    model.component("comp1").mesh("mesh1").feature("dis2").set("numelem", 25);
    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().all();
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "pressure", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "N/m^2", 0);
    model.study("std1").feature("stat").setIndex("pname", "pressure", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "N/m^2", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(16,0.2,36)", 0);
    model.study("std1").feature("stat").setIndex("punit", "MPa", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("p1").create("st1", "StopCondition");
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondterminateon", "true", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondActive", true, 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1")
         .setIndex("stopconddesc", "\u505c\u6b62\u8868\u8fbe\u5f0f 1", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondarr", "0.1-comp1.y_vol", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").setIndex("stopcondterminateon", "negative", 0);
    model.sol("sol1").feature("s1").feature("p1").feature("st1").set("storestopcondsol", "stepbefore_stepafter");
    model.sol("sol1").feature("s1").feature("p1").feature("st1").set("stopcondwarn", false);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset1shelllshl", "LayeredMaterial");
    model.result().dataset("dset1shelllshl").set("data", "dset1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1shelllshl");
    model.result("pg1").setIndex("looplevel", 73, 0);
    model.result("pg1").label("\u5e94\u529b (shell)");
    model.result("pg1").set("showlegends", true);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"shell.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.w"});
    model.result("pg1").feature("surf1").set("inheritplot", "none");
    model.result("pg1").set("data", "dset1shelllshl");
    model.result().dataset().create("dset1shelllrev", "Revolve2D");
    model.result().dataset("dset1shelllrev").set("data", "dset1shelllshl");
    model.result().dataset("dset1shelllrev").set("revangle", 225);
    model.result().dataset("dset1shelllrev").set("startangle", -90);
    model.result().dataset("dset1shelllrev").set("hasspacevars", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1shelllrev");
    model.result("pg2").setIndex("looplevel", 73, 0);
    model.result("pg2").label("\u5e94\u529b, 3D (shell)");
    model.result("pg2").set("frametype", "spatial");
    model.result().dataset("dset1shelllrev").label("\u4e8c\u7ef4\u65cb\u8f6c (\u591a\u5c42\u6750\u6599)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"shell.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").set("inheritplot", "none");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result("pg2").set("data", "dset1shelllrev");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1shelllshl");
    model.result("pg3").setIndex("looplevel", 73, 0);
    model.result("pg3").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (shell)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"shell.epeGp"});
    model.result("pg3").feature("surf1").set("inheritplot", "none");
    model.result("pg3").feature("surf1").set("resolution", "normal");
    model.result("pg3").feature("surf1").set("colortabletype", "discrete");
    model.result("pg3").feature("surf1").set("bandcount", 11);
    model.result("pg3").feature("surf1").set("colortable", "AuroraAustralisDark");
    model.result("pg3").feature("surf1").set("descractive", true);
    model.result("pg3").feature("surf1").set("descr", "\u7b49\u6548\u5851\u6027\u5e94\u53d8");
    model.result("pg3").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (shell)");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").set("showlegends", true);
    model.result("pg4").create("thr1", "ThroughThickness");
    model.result("pg4").feature("thr1").set("expr", new String[]{"shell.misesGp"});
    model.result("pg4").feature("thr1").set("legend", true);
    model.result("pg4").feature("thr1").set("posentry", "selection");
    model.result("pg4").feature("thr1").selection().geom("geom1", 0);
    model.result("pg4").feature("thr1").selection().set(1);
    model.result("pg4").label("\u5e94\u529b\uff0c\u5168\u539a\u5ea6 (shell)");
    model.result("pg4").setIndex("looplevelinput", "last", 0);
    model.result("pg4").label("\u5e94\u529b\uff0c\u5168\u539a\u5ea6 (shell)");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 73, 0);
    model.result("pg5").label("\u539a\u5ea6\u548c\u65b9\u5411 (shell)");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("expr", new String[]{"shell.d"});
    model.result("pg5").feature("line1").set("threshold", "manual");
    model.result("pg5").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("line1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("line1").set("colortabletrans", "reverse");
    model.result("pg5").feature("line1").set("colorscalemode", "linear");
    model.result("pg5").feature("line1").set("linetype", "tube");
    model.result("pg5").feature("line1").set("radiusexpr", "shell.d/2");
    model.result("pg5").feature("line1").set("tuberadiusscale", 1);
    model.result("pg5").feature("line1").label("\u539a\u5ea6");
    model.result("pg5").create("syss", "CoordSysLine");
    model.result("pg5").feature("syss").set("sys", "shellsys");
    model.result("pg5").feature("syss").label("\u58f3\u5c40\u90e8\u5750\u6807\u7cfb");
    model.result("pg5").label("\u539a\u5ea6\u548c\u65b9\u5411 (shell)");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset1shelllrev");
    model.result("pg6").setIndex("looplevel", 73, 0);
    model.result("pg6").label("\u9762\u8f7d\u8377 (shell)");
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
    model.result("pg6").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg6").feature("surf1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result("pg6").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg6").feature("surf1").feature("def").set("descractive", true);
    model.result("pg6").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg6").feature("surf1").feature("def").set("scale", "0");
    model.result("pg6").feature("surf1").create("tran1", "Transparency");
    model.result("pg6").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("revcoordsys", "cylindrical");
    model.result("pg6").feature("arws1")
         .set("expr", new String[]{"shell.fl1.far", "shell.fl1.faphi", "shell.fl1.faz"});
    model.result("pg6").feature("arws1").set("placement", "elements");
    model.result("pg6").feature("arws1").set("arrowbase", "tail");
    model.result("pg6").feature("arws1").label("\u9762\u8f7d\u8377 1");
    model.result("pg6").feature("arws1").set("inheritplot", "none");
    model.result("pg6").feature("arws1").create("col", "Color");
    model.result("pg6").feature("arws1").feature("col").set("colortable", "Rainbow");
    model.result("pg6").feature("arws1").feature("col").set("colortabletrans", "none");
    model.result("pg6").feature("arws1").feature("col").set("colorscalemode", "linear");
    model.result("pg6").feature("arws1").feature("col").set("colordata", "arrowlength");
    model.result("pg6").feature("arws1").feature("col").set("coloring", "gradient");
    model.result("pg6").feature("arws1").feature("col").set("topcolor", "red");
    model.result("pg6").feature("arws1").feature("col").set("bottomcolor", "custom");
    model.result("pg6").feature("arws1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg6").feature("arws1").set("color", "red");
    model.result("pg6").feature("arws1").create("def", "Deform");
    model.result("pg6").feature("arws1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg6").feature("arws1").feature("def").set("expr", new String[]{"shell.u", "shell.v", "shell.w"});
    model.result("pg6").feature("arws1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg6").feature("arws1").feature("def").set("descractive", true);
    model.result("pg6").feature("arws1").feature("def").set("scaleactive", true);
    model.result("pg6").feature("arws1").feature("def").set("scale", "0");
    model.result("pg6").feature().move("surf1", 1);
    model.result("pg6").label("\u9762\u8f7d\u8377 (shell)");

    model.nodeGroup().create("dset1shelllgrp", "Results");
    model.nodeGroup("dset1shelllgrp").label("\u5916\u52a0\u8f7d\u8377 (shell)");
    model.nodeGroup("dset1shelllgrp").set("type", "plotgroup");
    model.nodeGroup("dset1shelllgrp").label("\u5916\u52a0\u8f7d\u8377 (shell)");
    model.nodeGroup("dset1shelllgrp").placeAfter("plotgroup", "pg6");
    model.nodeGroup("dset1shelllgrp").add("plotgroup", "pg6");

    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("thr1").selection().set(2);
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature().remove("line1");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("data", "dset1shelllshl");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("expr", "1");
    model.result("pg5").feature("surf1").set("coloring", "uniform");
    model.result("pg5").feature("surf1").set("color", "gray");
    model.result("pg5").run();
    model.result("pg5").feature("syss").set("arrowcount", 15);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u5c48\u670d\u4f53\u79ef");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "\u538b\u529b (N/m<sup>2</sup>)");
    model.result("pg7").set("showlegends", false);
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "y_vol", 0);
    model.result("pg7").run();
    model.result("pg2").run();

    model.title("\u6b63\u4ea4\u6750\u6599\u538b\u529b\u5bb9\u5668 - \u58f3\u7248\u672c");

    model
         .description("\u7531\u8f67\u94a2\u5236\u6210\u7684\u8584\u58c1\u5bb9\u5668\u53d7\u5230\u5185\u90e8\u8d85\u538b\u7684\u4f5c\u7528\uff0c\u7531\u4e8e\u5236\u9020\u65b9\u6cd5\u7684\u5f71\u54cd\uff0c\u9762\u5916\u65b9\u5411\u7684\u5c48\u670d\u5e94\u529b\u5927\u4e8e\u5176\u4ed6\u4e24\u4e2a\u65b9\u5411\u3002\u672c\u4f8b\u4f7f\u7528 Hill \u6b63\u4ea4\u5404\u5411\u5f02\u6027\u5851\u6027\u6a21\u578b\u6a21\u62df\u5c48\u670d\u5f3a\u5ea6\u7684\u5dee\u5f02\uff0c\u8be5\u5bb9\u5668\u7531\u4e00\u4e2a\u5706\u67f1\u4f53\u6784\u6210\uff0c\u4e24\u7aef\u7528\u789f\u5f62\u5c01\u5934\uff08\u4e5f\u79f0\u4e3a Kl\u00f6pper \u5934\uff09\u5bc6\u5c01\u3002\n\n\u672c\u4f8b\u5728\u201c\u58f3\u201d\u63a5\u53e3\u4e2d\u5bf9\u5851\u6027\u5efa\u6a21\uff0c\u5e76\u4f7f\u7528\u8f74\u5bf9\u79f0\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("orthotropic_container_shell.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
