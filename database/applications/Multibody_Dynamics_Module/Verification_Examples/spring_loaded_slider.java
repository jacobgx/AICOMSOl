/*
 * spring_loaded_slider.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:28 by COMSOL 6.3.0.290. */
public class spring_loaded_slider {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/mbd", true);

    model.param().set("a", "0.05[m]");
    model.param().descr("a", "\u6ed1\u5757\u7684\u8fb9\u957f");
    model.param().set("p", "0.5[m]");
    model.param().descr("p", "\u6ed1\u5757\u521d\u59cb\u4f4d\u7f6e");
    model.param().set("omega", "1[rad/s]");
    model.param().descr("omega", "\u89d2\u901f\u5ea6");
    model.param().set("k", "2.5[N/m]");
    model.param().descr("k", "\u5f39\u7c27\u5e38\u6570");
    model.param().set("c", "0.1[N*s/m]");
    model.param().descr("c", "\u963b\u5c3c\u7cfb\u6570");
    model.param().set("rho", "2700[kg/m^3]");
    model.param().descr("rho", "\u5bc6\u5ea6");
    model.param().set("m", "rho*a^3");
    model.param().descr("m", "\u6ed1\u5757\u7684\u8d28\u91cf");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"1", "a"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"a", "a"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"p-a/2", "a"});
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").label("Aluminum");
    model.component("comp1").material("mat1").set("family", "aluminum");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "900[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]", "0", "0", "0", "238[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]", "0", "0", "0", "3.774e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23e-6[1/K]", "0", "0", "0", "23e-6[1/K]", "0", "0", "0", "23e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "70[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.33");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-250[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-330[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-350[GPa]");

    model.component("comp1").physics("mbd").prop("d").set("d", "a");
    model.component("comp1").physics("mbd").prop("InitialValues").set("xc_Init", new String[]{"0", "a/2", "0"});
    model.component("comp1").physics("mbd").prop("InitialValues").set("phit_Init", "omega");
    model.component("comp1").physics("mbd").create("att1", "Attachment", 1);
    model.component("comp1").physics("mbd").feature("att1").selection().set(3);
    model.component("comp1").physics("mbd").create("att2", "Attachment", 1);
    model.component("comp1").physics("mbd").feature("att2").selection().set(6);
    model.component("comp1").physics("mbd").create("prj1", "PrismaticJoint", -1);
    model.component("comp1").physics("mbd").feature("prj1").set("Source", "att1");
    model.component("comp1").physics("mbd").feature("prj1").set("Destination", "att2");
    model.component("comp1").physics("mbd").feature("prj1").create("sd1", "SpringAndDamper", -1);
    model.component("comp1").physics("mbd").feature("prj1").feature("sd1").set("k_u", "k");
    model.component("comp1").physics("mbd").feature("prj1").feature("sd1").set("c_u", "c");
    model.component("comp1").physics("mbd").feature("prj1").create("pm1", "PrescribedMotion", -1);
    model.component("comp1").physics("mbd").feature("prj1").feature("pm1")
         .set("ActivationConditionTranslational", "conditionallyActive");
    model.component("comp1").physics("mbd").feature("prj1").feature("pm1").set("i_up", "(t>=0.1)");
    model.component("comp1").physics("mbd").create("rig1", "RigidConnector", 1);
    model.component("comp1").physics("mbd").feature("rig1").selection().set(1);
    model.component("comp1").physics("mbd").feature("rig1").setIndex("Direction", true, 0);
    model.component("comp1").physics("mbd").feature("rig1").setIndex("Direction", true, 1);
    model.component("comp1").physics("mbd").feature("rig1").set("RotationType", "PrescribedRotationGroup");
    model.component("comp1").physics("mbd").feature("rig1").set("phi0", "omega*t");
    model.component("comp1").physics("mbd").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("mbd").feature("ge1").setIndex("name", "ua", 0, 0);
    model.component("comp1").physics("mbd").feature("ge1")
         .setIndex("equation", "m*uatt+c*uat+k*ua-(m*(p+ua)*omega^2)*(t>=0.1)", 0, 0);
    model.component("comp1").physics("mbd").feature("ge1")
         .setIndex("description", "\u4f4d\u79fb\u89e3\u6790\u503c", 0, 0);
    model.component("comp1").physics("mbd").feature("ge1").set("DependentVariableQuantity", "displacement");
    model.component("comp1").physics("mbd").feature("ge1").set("SourceTermQuantity", "force");

    model.study("std1").feature("time").set("tlist", "range(0,0.02,2*pi)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u4f4d\u79fb (mbd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u901f\u5ea6 (mbd)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u57df\u7f16\u53f7");
    model.result("pg2").feature("surf1").set("descractive", true);
    model.result("pg2").feature("surf1").set("expr", "mod(dom,10)");
    model.result("pg2").feature("surf1").set("descr", "\u57df\u7f16\u53f7");
    model.result("pg2").feature("surf1").set("rangecoloractive", "on");
    model.result("pg2").feature("surf1").set("rangecolormin", -0.5);
    model.result("pg2").feature("surf1").set("rangecolormax", 9.5);
    model.result("pg2").feature("surf1").set("colortable", "Cyclic");
    model.result("pg2").feature("surf1").set("colorlegend", false);
    model.result("pg2").feature("surf1").set("colortabletype", "discrete");
    model.result("pg2").feature("surf1").set("smooth", "none");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("def1", "Deform");
    model.result("pg2").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature().create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").label("\u7ebf\u4e0a\u7bad\u5934");
    model.result("pg2").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY"});
    model.result("pg2").feature("arwl1").set("placement", "elements");
    model.result("pg2").feature("arwl1").set("data", "parent");
    model.result("pg2").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg2").feature("arwl1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("arwl1").feature("def1").set("scaleactive", true);
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u76f8\u5bf9\u4f4d\u79fb");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"mbd.prj1.u"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u76f8\u5bf9\u4f4d\u79fb"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"m"});
    model.result("pg3").feature("glob1").setIndex("descr", "\u4f4d\u79fb\u8ba1\u7b97\u503c", 0);
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").run();
    model.result("pg3").create("glob2", "Global");
    model.result("pg3").feature("glob2").set("markerpos", "datapoints");
    model.result("pg3").feature("glob2").set("linewidth", "preference");
    model.result("pg3").feature("glob2").set("expr", new String[]{"ua"});
    model.result("pg3").feature("glob2").set("descr", new String[]{"\u4f4d\u79fb\u89e3\u6790\u503c"});
    model.result("pg3").feature("glob2").set("unit", new String[]{"m"});
    model.result("pg3").feature("glob2").set("linemarker", "asterisk");
    model.result("pg3").feature("glob2").set("markerpos", "interp");
    model.result("pg3").feature("glob2").set("markers", 20);
    model.result("pg3").feature("glob2").set("linestyle", "none");
    model.result("pg3").run();
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u6ed1\u5757\u5f84\u5411\u4f4d\u79fb\u6bd4\u8f83");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u76f8\u5bf9\u4f4d\u79fb [m]");
    model.result("pg3").set("legendpos", "lowerright");
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u76f8\u5bf9\u901f\u5ea6");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").setIndex("expr", "mbd.prj1.u_t", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u901f\u5ea6\u8ba1\u7b97\u503c", 0);
    model.result("pg4").run();
    model.result("pg4").feature("glob2").setIndex("expr", "uat", 0);
    model.result("pg4").feature("glob2").setIndex("unit", "", 0);
    model.result("pg4").feature("glob2").setIndex("descr", "\u901f\u5ea6\u89e3\u6790\u503c", 0);
    model.result("pg4").run();
    model.result("pg4").set("title", "\u6ed1\u5757\u5f84\u5411\u901f\u5ea6\u6bd4\u8f83");
    model.result("pg4").set("ylabel", "\u76f8\u5bf9\u901f\u5ea6 [m/s]");
    model.result("pg4").set("legendpos", "upperright");
    model.result("pg4").run();
    model.result().create("pg5", "PolarGroup");
    model.result("pg5").run();
    model.result("pg5").label("\u6ed1\u5757\u5f84\u5411\u4f4d\u7f6e");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "mbd.prj1.u+p", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u6ed1\u5757\u5f53\u524d\u4f4d\u7f6e", 0);
    model.result("pg5").feature("glob1").set("linewidth", 2);
    model.result("pg5").run();
    model.result("pg5").create("glob2", "Global");
    model.result("pg5").feature("glob2").set("markerpos", "datapoints");
    model.result("pg5").feature("glob2").set("linewidth", "preference");
    model.result("pg5").feature("glob2").setIndex("expr", "p", 0);
    model.result("pg5").feature("glob2").set("linewidth", 2);
    model.result("pg5").feature("glob2").set("linemarker", "asterisk");
    model.result("pg5").feature("glob2").set("markerpos", "interp");
    model.result("pg5").feature("glob2").set("markers", 20);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("rmin", 0);
    model.result("pg5").run();
    model.result().create("pg6", "PolarGroup");
    model.result("pg6").run();
    model.result("pg6").label("\u5f84\u5411\u901f\u5ea6");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "mbd.prj1.u_t", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "\u5f84\u5411\u901f\u5ea6", 0);
    model.result("pg6").feature("glob1").set("linewidth", 2);
    model.result("pg6").feature("glob1").set("legend", false);
    model.result("pg6").run();
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u5f84\u5411\u901f\u5ea6");
    model.result("pg6").run();
    model.result().create("pg7", "PolarGroup");
    model.result("pg7").run();
    model.result("pg7").label("\u5f84\u5411\u52a8\u80fd");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "0.5*m*mbd.prj1.u_t^2", 0);
    model.result("pg7").feature("glob1").setIndex("unit", "", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "\u5f84\u5411\u52a8\u80fd", 0);
    model.result("pg7").feature("glob1").set("linewidth", 2);
    model.result("pg7").feature("glob1").set("legend", false);
    model.result("pg7").run();
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "\u5f84\u5411\u52a8\u80fd");
    model.result("pg7").run();
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
    model.result().export("anim1").set("maxframes", 100);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 51, 0);
    model.result("pg1").run();

    model.title("\u8d1f\u8f7d\u5f39\u7c27\u65cb\u8f6c\u6ed1\u5757\u7684\u52a8\u6001\u7279\u6027");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u7531\u57fa\u5ea7\u65cb\u8f6c\u5f15\u8d77\u7684\u6ed1\u5757\u8fd0\u52a8\u7684\u5efa\u6a21\u8fc7\u7a0b\u3002\u7528\u4e8e\u8fde\u63a5\u4e24\u4e2a\u90e8\u4ef6\u7684\u68f1\u67f1\u5173\u8282\u4e3a\u8d1f\u8f7d\u5f39\u7c27\uff0c\u5e76\u5305\u542b\u963b\u5c3c\u6548\u5e94\u3002\u672c\u4f8b\u5c06\u6ed1\u5757\u7684\u8fd0\u52a8\u4e0e\u89e3\u6790\u89e3\u8fdb\u884c\u6bd4\u8f83\uff0c\u540e\u8005\u4e5f\u662f\u901a\u8fc7\u5e38\u5fae\u5206\u65b9\u7a0b\u540c\u65f6\u8ba1\u7b97\u5f97\u51fa\u7684\u3002");

    model.label("spring_loaded_slider.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
