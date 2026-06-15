/*
 * submarine_cable_08_d_inductive_effects_3d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:24 by COMSOL 6.3.0.290. */
public class submarine_cable_08_d_inductive_effects_3d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Tutorials,_Cables");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.param().label("\u51e0\u4f55\u53c2\u6570 1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Dcon", "26.2[mm]", "\u4e3b\u5bfc\u4f53\u76f4\u5f84\uff08\u76f8\uff09");
    model.param().set("Tins", "24.0[mm]", "\u7edd\u7f18\u539a\u5ea6 (XLPE)");
    model.param().set("Dins", "77.6[mm]", "\u7edd\u7f18\u76f4\u5f84\uff08XLPE \u548c SCC\uff09");
    model.param().set("Tscc", "(Dins/2-Dcon/2-Tins)/2", "\u534a\u5bfc\u4f53\u5316\u5408\u7269\u539a\u5ea6");
    model.param().set("Tpbs", "2.9[mm]", "\u94c5\u5305\u539a\u5ea6");
    model.param().set("Tpe", "2.9[mm]", "\u805a\u4e59\u70ef\u62a4\u5957\u539a\u5ea6");
    model.param()
         .set("Dpha", "Dins+2*Tpbs+2*Tpe", "\u76f8\u4f4d\u76f4\u5f84\uff08\u5305\u542b\u62a4\u5957\u548c PE\uff09");
    model.param().set("Dpha3", "Dpha*(2/sqrt(3)+1)", "\u4e09\u76f8\u7ed3\u5408\u7684\u76f4\u5f84");
    model.param().set("Dfic", "2.5[mm]", "\u5149\u7ea4\u7ea4\u82af\u76f4\u5f84");
    model.param().set("Tfih", "0.5[mm]", "\u94a2\u87ba\u65cb\u7ebf\u539a\u5ea6\uff08\u5149\u7ea4\uff09");
    model.param().set("Dfib", "9.2[mm]", "\u5149\u7f06\u76f4\u5f84");
    model.param().set("Dcab", "219.0[mm]", "\u6d77\u5e95\u7535\u7f06\u5916\u5f84");
    model.param().set("Darm", "(Dcab+Dpha3)/2", "\u94e0\u88c5\u73af\u4e2d\u5fc3\u76f4\u5f84");
    model.param().set("Tarm", "5.6[mm]", "\u94e0\u88c5\u539a\u5ea6\uff08\u7ebf\u5f84\uff09");
    model.param().set("Narm", "110", "\u73af\u4e2d\u7684\u94e0\u88c5\u7ebf\u6570");
    model.param().set("mfil", "0.5[mm]", "\u586b\u6599\u4f59\u91cf");
    model.param().set("marm", "pi*Darm/Narm-Tarm", "\u94e0\u88c5\u4f59\u91cf");
    model.param().create("par2");
    model.param("par2").label("\u51e0\u4f55\u53c2\u6570 2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2")
         .set("Acon", "500[mm^2]", "\u4e3b\u5bfc\u4f53\u7684\u6a2a\u622a\u9762\u79ef\uff08\u6bcf\u76f8\uff09");
    model.param("par2")
         .set("Ncon", "Acon/(pi*(Dcon/2)^2)", "\u5bfc\u4f53\u5806\u79ef\u5bc6\u5ea6\uff08\u76f8\uff09");
    model.param("par2")
         .set("Apbs", "pi*(Dins+Tpbs)*Tpbs", "\u94c5\u5305\u7684\u6a2a\u622a\u9762\u79ef\uff08\u6bcf\u76f8\uff09");
    model.param("par2").set("Lsec1", "1/3", "\u4ea4\u53c9\u4e92\u8054\u6bb5\u7684\u76f8\u5bf9\u957f\u5ea6 1");
    model.param("par2")
         .set("Lsec2", "1-Lsec1-Lsec3", "\u4ea4\u53c9\u4e92\u8054\u6bb5\u7684\u76f8\u5bf9\u957f\u5ea6 2");
    model.param("par2").set("Lsec3", "1/3", "\u4ea4\u53c9\u4e92\u8054\u6bb5\u7684\u76f8\u5bf9\u957f\u5ea6 3");
    model.param("par2").set("Lcab", "10[km]", "\u6d77\u5e95\u7535\u7f06\u603b\u957f");
    model.param("par2")
         .set("Scab", "1e5", "\u51e0\u4f55\u6bd4\u4f8b\u56e0\u5b50\uff08\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u6a21\u578b\uff09");
    model.param().create("par3");
    model.param("par3").label("\u51e0\u4f55\u53c2\u6570 3");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3")
         .set("LLpha", "18*Dpha3", "\u4e3b\u5bfc\u4f53\u7684\u7ede\u8ddd\uff08\u4e00\u4e2a\u626d\u8f6c\u5468\u671f\uff0c\u987a\u65f6\u9488\uff09");
    model.param("par3")
         .set("LLarm", "-15*Darm", "\u94e0\u88c5\u7ebf\u7684\u7ede\u8ddd\uff08\u4e00\u4e2a\u626d\u8f6c\u5468\u671f\uff0c\u9006\u65f6\u9488\uff09");
    model.param("par3")
         .set("CPcab", "1/(1/LLpha-1/LLarm)", "\u7535\u7f06\u4ea4\u53c9\u8282\u8ddd\uff08\u4e00\u4e2a\u626d\u8f6c\u5468\u671f\uff0c\u76f8\u5bf9\u4e8e\u94e0\u88c5\u7684\u76f8\uff09");
    model.param("par3")
         .set("Nper", "1/10", "\u6a21\u578b\u4e2d\u5305\u542b\u7684\u5468\u671f\u6570\uff08\u8c03\u6574\u53c2\u6570\uff09");
    model.param("par3")
         .set("Tenab", "round(Nper)==Nper", "\u542f\u7528\u6216\u7981\u7528\u626d\u8f6c\uff1a1 \u6216 0\uff08\u5f53 Nper \u4e3a\u6574\u6570\u65f6\u542f\u7528\uff09");
    model.param("par3")
         .set("Lsec", "CPcab*Nper", "\u6a21\u578b\u4e2d\u5305\u542b\u7684\u7535\u7f06\u6bb5\u957f\u5ea6");
    model.param("par3")
         .set("Tsec", "360[deg]*Tenab*Lsec/LLpha", "\u6a21\u578b\u4e2d\u5305\u542b\u7684\u7535\u7f06\u6bb5\u626d\u8f6c\u89d2\u5ea6");
    model.param("par3")
         .set("SCFpha", "if(Tenab,sqrt((2*pi*Dpha/sqrt(3))^2+LLpha^2)/abs(LLpha),1)", "\u503e\u659c\u6821\u6b63\u56e0\u5b50\uff0c\u4e3b\u5bfc\u4f53\uff08\u5f53 Nper \u4e3a\u6574\u6570\u65f6\u542f\u7528\uff09");
    model.param("par3")
         .set("SCFarm", "if(Tenab,sqrt((2*pi*Darm/2)^2+LLarm^2)/abs(LLarm),1)", "\u503e\u659c\u6821\u6b63\u56e0\u5b50\uff0c\u94e0\u88c5\u7ebf\uff08\u5f53 Nper \u4e3a\u6574\u6570\u65f6\u542f\u7528\uff09");
    model.param("par3")
         .set("TCFp20", "sqrt(pi/(5*(sqrt(5)-1)/2))", "\u622a\u65ad\u6821\u6b63\u56e0\u5b50\uff0c\u4e8c\u5341\u8fb9\u5f62\uff08\u76f8\uff09");
    model.param("par3")
         .set("TCFp16", "sqrt(pi/(4*sqrt(2-sqrt(2))))", "\u622a\u65ad\u6821\u6b63\u56e0\u5b50\uff0c\u5341\u516d\u8fb9\u5f62\uff08\u94e0\u88c5\uff09");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "Darm/2+2*Tarm/3");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "Lsec");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "0", "-Lsec/2"});
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("layer", "4*Tarm/3", 0);
    model.component("comp1").geom("geom1").run("cyl1");

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("transparency", true);
    model.component("comp1").view("view1").label("\u89c6\u56fe 1\uff08\u6b63\u4ea4\uff09");
    model.component("comp1").view("view1").set("locked", true);
    model.component("comp1").view("view1").set("showmaterial", true);
    model.component("comp1").view("view1").camera().set("viewscaletype", "manual");
    model.component("comp1").view("view1").camera().set("zscale", "1/(10*Nper)");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"Darm/2", "Darm/2", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "Darm/2", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "0", "Lsec/2-Darm/2"});
    model.component("comp1").geom("geom1").run("blk1");

    model.component("comp1").view().duplicate("view2", "view1");
    model.component("comp1").view("view2").label("\u89c6\u56fe 2\uff08\u6b63\u4ea4\uff0c\u4fef\u89c6\u56fe\uff09");
    model.component("comp1").view("view2").set("locked", true);
    model.component("comp1").view().duplicate("view3", "view2");
    model.component("comp1").view("view3").label("\u89c6\u56fe 3\uff08\u6b63\u4ea4\uff0c\u4ef0\u89c6\u56fe\uff09");
    model.component("comp1").view("view3").set("locked", true);
    model.component("comp1").view().duplicate("view4", "view3");
    model.component("comp1").view("view4").label("\u89c6\u56fe 4\uff08\u6b63\u4ea4\uff0c\u4fa7\u89c6\u56fe\uff09");
    model.component("comp1").view("view4").set("showgrid", true);
    model.component("comp1").view("view4").set("locked", false);
    model.component("comp1").view("view4").set("showgrid", false);
    model.component("comp1").view("view4").set("locked", true);
    model.component("comp1").view().duplicate("view5", "view4");
    model.component("comp1").view("view5").label("\u89c6\u56fe 5\uff08\u900f\u89c6\u56fe\uff09");
    model.component("comp1").view("view5").camera().set("projection", "perspective");
    model.component("comp1").view("view5").camera().set("zoomanglefull", 36);
    model.component("comp1").view("view5").camera().setIndex("position", "1.4*Dcab", 0);
    model.component("comp1").view("view5").camera().set("position", new String[]{"1.4*Dcab", "0.4*Dcab", "3"});
    model.component("comp1").view("view5").camera().setIndex("position", "Lsec/(2*Nper)+2.8*Dcab", 2);
    model.component("comp1").view("view5").camera().setIndex("up", "1e-6", 0);
    model.component("comp1").view("view5").camera().setIndex("up", 0, 0);
    model.component("comp1").view("view5").camera().set("up", new int[]{0, 1, 0});
    model.component("comp1").view("view5").camera().set("zscale", "1/Nper");
    model.component("comp1").view("view5").camera().set("viewoffset", new String[]{"0.8*Dcab", "0.8*Dcab"});

    model.param("par3").set("Nper", "1");

    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("locked", false);
    model.component("comp1").view("view2").set("locked", false);
    model.component("comp1").view("view3").set("locked", false);
    model.component("comp1").view("view4").set("locked", false);
    model.component("comp1").view("view5").set("locked", false);
    model.component("comp1").view("view1").camera().set("projection", "orthographic");
    model.component("comp1").view("view1").camera().set("orthoscale", 0.5474655628204346);
    model.component("comp1").view("view2").camera().set("projection", "orthographic");
    model.component("comp1").view("view2").camera().set("orthoscale", 0.3977237939834595);
    model.component("comp1").view("view3").camera().set("projection", "orthographic");
    model.component("comp1").view("view3").camera().set("orthoscale", 0.3977237939834595);
    model.component("comp1").view("view4").camera().set("projection", "orthographic");
    model.component("comp1").view("view4").camera().set("orthoscale", 0.3555237948894501);

    model.title("\u6d77\u5e95\u7535\u7f06 7a - \u51e0\u4f55\u548c\u7f51\u683c\u4e09\u7ef4\u6a21\u578b");

    model
         .description("\u8fd9\u4e2a MPH \u6587\u4ef6\u8868\u793a\u5728\u201c\u51e0\u4f55\u548c\u7f51\u683c\u4e09\u7ef4\u6a21\u578b\u201d\u6559\u7a0b\u4e2d\u6784\u5efa\u548c\u5206\u6790\u7684\u6a21\u578b\u7684\u4e2d\u95f4\u72b6\u6001\u3002\u6709\u5173\u66f4\u591a\u4fe1\u606f\uff0c\u5305\u62ec\u8be6\u7ec6\u7684\u4ecb\u7ecd\u3001\u5206\u6b65\u64cd\u4f5c\u8bf4\u660e\u548c\u7ed3\u679c\u5206\u6790\uff08\u5305\u542b\u9a8c\u8bc1\u4ee5\u53ca\u5bf9\u5b66\u672f\u7814\u7a76\u7684\u5f15\u7528\uff09\uff0c\u8bf7\u53c2\u9605\u201c\u6848\u4f8b\u5e93\u201d\u4e2d\u672c\u6559\u7a0b\u7684\u4e3b\u5165\u53e3\u70b9\u9644\u5e26\u7684 *.pdf \u6587\u4ef6\uff1asubmarine_cable_07_geom_mesh_3d\u3002");

    model.label("submarine_cable_07_a_geom_mesh_3d.mph");

    model.component("comp1").view("view1").camera().set("projection", "orthographic");
    model.component("comp1").view("view1").camera().set("orthoscale", 0.4105992019176483);
    model.component("comp1").view("view2").camera().set("projection", "orthographic");
    model.component("comp1").view("view2").camera().set("orthoscale", 0.2982928454875946);
    model.component("comp1").view("view3").camera().set("projection", "orthographic");
    model.component("comp1").view("view3").camera().set("orthoscale", 0.2982928454875946);
    model.component("comp1").view("view4").camera().set("projection", "orthographic");
    model.component("comp1").view("view4").camera().set("orthoscale", 0.26664286851882935);
    model.component("comp1").view("view1").set("locked", true);
    model.component("comp1").view("view2").set("locked", true);
    model.component("comp1").view("view3").set("locked", true);
    model.component("comp1").view("view4").set("locked", true);
    model.component("comp1").view("view5").set("locked", true);

    model.param("par3").set("Nper", "1/10");
    model.param().create("par4");
    model.param("par4").label("\u7535\u78c1\u53c2\u6570");

//    To import content from file, use:
//    model.param("par4").loadFile("FILENAME");
    model.param("par4").set("f0", "50[Hz]", "\u5de5\u4f5c\u9891\u7387");
    model.param("par4").set("w0", "(2*pi*f0[1/Hz])[rad/s]", "\u89d2\u9891\u7387");
    model.param("par4").set("V0", "220[kV]/sqrt(3)", "\u76f8\u5bf9\u5730\u7535\u538b\uff08\u5e45\u503c\uff09");
    model.param("par4").set("I0", "655[A]*sqrt(2)", "\u989d\u5b9a\u7535\u6d41\uff08\u5e45\u503c\uff09");
    model.param("par4").set("Scup", "5.96e7[S/m]", "\u94dc\u7535\u5bfc\u7387\uff0c20\u00b0C");
    model.param("par4").set("Spbs", "4.55e6[S/m]", "\u94c5\u5305\u7535\u5bfc\u7387\uff0c20\u00b0C");
    model.param("par4").set("Sarm", "4.03e6[S/m]", "\u94e0\u88c5\u7ebf\u7535\u5bfc\u7387\uff0c20\u00b0C");
    model.param("par4").set("Mcup", "1", "\u76f8\u5bf9\u78c1\u5bfc\u7387\uff0c\u94dc");
    model.param("par4").set("Mpbs", "1", "\u76f8\u5bf9\u78c1\u5bfc\u7387\uff0c\u94c5\u5305");
    model.param("par4").set("Marm", "100-50*j", "\u76f8\u5bf9\u78c1\u5bfc\u7387\uff0c\u94e0\u88c5\u7ebf");
    model.param("par4")
         .set("Dscup", "min(1/real(sqrt(j*w0*mu0_const*Mcup*Scup)),Dcon/3)", "\u96c6\u80a4\u6df1\u5ea6\uff0c\u94dc\uff08\u89e3\u6790\uff09");
    model.param("par4")
         .set("Dspbs", "min(1/real(sqrt(j*w0*mu0_const*Mpbs*Spbs)),12*Tpbs)", "\u96c6\u80a4\u6df1\u5ea6\uff0c\u94c5\u5305\uff08\u89e3\u6790\uff09");
    model.param("par4")
         .set("Dsarm", "min(1/real(sqrt(j*w0*mu0_const*Marm*Sarm)),Tarm/2)", "\u96c6\u80a4\u6df1\u5ea6\uff0c\u94e0\u88c5\u7ebf\uff08\u89e3\u6790\uff09");
    model.param("par4")
         .set("Rcon", "1/Acon/Scup", "\u6bcf\u76f8\u4e3b\u5bfc\u4f53\u76f4\u6d41\u7535\u963b\uff0c20\u00b0C\uff08\u89e3\u6790\uff09");
    model.param("par4")
         .set("Rpbs", "1/Apbs/Spbs", "\u6bcf\u76f8\u94c5\u5305\u76f4\u6d41\u7535\u963b\uff0c20\u00b0C\uff08\u89e3\u6790\uff09");
    model.param("par4")
         .set("Exlpe", "2.5", "\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570 XLPE\uff08\u53d6\u81ea IEC 60287\uff09");
    model.param("par4")
         .set("Cpha", "2*pi*epsilon0_const*Exlpe/log((Dins/2-Tscc)/(Dcon/2+Tscc))", "\u6bcf\u76f8\u7535\u5bb9\uff08\u89e3\u6790\uff09");
    model.param("par4").set("Icpha", "w0*Cpha*V0", "\u6bcf\u76f8\u5145\u7535\u7535\u6d41\uff08\u89e3\u6790\uff09");

    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").feature().remove("blk1");
    model.component("comp1").geom("geom1").feature().remove("cyl1");
    model.component("comp1").geom("geom1").run("");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "Darm/2");
    model.component("comp1").geom("geom1").feature("pol1").set("y", 0);
    model.component("comp1").geom("geom1").feature("pol1").set("z", "-(Lsec/2+Tarm*{0,10})");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("pol1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "360[deg]*range(1/Narm,1/Narm,1)");
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").label("\u76f8\u548c\u5c4f\u853d\u5c42");
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "-Lsec/2");
    model.component("comp1").geom("geom1").feature("wp1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp1").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("x", "Dpha/sqrt(3)+cos(360[deg]*range(1/40,1/20,1-1/40))*TCFp20*Dcon/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("y", "sin(360[deg]*range(1/40,1/20,1-1/40))*TCFp20*Dcon/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "Dins/2+Tpbs");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1")
         .set("pos", new String[]{"Dpha/sqrt(3)", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").setIndex("layer", "Tpbs", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pt1")
         .setIndex("p", "Dpha/sqrt(3)+(Dins/2+Tpbs)/sqrt(2)", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pt1")
         .setIndex("p", "(Dins/2+Tpbs)/sqrt(2)", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pt1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sca1", "Scale");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sca1").selection("input")
         .set("c1", "pol1", "pt1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sca1").set("type", "anisotropic");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sca1")
         .set("anisotropic", new String[]{"1", "SCFpha"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sca1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sca1")
         .set("anisotropic", new String[]{"1", "2*SCFpha"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sca1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sca1")
         .set("anisotropic", new String[]{"1", "SCFpha"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sca1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").selection("input")
         .set("sca1(1)", "sca1(2)");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").set("rot", "360[deg]*{1/3,2/3,1}");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("rot1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").selection("vertex1")
         .set("rot1(3)", 6);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").selection("vertex2")
         .set("rot1(1)", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rot2", "Rotate");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot2").selection("input").set("ls1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot2").set("rot", "360[deg]*{1/3,2/3,1}");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("rot2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("csol1").selection("input")
         .set("rot1", "rot2", "sca1(3)");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("csol1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").label("\u7535\u7f06\u94e0\u88c5\u548c\u6d77\u5e8a");
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", "-Lsec/2");
    model.component("comp1").geom("geom1").feature("wp2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp2").set("selresultshow", false);

    model.component("comp1").view("view7").axis().set("xmin", 0.0868808776140213);
    model.component("comp1").view("view7").axis().set("xmax", 0.11871876567602158);
    model.component("comp1").view("view7").axis().set("ymin", -0.012240377254784107);
    model.component("comp1").view("view7").axis().set("ymax", 0.012240377254784107);
    model.component("comp1").view("view7").set("locked", true);

    model.component("comp1").geom("geom1").feature("wp2").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .set("x", "Darm/2+TCFp16*Tarm/6*{0,1}");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("y", 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("rot1").selection("input").set("pol1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("rot1")
         .set("rot", "360[deg]*range(7/32,1/4,1-1/32)");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("rot1")
         .set("pos", new String[]{"Darm/2", "0"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("rot1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol2").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol2")
         .set("x", "Darm/2+cos(360[deg]*range(1/32,1/16,1-1/32))*TCFp16*Tarm/2");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol2")
         .set("y", "sin(360[deg]*range(1/32,1/16,1-1/32))*TCFp16*Tarm/2");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("pol2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("sca1", "Scale");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("sca1").selection("input")
         .set("pol2", "rot1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("sca1").set("type", "anisotropic");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("sca1")
         .set("anisotropic", new String[]{"1", "SCFarm"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("sca1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("pol3", "Polygon");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol3").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol3").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol3").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol3")
         .set("x", "cos(360[deg]/Narm*{0,1/2,1})*(Darm/2-2*Tarm/3)");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol3")
         .set("y", "sin(360[deg]/Narm*{0,1/2,1})*(Darm/2-2*Tarm/3)");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("pol3");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature().duplicate("pol4", "pol3");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol4")
         .set("x", "cos(360[deg]/Narm*{0,1/2,1})*(Darm/2+2*Tarm/3)");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol4")
         .set("y", "sin(360[deg]/Narm*{0,1/2,1})*(Darm/2+2*Tarm/3)");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("pol4");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("rot2", "Rotate");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("rot2").selection("input")
         .set("pol3", "pol4", "sca1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("rot2")
         .set("rot", "360[deg]*range(1/Narm,1/Narm,1)");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("rot2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", "5*Dcab/2");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "-8*Dcab/2", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "-8*Dcab/2", 1);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "-Lsec/2", 2);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("pt2", "Point");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "8*Dcab/2", 0);
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "8*Dcab/2", 1);
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", "-Lsec/2", 2);
    model.component("comp1").geom("geom1").run("pt2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").label("\u7f51\u683c\u63a7\u5236\u5b9e\u4f53");
    model.component("comp1").geom("geom1").feature("wp3").set("quickz", "-Lsec/2");

    model.component("comp1").view("view8").axis().set("xmin", 0.03283639997243881);
    model.component("comp1").view("view8").axis().set("xmax", 0.07016289234161377);
    model.component("comp1").view("view8").axis().set("ymin", -0.014749272726476192);
    model.component("comp1").view("view8").axis().set("ymax", 0.014749272726476192);
    model.component("comp1").view("view8").set("locked", true);

    model.component("comp1").geom("geom1").feature("wp3").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1")
         .set("x", "Dpha/sqrt(3)+TCFp20*Dcon/2-Dscup/8*{0,1,2,3}");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol1").set("y", 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot1").selection("input").set("pol1");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot1").set("rot", "360[deg]*{-1/40,1/40}");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot1")
         .set("pos", new String[]{"Dpha/sqrt(3)", "0"});
    model.component("comp1").geom("geom1").feature("wp3").geom().run("rot1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("ls1").selection("vertex1")
         .set("rot1(1)", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("ls1").selection("vertex2")
         .set("rot1(2)", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("rot2", "Rotate");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot2").selection("input")
         .set("ls1", "rot1");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot2")
         .set("rot", "360[deg]*range(1/20,1/20,1)");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot2")
         .set("pos", new String[]{"Dpha/sqrt(3)", "0"});
    model.component("comp1").geom("geom1").feature("wp3").geom().run("rot2");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol2").set("type", "closed");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol2").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol2")
         .set("x", "Dpha/sqrt(3)+cos(360[deg]*range(1/20,1/20,1))*(TCFp20*Dcon/2-Dscup*9/16)");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol2")
         .set("y", "sin(360[deg]*range(1/20,1/20,1))*(TCFp20*Dcon/2-Dscup*9/16)");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("pol2");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("sca1", "Scale");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("sca1").selection("input")
         .set("pol2", "rot2");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("sca1").set("type", "anisotropic");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("sca1")
         .set("anisotropic", new String[]{"1", "SCFpha"});
    model.component("comp1").geom("geom1").feature("wp3").geom().run("sca1");

    model.component("comp1").view("view8").axis().set("xmin", 0.09484034776687622);
    model.component("comp1").view("view8").axis().set("xmax", 0.11075928807258606);
    model.component("comp1").view("view8").axis().set("ymin", -0.006290244869887829);
    model.component("comp1").view("view8").axis().set("ymax", 0.006290244869887829);
    model.component("comp1").view("view8").set("locked", true);

    model.component("comp1").geom("geom1").feature("wp3").geom().create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pt1")
         .setIndex("p", "Darm/2+TCFp16*Tarm/2", 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("pt1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("rot3", "Rotate");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot3").selection("input").set("pt1");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot3")
         .set("rot", "360[deg]*{5/32,11/32,21/32,27/32}");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot3")
         .set("pos", new String[]{"Darm/2", "0"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot3").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot3").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("rot3");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("sca2", "Scale");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("sca2").selection("input").named("rot3");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("sca2").set("type", "anisotropic");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("sca2")
         .set("anisotropic", new String[]{"1", "SCFarm"});
    model.component("comp1").geom("geom1").feature("wp3").geom().run("sca2");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("rot4", "Rotate");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot4").selection("input")
         .set("sca2(3)", "sca2(4)");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot4").set("rot", "360[deg]/Narm");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("rot4");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("ls2").selection("vertex1")
         .set("sca2(2)", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("ls2").selection("vertex2")
         .set("rot4(1)", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("ls2");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("ls3", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("ls3").selection("vertex1")
         .set("sca2(1)", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("ls3").selection("vertex2")
         .set("rot4(2)", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("ls3");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("rot5", "Rotate");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot5").selection("input")
         .set("ls2", "ls3", "rot4", "sca2(1)", "sca2(2)");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot5")
         .set("rot", "360[deg]*range(1/Narm,1/Narm,1)");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("rot5");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("pol3", "Polygon");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol3").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol3").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol3").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol3")
         .set("x", "Darm/2+TCFp16*Tarm/2-{0,Dsarm/5,2*Dsarm/5,TCFp16*Tarm/3,TCFp16*Tarm/2}");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("pol3").set("y", 0);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("pol3");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("rot6", "Rotate");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot6").selection("input").set("pol3");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot6")
         .set("rot", "360[deg]*{-1/32,1/32,3/32}");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot6")
         .set("pos", new String[]{"Darm/2", "0"});
    model.component("comp1").geom("geom1").feature("wp3").geom().run("rot6");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("del1").selection("input")
         .set("rot6(2)", 1, 2);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("del1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("ls4", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("ls4").selection("vertex1")
         .set("rot6(1)", 3);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("ls4").selection("vertex2").set("del1", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("ls4");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("ls5", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("ls5").selection("vertex1").set("ls4", 2);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("ls5").selection("vertex2")
         .set("rot6(3)", 3);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("ls5");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("rot7", "Rotate");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot7").selection("input")
         .set("del1", "ls4", "ls5", "rot6(1)", "rot6(3)");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot7")
         .set("rot", "360[deg]*range(1/8,1/8,1)");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot7")
         .set("pos", new String[]{"Darm/2", "0"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot7").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("rot7").set("selresultshow", false);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("rot7");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("sca3", "Scale");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("sca3").selection("input").named("rot7");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("sca3").set("type", "anisotropic");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("sca3")
         .set("anisotropic", new String[]{"1", "SCFarm"});
    model.component("comp1").geom("geom1").feature("wp3").geom().run("sca3");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().remove("pt2");
    model.component("comp1").geom("geom1").feature().remove("pt1");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol2").set("x", 0);
    model.component("comp1").geom("geom1").feature("pol2").set("y", 0);
    model.component("comp1").geom("geom1").feature("pol2").set("z", "Lsec*{-1/2,1/2}");
    model.component("comp1").geom("geom1").feature("pol2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("pol2").set("selresultshow", false);
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").create("swe1", "Sweep");
    model.component("comp1").geom("geom1").feature("swe1").set("includefinal", false);
    model.component("comp1").geom("geom1").feature("swe1").set("crossfaces", true);
    model.component("comp1").geom("geom1").feature("swe1").set("manualdir", false);
    model.component("comp1").geom("geom1").feature("swe1").selection("enttosweep").named("wp1");
    model.component("comp1").geom("geom1").feature("swe1").selection("edge").named("pol2");
    model.component("comp1").geom("geom1").feature("swe1").set("keep", false);
    model.component("comp1").geom("geom1").feature("swe1").set("twist", "360[deg]*Tenab*s[m]/LLpha");
    model.component("comp1").geom("geom1").run("swe1");
    model.component("comp1").geom("geom1").feature().duplicate("pol3", "pol2");
    model.component("comp1").geom("geom1").run("pol3");
    model.component("comp1").geom("geom1").feature().duplicate("swe2", "swe1");
    model.component("comp1").geom("geom1").feature("swe2").selection("enttosweep").named("wp2");
    model.component("comp1").geom("geom1").feature("swe2").selection("edge").named("pol3");
    model.component("comp1").geom("geom1").feature("swe2").set("twist", "360[deg]*Tenab*s[m]/LLarm");
    model.component("comp1").geom("geom1").run("swe2");

    model.param("par3").set("Nper", "1");

    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").run("swe1");
    model.component("comp1").geom("geom1").run("swe2");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("locked", false);
    model.component("comp1").view("view2").set("locked", false);
    model.component("comp1").view("view3").set("locked", false);
    model.component("comp1").view("view4").set("locked", false);
    model.component("comp1").view("view5").set("locked", false);
    model.component("comp1").view("view1").camera().set("projection", "orthographic");
    model.component("comp1").view("view1").camera().set("orthoscale", 0.5474655628204346);
    model.component("comp1").view("view2").camera().set("projection", "orthographic");
    model.component("comp1").view("view2").camera().set("orthoscale", 0.3977237939834595);
    model.component("comp1").view("view3").camera().set("projection", "orthographic");
    model.component("comp1").view("view3").camera().set("orthoscale", 0.3977237939834595);
    model.component("comp1").view("view4").camera().set("projection", "orthographic");
    model.component("comp1").view("view4").camera().set("orthoscale", 0.3555237948894501);
    model.component("comp1").view("view6").set("locked", false);
    model.component("comp1").view("view6").axis().set("xmin", -0.1905168890953064);
    model.component("comp1").view("view6").axis().set("xmax", 0.1905168890953064);
    model.component("comp1").view("view6").axis().set("ymin", -0.15056250989437103);
    model.component("comp1").view("view6").axis().set("ymax", 0.15056250989437103);
    model.component("comp1").view("view7").set("locked", false);
    model.component("comp1").view("view7").axis().set("xmin", -0.1905168890953064);
    model.component("comp1").view("view7").axis().set("xmax", 0.1905168890953064);
    model.component("comp1").view("view7").axis().set("ymin", -0.15056250989437103);
    model.component("comp1").view("view7").axis().set("ymax", 0.15056250989437103);
    model.component("comp1").view("view8").set("locked", false);
    model.component("comp1").view("view8").axis().set("xmin", -0.1905168890953064);
    model.component("comp1").view("view8").axis().set("xmax", 0.1905168890953064);
    model.component("comp1").view("view8").axis().set("ymin", -0.15056250989437103);
    model.component("comp1").view("view8").axis().set("ymax", 0.15056250989437103);

    model.title("\u6d77\u5e95\u7535\u7f06 7b - \u51e0\u4f55\u548c\u7f51\u683c\u4e09\u7ef4\u6a21\u578b");

    model
         .description("\u8fd9\u4e2a MPH \u6587\u4ef6\u8868\u793a\u5728\u201c\u51e0\u4f55\u548c\u7f51\u683c\u4e09\u7ef4\u6a21\u578b\u201d\u6559\u7a0b\u4e2d\u6784\u5efa\u548c\u5206\u6790\u7684\u6a21\u578b\u7684\u4e2d\u95f4\u72b6\u6001\u3002\u6709\u5173\u66f4\u591a\u4fe1\u606f\uff0c\u5305\u62ec\u8be6\u7ec6\u7684\u4ecb\u7ecd\u3001\u5206\u6b65\u64cd\u4f5c\u8bf4\u660e\u548c\u7ed3\u679c\u5206\u6790\uff08\u5305\u542b\u9a8c\u8bc1\u4ee5\u53ca\u5bf9\u5b66\u672f\u7814\u7a76\u7684\u5f15\u7528\uff09\uff0c\u8bf7\u53c2\u9605\u201c\u6848\u4f8b\u5e93\u201d\u4e2d\u672c\u6559\u7a0b\u7684\u4e3b\u5165\u53e3\u70b9\u9644\u5e26\u7684 *.pdf \u6587\u4ef6\uff1asubmarine_cable_07_geom_mesh_3d\u3002");

    model.label("submarine_cable_07_b_geom_mesh_3d.mph");

    model.component("comp1").view("view2").camera().set("projection", "perspective");
    model.component("comp1").view("view2").camera().set("zoomanglefull", 10);
    model.component("comp1").view("view1").camera().set("projection", "orthographic");
    model.component("comp1").view("view1").camera().set("orthoscale", 0.4105992019176483);
    model.component("comp1").view("view2").camera().set("projection", "orthographic");
    model.component("comp1").view("view2").camera().set("orthoscale", 0.2982928454875946);
    model.component("comp1").view("view3").camera().set("projection", "orthographic");
    model.component("comp1").view("view3").camera().set("orthoscale", 0.2982928454875946);
    model.component("comp1").view("view4").camera().set("projection", "orthographic");
    model.component("comp1").view("view4").camera().set("orthoscale", 0.26664286851882935);
    model.component("comp1").view("view1").set("locked", true);
    model.component("comp1").view("view2").set("locked", true);
    model.component("comp1").view("view3").set("locked", true);
    model.component("comp1").view("view4").set("locked", true);
    model.component("comp1").view("view5").set("locked", true);

    model.component("comp1").selection().create("cyl1", "Cylinder");
    model.component("comp1").selection("cyl1").label("\u6a2a\u622a\u9762\uff1a\u9876\u90e8");
    model.component("comp1").selection("cyl1").set("entitydim", 2);
    model.component("comp1").selection("cyl1").set("r", "6*Dcab/2");
    model.component("comp1").selection("cyl1").set("top", "Lsec/4");
    model.component("comp1").selection("cyl1").set("bottom", "-Lsec/4");
    model.component("comp1").selection("cyl1").set("pos", new String[]{"0", "0", "Lsec/2"});
    model.component("comp1").selection("cyl1").set("condition", "allvertices");
    model.component("comp1").selection().duplicate("cyl2", "cyl1");
    model.component("comp1").selection("cyl2").label("\u6a2a\u622a\u9762\uff1a\u5e95\u90e8");
    model.component("comp1").selection("cyl2").set("pos", new String[]{"0", "0", "-Lsec/2"});
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u6a2a\u622a\u9762\uff0c\u9876\u90e8\u548c\u5e95\u90e8");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"cyl1", "cyl2"});
    model.component("comp1").selection().create("cyl3", "Cylinder");
    model.component("comp1").selection("cyl3").label("\u76f8");
    model.component("comp1").selection("cyl3").set("r", "Dpha/sqrt(3)+(Dcon/2+Dins/2)/2");
    model.component("comp1").selection("cyl3").set("rin", "Dpha/sqrt(3)-(Dcon/2+Dins/2)/2");
    model.component("comp1").selection("cyl3").set("condition", "allvertices");
    model.component("comp1").selection().create("ball1", "Ball");
    model.component("comp1").selection("ball1").label("\u76f8 1");
    model.component("comp1").selection("ball1").set("inputent", "selections");
    model.component("comp1").selection("ball1").set("input", new String[]{"cyl3"});
    model.component("comp1").selection("ball1").set("posx", "-(Dpha/2)/sqrt(3)");
    model.component("comp1").selection("ball1").set("posy", "Dpha/2");
    model.component("comp1").selection("ball1").set("posz", "-Lsec/2");
    model.component("comp1").selection("ball1").set("r", "(Dcon/2+Dins/2)/2");
    model.component("comp1").selection("ball1").set("condition", "somevertex");
    model.component("comp1").selection().duplicate("ball2", "ball1");
    model.component("comp1").selection("ball2").label("\u76f8 2");
    model.component("comp1").selection("ball2").set("posx", "Dpha/sqrt(3)");
    model.component("comp1").selection("ball2").set("posy", 0);
    model.component("comp1").selection().duplicate("ball3", "ball2");
    model.component("comp1").selection("ball3").label("\u76f8 3");
    model.component("comp1").selection("ball3").set("posx", "-(Dpha/2)/sqrt(3)");
    model.component("comp1").selection("ball3").set("posy", "-Dpha/2");
    model.component("comp1").selection().create("adj1", "Adjacent");
    model.component("comp1").selection("adj1").label("\u4ea4\u8054\u805a\u4e59\u70ef (XLPE)");
    model.component("comp1").selection("adj1").set("input", new String[]{"cyl3"});
    model.component("comp1").selection("adj1").set("outputdim", 3);
    model.component("comp1").selection().create("adj2", "Adjacent");
    model.component("comp1").selection("adj2").label("\u76f8\u548c\u5c4f\u853d\u5c42");
    model.component("comp1").selection("adj2").set("input", new String[]{"adj1"});
    model.component("comp1").selection("adj2").set("outputdim", 3);
    model.component("comp1").selection().create("dif1", "Difference");
    model.component("comp1").selection("dif1").label("\u5c4f\u853d\u5c42");
    model.component("comp1").selection("dif1").set("add", new String[]{"adj2"});
    model.component("comp1").selection("dif1").set("subtract", new String[]{"cyl3"});
    model.component("comp1").selection().create("cyl4", "Cylinder");
    model.component("comp1").selection("cyl4").label("\u7535\u7f06\u94e0\u88c5\uff0c\u4e2d\u5fc3\u7ebf");
    model.component("comp1").selection("cyl4").set("entitydim", 1);
    model.component("comp1").selection("cyl4").set("groupcontang", true);
    model.component("comp1").selection("cyl4").set("angletol", 60);
    model.component("comp1").selection("cyl4").set("r", "Darm/2+Tarm");
    model.component("comp1").selection("cyl4").set("top", "5*Tarm");
    model.component("comp1").selection("cyl4").set("bottom", "-5*Tarm");
    model.component("comp1").selection("cyl4").set("pos", new String[]{"0", "0", "-(Lsec/2+10*Tarm)"});
    model.component("comp1").selection("cyl4").set("condition", "somevertex");
    model.component("comp1").selection().create("adj3", "Adjacent");
    model.component("comp1").selection("adj3").label("\u7535\u7f06\u94e0\u88c5");
    model.component("comp1").selection("adj3").set("entitydim", 1);
    model.component("comp1").selection("adj3").set("input", new String[]{"cyl4"});
    model.component("comp1").selection("adj3").set("outputdim", 3);
    model.component("comp1").selection().create("uni2", "Union");
    model.component("comp1").selection("uni2").label("\u5bfc\u4f53");
    model.component("comp1").selection("uni2").set("input", new String[]{"adj2", "adj3"});
    model.component("comp1").selection().create("com1", "Complement");
    model.component("comp1").selection("com1").label("\u7edd\u7f18\u4f53");
    model.component("comp1").selection("com1").set("input", new String[]{"uni2"});
    model.component("comp1").selection().create("cyl5", "Cylinder");
    model.component("comp1").selection("cyl5").label("\u7535\u7f06\u57df");
    model.component("comp1").selection("cyl5").set("r", "Darm/2+Tarm");
    model.component("comp1").selection("cyl5").set("condition", "allvertices");
    model.component("comp1").selection().create("adj4", "Adjacent");
    model.component("comp1").selection("adj4").label("\u76f8\uff0c\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("adj4").set("input", new String[]{"cyl3"});

    model.nodeGroup().create("grp1", "Definitions", "comp1");
    model.nodeGroup("grp1").set("type", "selection");
    model.nodeGroup("grp1").placeAfter("selection", "cyl5");
    model.nodeGroup("grp1").add("selection", "adj4");
    model.nodeGroup("grp1").label("\u7f51\u683c\u9009\u62e9");

    model.component("comp1").selection().create("adj5", "Adjacent");

    model.nodeGroup("grp1").add("selection", "adj5");

    model.component("comp1").selection("adj5").label("\u5c4f\u853d\u5c42\uff0c\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("adj5").set("input", new String[]{"dif1"});
    model.component("comp1").selection().create("adj6", "Adjacent");

    model.nodeGroup("grp1").add("selection", "adj6");

    model.component("comp1").selection("adj6").label("\u94e0\u88c5\uff0c\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("adj6").set("input", new String[]{"adj3"});
    model.component("comp1").selection().create("int1", "Intersection");

    model.nodeGroup("grp1").add("selection", "int1");

    model.component("comp1").selection("int1").label("\u76f8\uff0c\u8fb9\u754c\u5e95\u90e8");
    model.component("comp1").selection("int1").set("entitydim", 2);
    model.component("comp1").selection("int1").set("input", new String[]{"cyl2", "adj4"});
    model.component("comp1").selection().create("int2", "Intersection");

    model.nodeGroup("grp1").add("selection", "int2");

    model.component("comp1").selection("int2")
         .label("\u5c4f\u853d\u5c42\uff0c\u8fb9\u754c\u5e95\u90e8\uff08\u6620\u5c04 3\uff09");
    model.component("comp1").selection("int2").set("entitydim", 2);
    model.component("comp1").selection("int2").set("input", new String[]{"cyl2", "adj5"});
    model.component("comp1").selection().create("int3", "Intersection");

    model.nodeGroup("grp1").add("selection", "int3");

    model.component("comp1").selection("int3").label("\u94e0\u88c5\uff0c\u8fb9\u754c\u5e95\u90e8");
    model.component("comp1").selection("int3").set("entitydim", 2);
    model.component("comp1").selection("int3").set("input", new String[]{"cyl2", "adj6"});
    model.component("comp1").selection().create("cyl6", "Cylinder");

    model.nodeGroup("grp1").add("selection", "cyl6");

    model.component("comp1").selection("cyl6").label("\u81ea\u7531\u4e09\u89d2\u5f62\u7f51\u683c 1");
    model.component("comp1").selection("cyl6").set("entitydim", 2);
    model.component("comp1").selection("cyl6").set("inputent", "selections");
    model.component("comp1").selection("cyl6").set("input", new String[]{"cyl2"});
    model.component("comp1").selection("cyl6").set("r", "Dcon/2-Dscup/8");
    model.component("comp1").selection("cyl6").set("pos", new String[]{"Dpha/sqrt(3)", "0", "0"});
    model.component("comp1").selection("cyl6").set("condition", "allvertices");
    model.component("comp1").selection().duplicate("cyl7", "cyl6");

    model.nodeGroup("grp1").add("selection", "cyl7");

    model.component("comp1").selection("cyl7").label("\u6620\u5c04 1");
    model.component("comp1").selection("cyl7").set("r", "Dcon/2+Dscup/8");
    model.component("comp1").selection("cyl7").set("rin", "Dcon/2-Dscup/2");
    model.component("comp1").selection().create("uni3", "Union");

    model.nodeGroup("grp1").add("selection", "uni3");

    model.component("comp1").selection("uni3").label("\u590d\u5236\u9762 1\uff0c\u6e90\u8fb9\u754c");
    model.component("comp1").selection("uni3").set("entitydim", 2);
    model.component("comp1").selection("uni3").set("input", new String[]{"cyl6", "cyl7"});
    model.component("comp1").selection().create("dif2", "Difference");

    model.nodeGroup("grp1").add("selection", "dif2");

    model.component("comp1").selection("dif2").label("\u590d\u5236\u9762 1\uff0c\u76ee\u6807\u8fb9\u754c");
    model.component("comp1").selection("dif2").set("entitydim", 2);
    model.component("comp1").selection("dif2").set("add", new String[]{"int1"});
    model.component("comp1").selection("dif2").set("subtract", new String[]{"uni3"});
    model.component("comp1").selection().duplicate("cyl8", "cyl6");

    model.nodeGroup("grp1").add("selection", "cyl8");

    model.component("comp1").selection("cyl8").label("\u81ea\u7531\u4e09\u89d2\u5f62\u7f51\u683c 2");
    model.component("comp1").selection("cyl8").set("r", "Tarm/2-Dsarm/5");
    model.component("comp1").selection("cyl8").set("pos", new String[]{"Darm/2", "0", "0"});
    model.component("comp1").selection().duplicate("cyl9", "cyl8");

    model.nodeGroup("grp1").add("selection", "cyl9");

    model.component("comp1").selection("cyl9").label("\u6620\u5c04 2");
    model.component("comp1").selection("cyl9").set("r", "Tarm/2+Dsarm/5");
    model.component("comp1").selection("cyl9").set("rin", "Tarm/2-Dsarm/2");
    model.component("comp1").selection().create("uni4", "Union");

    model.nodeGroup("grp1").add("selection", "uni4");

    model.component("comp1").selection("uni4").label("\u590d\u5236\u9762 2\uff0c\u6e90\u8fb9\u754c");
    model.component("comp1").selection("uni4").set("entitydim", 2);
    model.component("comp1").selection("uni4").set("input", new String[]{"cyl8", "cyl9"});
    model.component("comp1").selection().create("dif3", "Difference");

    model.nodeGroup("grp1").add("selection", "dif3");

    model.component("comp1").selection("dif3").label("\u590d\u5236\u9762 2\uff0c\u76ee\u6807\u8fb9\u754c");
    model.component("comp1").selection("dif3").set("entitydim", 2);
    model.component("comp1").selection("dif3").set("add", new String[]{"int3"});
    model.component("comp1").selection("dif3").set("subtract", new String[]{"uni4"});
    model.component("comp1").selection().create("dif4", "Difference");

    model.nodeGroup("grp1").add("selection", "dif4");

    model.component("comp1").selection("dif4").label("\u975e\u94e0\u88c5\uff0c\u8fb9\u754c\u5e95\u90e8");
    model.component("comp1").selection("dif4").set("entitydim", 2);
    model.component("comp1").selection("dif4").set("add", new String[]{"cyl2"});
    model.component("comp1").selection("dif4").set("subtract", new String[]{"int3"});
    model.component("comp1").selection().create("cyl10", "Cylinder");

    model.nodeGroup("grp1").add("selection", "cyl10");

    model.component("comp1").selection("cyl10").label("\u6620\u5c04 4");
    model.component("comp1").selection("cyl10").set("entitydim", 2);
    model.component("comp1").selection("cyl10").set("inputent", "selections");
    model.component("comp1").selection("cyl10").set("input", new String[]{"dif4"});
    model.component("comp1").selection("cyl10").set("r", "Darm/2+Tarm/2");
    model.component("comp1").selection("cyl10").set("rin", "Darm/2-Tarm/2");
    model.component("comp1").selection("cyl10").set("condition", "allvertices");
    model.component("comp1").selection("cyl10").set("angle2", 360);
    model.component("comp1").selection().create("dif5", "Difference");

    model.nodeGroup("grp1").add("selection", "dif5");

    model.component("comp1").selection("dif5").label("\u81ea\u7531\u4e09\u89d2\u5f62\u7f51\u683c 3");
    model.component("comp1").selection("dif5").set("entitydim", 2);
    model.component("comp1").selection("dif5").set("add", new String[]{"cyl2"});
    model.component("comp1").selection("dif5").set("subtract", new String[]{"int1", "int2", "int3", "cyl10"});
    model.component("comp1").selection().create("cyl11", "Cylinder");

    model.nodeGroup("grp1").add("selection", "cyl11");

    model.component("comp1").selection("cyl11")
         .label("\u81ea\u7531\u4e09\u89d2\u5f62\u7f51\u683c 3\uff0c\u5927\u5c0f 1");
    model.component("comp1").selection("cyl11").set("entitydim", 2);
    model.component("comp1").selection("cyl11").set("inputent", "selections");
    model.component("comp1").selection("cyl11").set("input", new String[]{"dif5"});
    model.component("comp1").selection("cyl11").set("r", "Darm/2+Tarm");
    model.component("comp1").selection("cyl11").set("rin", "Darm/2-Tarm");
    model.component("comp1").selection("cyl11").set("condition", "allvertices");
    model.component("comp1").selection().create("cyl12", "Cylinder");

    model.nodeGroup("grp1").add("selection", "cyl12");

    model.component("comp1").selection("cyl12").label("\u626b\u63a0 1\uff0c\u5206\u5e03 1");
    model.component("comp1").selection("cyl12").set("r", "Dpha3/2");
    model.component("comp1").selection("cyl12").set("condition", "allvertices");
    model.component("comp1").selection().duplicate("cyl13", "cyl12");

    model.nodeGroup("grp1").add("selection", "cyl13");

    model.component("comp1").selection("cyl13").label("\u626b\u63a0 1\uff0c\u5206\u5e03 2");
    model.component("comp1").selection("cyl13").set("r", "6*Dcab/2");
    model.component("comp1").selection("cyl13").set("rin", "Dpha3/2");
    model.component("comp1").selection().create("uni5", "Union");

    model.nodeGroup("grp1").add("selection", "uni5");

    model.component("comp1").selection("uni5").label("\u626b\u63a0 1");
    model.component("comp1").selection("uni5").set("input", new String[]{"cyl12", "cyl13"});
    model.component("comp1").selection("uni5").label("*.\u626b\u63a0 1");
    model.component("comp1").selection().create("com2", "Complement");

    model.nodeGroup("grp1").add("selection", "com2");

    model.component("comp1").selection("com2").label("d");
    model.component("comp1").selection("com2")
         .label("\u5176\u4f59\u7684\u57df\uff08\u81ea\u7531\u56db\u9762\u4f53\u7f51\u683c 1\uff09");
    model.component("comp1").selection("com2").set("input", new String[]{"uni5"});
    model.component("comp1").selection().create("adj7", "Adjacent");

    model.nodeGroup("grp1").add("selection", "adj7");

    model.component("comp1").selection("adj7").label("\u5176\u4f59\u7684\u57df\uff0c\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection("adj7").set("input", new String[]{"com2"});
    model.component("comp1").selection("adj7").label(".pdf\u5176\u4f59\u7684\u57df\uff0c\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").selection().create("dif6", "Difference");

    model.nodeGroup("grp1").add("selection", "dif6");

    model.component("comp1").selection("dif6").label("\u8f6c\u6362 1");
    model.component("comp1").selection("dif6").set("entitydim", 2);
    model.component("comp1").selection("dif6").set("add", new String[]{"adj7"});
    model.component("comp1").selection("dif6").set("subtract", new String[]{"uni1"});

    model.component("comp1").view("view2").set("renderwireframe", true);

    model.component("comp1").selection().create("int4", "Intersection");

    model.nodeGroup("grp1").add("selection", "int4");

    model.component("comp1").selection("int4").label("\u590d\u5236\u9762 3\uff0c\u6e90\u8fb9\u754c");
    model.component("comp1").selection("int4").set("entitydim", 2);
    model.component("comp1").selection("int4").set("input", new String[]{"cyl2", "adj7"});
    model.component("comp1").selection().create("int5", "Intersection");

    model.nodeGroup("grp1").add("selection", "int5");

    model.component("comp1").selection("int5").label("\u590d\u5236\u9762 3\uff0c\u76ee\u6807\u8fb9\u754c");
    model.component("comp1").selection("int5").set("entitydim", 2);
    model.component("comp1").selection("int5").set("input", new String[]{"cyl1", "adj7"});

    model.component("comp1").view("view2").set("renderwireframe", false);

    model.component("comp1").selection().create("cyl14", "Cylinder");
    model.component("comp1").selection("cyl14").label("\u7535\u7f06\u9762\uff0c\u9876\u90e8");
    model.component("comp1").selection("cyl14").set("entitydim", 2);
    model.component("comp1").selection("cyl14").set("inputent", "selections");
    model.component("comp1").selection("cyl14").set("input", new String[]{"cyl1"});
    model.component("comp1").selection("cyl14").set("r", "Darm/2+Tarm");
    model.component("comp1").selection("cyl14").set("condition", "allvertices");

    model.nodeGroup().create("grp2", "Definitions", "comp1");
    model.nodeGroup("grp2").set("type", "selection");
    model.nodeGroup("grp2").placeAfter("selection", "cyl5");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("selection", "cyl14");
    model.nodeGroup("grp2").label("\u540e\u5904\u7406\u9009\u62e9");

    model.component("comp1").selection().duplicate("cyl15", "cyl14");

    model.nodeGroup("grp2").add("selection", "cyl15");

    model.component("comp1").selection("cyl15").label("\u7535\u7f06\u73af\uff0c\u9876\u90e8");
    model.component("comp1").selection("cyl15").set("rin", "Darm/2-Tarm");
    model.component("comp1").selection().duplicate("cyl16", "cyl15");

    model.nodeGroup("grp2").add("selection", "cyl16");

    model.component("comp1").selection("cyl16").label("\u94e0\u88c5\u4e09\u76f8\u7ebf");
    model.component("comp1").selection("cyl16").set("angle1", "Tsec-2*360[deg]/Narm");
    model.component("comp1").selection("cyl16").set("angle2", "Tsec+2*360[deg]/Narm");

    model.component("comp1").view("view1").set("locked", false);
    model.component("comp1").view("view2").set("locked", false);
    model.component("comp1").view("view3").set("locked", false);
    model.component("comp1").view("view4").set("locked", false);
    model.component("comp1").view("view5").set("locked", false);
    model.component("comp1").view("view1").camera().set("projection", "orthographic");
    model.component("comp1").view("view1").camera().set("orthoscale", 0.5474655628204346);
    model.component("comp1").view("view2").camera().set("projection", "orthographic");

    return model;
  }

  public static Model run3(Model model) {
    model.component("comp1").view("view2").camera().set("orthoscale", 0.3977237939834595);
    model.component("comp1").view("view3").camera().set("projection", "orthographic");
    model.component("comp1").view("view3").camera().set("orthoscale", 0.3977237939834595);
    model.component("comp1").view("view4").camera().set("projection", "orthographic");
    model.component("comp1").view("view4").camera().set("orthoscale", 0.3555237948894501);

    model.title("\u6d77\u5e95\u7535\u7f06 7c - \u51e0\u4f55\u548c\u7f51\u683c\u4e09\u7ef4\u6a21\u578b");

    model
         .description("\u8fd9\u4e2a MPH \u6587\u4ef6\u8868\u793a\u5728\u201c\u51e0\u4f55\u548c\u7f51\u683c\u4e09\u7ef4\u6a21\u578b\u201d\u6559\u7a0b\u4e2d\u6784\u5efa\u548c\u5206\u6790\u7684\u6a21\u578b\u7684\u4e2d\u95f4\u72b6\u6001\u3002\u6709\u5173\u66f4\u591a\u4fe1\u606f\uff0c\u5305\u62ec\u8be6\u7ec6\u7684\u4ecb\u7ecd\u3001\u5206\u6b65\u64cd\u4f5c\u8bf4\u660e\u548c\u7ed3\u679c\u5206\u6790\uff08\u5305\u542b\u9a8c\u8bc1\u4ee5\u53ca\u5bf9\u5b66\u672f\u7814\u7a76\u7684\u5f15\u7528\uff09\uff0c\u8bf7\u53c2\u9605\u201c\u6848\u4f8b\u5e93\u201d\u4e2d\u672c\u6559\u7a0b\u7684\u4e3b\u5165\u53e3\u70b9\u9644\u5e26\u7684 *.pdf \u6587\u4ef6\uff1asubmarine_cable_07_geom_mesh_3d\u3002");

    model.label("submarine_cable_07_c_geom_mesh_3d.mph");

    model.component("comp1").view("view1").camera().set("projection", "orthographic");
    model.component("comp1").view("view1").camera().set("orthoscale", 0.4105992019176483);
    model.component("comp1").view("view2").camera().set("projection", "orthographic");
    model.component("comp1").view("view2").camera().set("orthoscale", 0.2982928454875946);
    model.component("comp1").view("view3").camera().set("projection", "orthographic");
    model.component("comp1").view("view3").camera().set("orthoscale", 0.2982928454875946);
    model.component("comp1").view("view4").camera().set("projection", "orthographic");
    model.component("comp1").view("view4").camera().set("orthoscale", 0.26664286851882935);
    model.component("comp1").view("view1").set("locked", true);
    model.component("comp1").view("view2").set("locked", true);
    model.component("comp1").view("view3").set("locked", true);
    model.component("comp1").view("view4").set("locked", true);
    model.component("comp1").view("view5").set("locked", true);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().named("cyl6");

    model.component("comp1").view("view3").set("transparency", false);

    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "Dcon/9");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmin", "Dcon/9");
    model.component("comp1").mesh("mesh1").run("ftri1");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().named("cyl7");
    model.component("comp1").mesh("mesh1").run("map1");
    model.component("comp1").mesh("mesh1").create("cpf1", "CopyFace");
    model.component("comp1").mesh("mesh1").feature("cpf1").selection("source").geom(2);
    model.component("comp1").mesh("mesh1").feature("cpf1").selection("destination").geom(2);
    model.component("comp1").mesh("mesh1").feature("cpf1").selection("source").named("uni3");
    model.component("comp1").mesh("mesh1").feature("cpf1").selection("destination").named("dif2");
    model.component("comp1").mesh("mesh1").run("cpf1");
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().named("cyl8");
    model.component("comp1").mesh("mesh1").feature("ftri2").set("method", "del");
    model.component("comp1").mesh("mesh1").feature("ftri2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmax", "Tarm/4");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hmin", "Tarm/4");
    model.component("comp1").mesh("mesh1").run("ftri2");
    model.component("comp1").mesh("mesh1").create("map2", "Map");
    model.component("comp1").mesh("mesh1").feature("map2").selection().named("cyl9");
    model.component("comp1").mesh("mesh1").run("map2");
    model.component("comp1").mesh("mesh1").create("cpf2", "CopyFace");
    model.component("comp1").mesh("mesh1").feature("cpf2").selection("source").geom(2);
    model.component("comp1").mesh("mesh1").feature("cpf2").selection("destination").geom(2);
    model.component("comp1").mesh("mesh1").feature("cpf2").selection("source").named("uni4");
    model.component("comp1").mesh("mesh1").feature("cpf2").selection("destination").named("dif3");
    model.component("comp1").mesh("mesh1").run("cpf2");
    model.component("comp1").mesh("mesh1").create("map3", "Map");
    model.component("comp1").mesh("mesh1").feature("map3").selection().named("int2");
    model.component("comp1").mesh("mesh1").feature("map3").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("map3").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("map3").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("map3").feature("size1").set("hmax", "3*Tpbs");
    model.component("comp1").mesh("mesh1").feature("map3").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("map3").feature("size1").set("hmin", "3*Tpbs");
    model.component("comp1").mesh("mesh1").run("map3");
    model.component("comp1").mesh("mesh1").create("ftri3", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri3").selection().named("dif5");
    model.component("comp1").mesh("mesh1").feature("ftri3").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri3").feature("size1").selection().named("cyl11");
    model.component("comp1").mesh("mesh1").feature("ftri3").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri3").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri3").feature("size1").set("hmax", "Tarm");
    model.component("comp1").mesh("mesh1").feature("ftri3").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri3").feature("size1").set("hmin", "Tarm");
    model.component("comp1").mesh("mesh1").feature("ftri3").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri3").feature("size1").set("hgrad", 2);
    model.component("comp1").mesh("mesh1").run("ftri3");
    model.component("comp1").mesh("mesh1").create("map4", "Map");
    model.component("comp1").mesh("mesh1").feature("map4").selection().named("cyl10");
    model.component("comp1").mesh("mesh1").run("map4");
    model.component("comp1").mesh("mesh1").feature("map4").selection().set(3141, 3142, 3145, 3146);
    model.component("comp1").mesh("mesh1").feature("map4").selection().named("cyl10");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().named("uni5");

    model.component("comp1").view("view3").set("transparency", true);

    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().named("cyl12");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", "round(Lsec/(Dpha/3))");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection().named("cyl13");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("numelem", "round(Lsec/(3*Tarm))");
    model.component("comp1").mesh("mesh1").run("swe1");
    model.component("comp1").mesh("mesh1").create("conv1", "Convert");
    model.component("comp1").mesh("mesh1").feature("conv1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("conv1").selection().named("dif6");
    model.component("comp1").mesh("mesh1").run("conv1");
    model.component("comp1").mesh("mesh1").create("cpf3", "CopyFace");
    model.component("comp1").mesh("mesh1").feature("cpf3").selection("source").geom(2);
    model.component("comp1").mesh("mesh1").feature("cpf3").selection("destination").geom(2);
    model.component("comp1").mesh("mesh1").feature("cpf3").selection("source").named("int4");
    model.component("comp1").mesh("mesh1").feature("cpf3").selection("destination").named("int5");
    model.component("comp1").mesh("mesh1").run("cpf3");
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().named("com2");
    model.component("comp1").mesh("mesh1").feature("ftet1").set("zscale", "1/6");
    model.component("comp1").mesh("mesh1").feature("ftet1").set("optlevel", "high");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hgrad", 2);
    model.component("comp1").mesh("mesh1").run("ftet1");

    model.result().dataset().create("mesh1", "Mesh");
    model.result().dataset("mesh1").set("mesh", "mesh1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7f51\u683c\u56fe 1");
    model.result("pg1").set("data", "mesh1");
    model.result("pg1").set("inherithide", true);
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").create("mesh1", "Mesh");
    model.result("pg1").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg1").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg1").feature("mesh1").set("meshdomain", "volume");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").label("\u7f51\u683c\u8d28\u91cf\uff0c\u4f53\u79ef\u5355\u5143");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u7f51\u683c\u8d28\u91cf\uff0c\u4f53\u79ef\u5355\u5143");
    model.result("pg1").set("view", "view5");
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("mesh1").set("colortable", "WaveLight");
    model.result("pg1").feature("mesh1").set("filteractive", true);
    model.result("pg1").feature("mesh1").set("logfilterexpr", "z<Lsec/2-2*Nper*(Dcab+x+y)");
    model.result("pg1").run();

    model.component("comp1").view("view5").set("transparency", false);

    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u7f51\u683c\u8d28\u91cf\uff0c\u8d28\u91cf\u8f83\u5dee\u7684\u5355\u5143");
    model.result("pg2").set("title", "\u7f51\u683c\u8d28\u91cf\uff0c\u8d28\u91cf\u8f83\u5dee\u7684\u5355\u5143");
    model.result("pg2").run();
    model.result("pg2").feature("mesh1").set("elemcolor", "white");
    model.result("pg2").feature("mesh1").set("wireframecolor", "none");
    model.result("pg2").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg2").feature("mesh1").feature("sel1").selection().named("adj3");

    model.component("comp1").view("view5").set("transparency", true);

    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("mesh2", "mesh1");
    model.result("pg2").run();
    model.result("pg2").feature("mesh2").set("wireframecolor", "black");
    model.result("pg2").feature("mesh2").set("filteractive", false);
    model.result("pg2").run();
    model.result("pg2").feature("mesh2").feature("sel1").selection().named("dif1");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("mesh3", "Mesh");
    model.result("pg2").feature("mesh3").set("colortable", "TrafficFlow");
    model.result("pg2").feature("mesh3").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mesh3").set("nonlinearcolortablerev", true);
    model.result("pg2").feature("mesh3").set("meshdomain", "volume");
    model.result("pg2").feature("mesh3").set("elemcolor", "red");
    model.result("pg2").feature("mesh3").set("filteractive", true);
    model.result("pg2").feature("mesh3").set("elemfilter", "quality");
    model.result("pg2").feature("mesh3").set("tetkeep", 0.01);
    model.result("pg2").run();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("quickplane", "xy");
    model.result().dataset("cpl1").set("quickz", "-Lsec/2");
    model.result().dataset().create("cpl2", "CutPlane");
    model.result().dataset("cpl2").set("quickplane", "xy");
    model.result().dataset("cpl2").set("quickz", "Lsec/2");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u7f51\u683c\u6bd4\u8f83\uff0c\u6e90\u548c\u76ee\u6807");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u7f51\u683c\u6bd4\u8f83\uff0c\u6e90\u548c\u76ee\u6807");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "1");
    model.result("pg3").feature("surf1").set("coloring", "uniform");
    model.result("pg3").feature("surf1").set("wireframe", true);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("surf2", "surf1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").set("data", "cpl2");
    model.result("pg3").feature("surf2").set("color", "blue");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").create("def1", "Deform");
    model.result("pg3").feature("surf2").feature("def1").set("planecoordsys", "cartesian");
    model.result("pg3").feature("surf2").feature("def1").set("expr", new String[]{"", "", ""});
    model.result("pg3").feature("surf2").feature("def1").set("descr", "");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").feature("def1")
         .set("expr", new String[]{"(x*cos(-Tsec)-y*sin(-Tsec))-x+0.002*x", "", ""});
    model.result("pg3").feature("surf2").feature("def1")
         .setIndex("expr", "(x*sin(-Tsec)+y*cos(-Tsec))-y+0.002*y", 1);
    model.result("pg3").feature("surf2").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("surf2").feature("def1").set("scale", 1);
    model.result("pg3").run();
    model.result("pg2").run();
    model.result("pg2").run();

    model.component("comp1").view("view5").set("transparency", true);
    model.component("comp1").view("view1").set("locked", false);
    model.component("comp1").view("view2").set("locked", false);
    model.component("comp1").view("view3").set("locked", false);
    model.component("comp1").view("view4").set("locked", false);
    model.component("comp1").view("view5").set("locked", false);
    model.component("comp1").view("view1").camera().set("projection", "orthographic");
    model.component("comp1").view("view1").camera().set("orthoscale", 0.5474655628204346);
    model.component("comp1").view("view2").camera().set("projection", "orthographic");
    model.component("comp1").view("view2").camera().set("orthoscale", 0.3977237939834595);
    model.component("comp1").view("view3").camera().set("projection", "orthographic");
    model.component("comp1").view("view3").camera().set("orthoscale", 0.3977237939834595);
    model.component("comp1").view("view4").camera().set("projection", "orthographic");
    model.component("comp1").view("view4").camera().set("orthoscale", 0.3555237948894501);

    model.result("pg3").run();
    model.result("pg2").run();

    model.title("\u6d77\u5e95\u7535\u7f06 7 - \u51e0\u4f55\u548c\u7f51\u683c\u4e09\u7ef4\u6a21\u578b");

    model
         .description("\u201c\u7535\u611f\u6548\u5e94\u4e09\u7ef4\u6a21\u578b\u201d\u6559\u7a0b\uff08\u672c\u7cfb\u5217\u7684\u4e0b\u4e00\u7bc7\u6559\u7a0b\uff09\u9700\u8981\u5728\u51e0\u4f55\u5904\u7406\u548c\u7f51\u683c\u5212\u5206\u65b9\u9762\u8fdb\u884c\u5927\u91cf\u7684\u51c6\u5907\u3002\u5b9e\u9645\u4e0a\uff0c\u8fd9\u901a\u5e38\u8868\u793a\u5bf9\u5927\u578b\u4e09\u7ef4 FEM \u6a21\u578b\uff08\u5c24\u5176\u662f\u626d\u8f6c\u7535\u7f06\u6a21\u578b\uff09\u8fdb\u884c\u7684\u4e3b\u8981\u5de5\u4f5c\u3002\u4e3a\u4e86\u4e0d\u5ffd\u7565\u8fd9\u4e9b\u91cd\u8981\u7684\u4e3b\u9898\uff0c\u6211\u4eec\u4f1a\u5728\u5355\u72ec\u7684\u6559\u7a0b\u4e2d\u5bf9\u5b83\u4eec\u8fdb\u884c\u4ecb\u7ecd\u3002\n\n\u672c\u6559\u7a0b\u5206\u4e3a\u56db\u4e2a\u90e8\u5206\u3002\u7b2c\u4e00\u90e8\u5206\u914d\u7f6e\u76f8\u673a\u8bbe\u7f6e\uff08\u4ee5\u786e\u4fdd\u51e0\u4f55\u3001\u7f51\u683c\u548c\u7ed8\u56fe\u6309\u9884\u671f\u6e32\u67d3\uff09\u3002\u7136\u540e\uff0c\u6dfb\u52a0\u51e0\u4f55\u5e8f\u5217\u548c\u9009\u62e9\u3002\u6700\u540e\uff0c\u6784\u5efa\u7f51\u683c\u3002\u4e3a\u4e86\u7814\u7a76\u7f51\u683c\u7684\u8d28\u91cf\uff0c\u9700\u8981\u8fdb\u884c\u4e00\u4e9b\u540e\u5904\u7406\u3002");

    model.label("submarine_cable_07_geom_mesh_3d.mph");

    model.result("pg2").run();

    model.component("comp1").view("view2").camera().set("projection", "perspective");
    model.component("comp1").view("view2").camera().set("zoomanglefull", 10);
    model.component("comp1").view("view1").camera().set("projection", "orthographic");
    model.component("comp1").view("view1").camera().set("orthoscale", 0.3132075071334839);
    model.component("comp1").view("view2").camera().set("projection", "orthographic");
    model.component("comp1").view("view2").camera().set("orthoscale", 0.2982928454875946);
    model.component("comp1").view("view3").camera().set("projection", "orthographic");
    model.component("comp1").view("view3").camera().set("orthoscale", 0.2982928454875946);
    model.component("comp1").view("view4").camera().set("projection", "orthographic");
    model.component("comp1").view("view4").camera().set("orthoscale", 0.26664286851882935);
    model.component("comp1").view("view1").set("locked", true);
    model.component("comp1").view("view2").set("locked", true);
    model.component("comp1").view("view3").set("locked", true);
    model.component("comp1").view("view4").set("locked", true);
    model.component("comp1").view("view5").set("locked", true);

    model.param("par3").set("Nper", "1/10");

    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");
    model.component("comp1").physics("mf").feature("fsp1").set("sigma_stab_mat", "from_delta_s");
    model.component("comp1").physics("mf").feature("fsp1").set("delta_s", "6*CPcab");
    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 3);
    model.component("comp1").physics("mf").feature("als1").selection().named("uni2");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/mf", true);
    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").create("ccc", "CoilCurrentCalculation");
    model.study("std1").feature().move("ccc", 0);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u94dc");
    model.component("comp1").material("mat1").selection().named("cyl3");
    model.component("comp1").material("mat1").set("family", "copper");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u94c5");
    model.component("comp1").material("mat2").selection().named("dif1");
    model.component("comp1").material("mat2").set("family", "lead");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u9540\u950c\u94a2");
    model.component("comp1").material("mat3").selection().named("adj3");
    model.component("comp1").material("mat3").set("family", "steel");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"Mcup"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"Ncon*Scup"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"Mpbs"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"Spbs"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermeability", new String[]{"Marm"});
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"Sarm"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"1"});

    model.component("comp1").physics("mf").prop("ShapeProperty").set("order_magneticvectorpotential", 1);
    model.component("comp1").physics("mf").create("pc1", "PeriodicCondition", 2);
    model.component("comp1").physics("mf").feature("pc1").selection().named("uni1");
    model.component("comp1").physics("mf").create("coil1", "Coil", 3);
    model.component("comp1").physics("mf").feature("coil1").label("\u76f8 1");
    model.component("comp1").physics("mf").feature("coil1").selection().named("ball1");

    model.component("comp1").view("view2").set("renderwireframe", true);

    model.component("comp1").physics("mf").feature("coil1").set("ICoil", "I0");
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").feature("ct1").selection().named("cyl1");
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").create("cg1", "CoilGround", 2);
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").feature("cg1").selection().named("cyl2");
    model.component("comp1").physics("mf").feature().duplicate("coil2", "coil1");
    model.component("comp1").physics("mf").feature("coil2").label("\u76f8 2");
    model.component("comp1").physics("mf").feature("coil2").selection().named("ball2");
    model.component("comp1").physics("mf").feature("coil2").set("ICoil", "I0*exp(-120[deg]*j)");
    model.component("comp1").physics("mf").feature().duplicate("coil3", "coil2");
    model.component("comp1").physics("mf").feature("coil3").label("\u76f8 3");
    model.component("comp1").physics("mf").feature("coil3").selection().named("ball3");

    model.component("comp1").view("view2").set("renderwireframe", false);

    model.component("comp1").physics("mf").feature("coil3").set("ICoil", "I0*exp(+120[deg]*j)");

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").label("\u78c1\u901a\u5bc6\u5ea6\u6a21 (mf)");
    model.result("pg4").create("mslc1", "Multislice");
    model.result("pg4").run();
    model.result().dataset("dset1").selection().geom("geom1", 3);
    model.result().dataset("dset1").selection().named("cyl5");
    model.result().dataset().create("cpl3", "CutPlane");
    model.result().dataset("cpl3").set("quickplane", "xy");
    model.result().dataset("cpl3").set("genparaactive", true);
    model.result().dataset("cpl3").set("genparadist", "Lsec*{-1/2,-1/4,1/4,1/2}");
    model.result("pg4").run();
    model.result("pg4").set("data", "cpl3");
    model.result("pg4").set("view", "view5");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").run();
    model.result("pg4").feature().remove("mslc1");
    model.result("pg4").run();
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("data", "dset1");
    model.result("pg4").feature("vol1").set("titletype", "none");
    model.result("pg4").feature("vol1").set("inheritplot", "surf1");
    model.result("pg4").feature("vol1").create("sel1", "Selection");
    model.result("pg4").feature("vol1").feature("sel1").selection().named("uni2");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u7eb5\u5411\u7535\u6d41\u5bc6\u5ea6 (mf)");
    model.result("pg5").set("data", "cpl3");
    model.result("pg5").set("view", "view1");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("data", "dset1");
    model.result("pg5").feature("vol1").set("expr", "mf.Jz");
    model.result("pg5").feature("vol1").set("descr", "\u7535\u6d41\u5bc6\u5ea6\uff0cz \u5206\u91cf");
    model.result("pg5").feature("vol1").create("sel1", "Selection");
    model.result("pg5").feature("vol1").feature("sel1").selection().named("adj3");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").create("arws1", "ArrowSurface");
    model.result("pg5").feature("arws1").set("data", "dset1");
    model.result("pg5").feature("arws1").set("expr", new String[]{"0", "0", "mf.Jz"});
    model.result("pg5").feature("arws1").set("plotcomp", "tangential");
    model.result("pg5").feature("arws1").set("titletype", "none");
    model.result("pg5").feature("arws1").set("placement", "elements");
    model.result("pg5").feature("arws1").set("arrowtype", "cone");
    model.result("pg5").feature("arws1").set("color", "black");
    model.result("pg5").feature("arws1").create("sel1", "Selection");
    model.result("pg5").feature("arws1").feature("sel1").selection().named("adj6");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u6a2a\u5411\u7535\u6d41\u5bc6\u5ea6 (mf)");
    model.result("pg6").run();
    model.result("pg6").feature("vol1").set("expr", "sqrt(real(mf.Jx)^2+real(mf.Jy)^2)");
    model.result("pg6").feature("vol1").set("descractive", true);
    model.result("pg6").feature("vol1")
         .set("descr", "\u6a2a\u5411\u7535\u6d41\u5bc6\u5ea6\u6a21\uff08\u77ac\u65f6\uff09");
    model.result("pg6").run();
    model.result("pg6").feature("arws1").set("expr", new String[]{"mf.Jx", "mf.Jy", "0"});
    model.result("pg6").run();
    model.result().numerical().create("int1", "IntVolume");
    model.result().numerical("int1").label("\u76f8\u635f\u8017");
    model.result().numerical("int1").selection().named("cyl3");
    model.result().numerical("int1").setIndex("expr", "mf.Qh/Lsec", 0);
    model.result().numerical("int1").setIndex("unit", "W/km", 0);
    model.result().numerical("int1")
         .setIndex("descr", "\u76f8\u635f\u8017\uff08\u4e8c\u7ef4\u62c9\u4f38\u6a21\u578b\uff09", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u76f8\u635f\u8017");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().numerical().create("int2", "IntVolume");
    model.result().numerical("int2").label("\u5c4f\u853d\u5c42\u635f\u8017");
    model.result().numerical("int2").selection().named("dif1");
    model.result().numerical("int2").setIndex("expr", "mf.Qh/Lsec", 0);
    model.result().numerical("int2").setIndex("unit", "W/km", 0);
    model.result().numerical("int2")
         .setIndex("descr", "\u5c4f\u853d\u5c42\u635f\u8017\uff08\u4e8c\u7ef4\u62c9\u4f38\u6a21\u578b\uff09", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5c4f\u853d\u5c42\u635f\u8017");
    model.result().numerical("int2").set("table", "tbl2");
    model.result().numerical("int2").setResult();
    model.result().numerical().create("int3", "IntVolume");
    model.result().numerical("int3").label("\u94e0\u88c5\u635f\u8017");
    model.result().numerical("int3").selection().named("adj3");
    model.result().numerical("int3").setIndex("expr", "mf.Qh/Lsec", 0);
    model.result().numerical("int3").setIndex("unit", "W/km", 0);
    model.result().numerical("int3")
         .setIndex("descr", "\u94e0\u88c5\u635f\u8017\uff08\u4e8c\u7ef4\u62c9\u4f38\u6a21\u578b\uff09", 0);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u94e0\u88c5\u635f\u8017");
    model.result().numerical("int3").set("table", "tbl3");
    model.result().numerical("int3").setResult();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u76f8\u4ea4\u6d41\u7535\u963b");
    model.result().numerical("gev1").setIndex("expr", "(mf.RCoil_1/Lsec+mf.RCoil_2/Lsec+mf.RCoil_3/Lsec)/3", 0);
    model.result().numerical("gev1").setIndex("unit", "mohm/km", 0);
    model.result().numerical("gev1")
         .setIndex("descr", "\u76f8\u4ea4\u6d41\u7535\u963b\uff08\u4e8c\u7ef4\u62c9\u4f38\u6a21\u578b\uff09", 0);
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("\u76f8\u4ea4\u6d41\u7535\u963b");
    model.result().numerical("gev1").set("table", "tbl4");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("\u76f8\u7535\u611f");
    model.result().numerical("gev2").setIndex("expr", "(mf.LCoil_1/Lsec+mf.LCoil_2/Lsec+mf.LCoil_3/Lsec)/3", 0);
    model.result().numerical("gev2").setIndex("unit", "mH/km", 0);
    model.result().numerical("gev2")
         .setIndex("descr", "\u76f8\u7535\u611f\uff08\u4e8c\u7ef4\u62c9\u4f38\u6a21\u578b\uff09", 0);
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").comments("\u76f8\u7535\u611f");
    model.result().numerical("gev2").set("table", "tbl5");
    model.result().numerical("gev2").setResult();
    model.result("pg4").run();
    model.result("pg4").run();

    return model;
  }

  public static Model run4(Model model) {

    model.component("comp1").view("view5").set("transparency", true);
    model.component("comp1").view("view1").set("locked", false);
    model.component("comp1").view("view2").set("locked", false);
    model.component("comp1").view("view3").set("locked", false);
    model.component("comp1").view("view4").set("locked", false);
    model.component("comp1").view("view5").set("locked", false);
    model.component("comp1").view("view1").camera().set("projection", "orthographic");
    model.component("comp1").view("view1").camera().set("orthoscale", 0.32947847247123724);
    model.component("comp1").view("view2").camera().set("projection", "orthographic");
    model.component("comp1").view("view2").camera().set("orthoscale", 0.3977237939834595);
    model.component("comp1").view("view3").camera().set("projection", "orthographic");
    model.component("comp1").view("view3").camera().set("orthoscale", 0.3977237939834595);
    model.component("comp1").view("view4").camera().set("projection", "orthographic");
    model.component("comp1").view("view4").camera().set("orthoscale", 0.3555237948894501);

    model.title("\u6d77\u5e95\u7535\u7f06 8a - \u7535\u611f\u6548\u5e94\u4e09\u7ef4\u6a21\u578b");

    model
         .description("\u8fd9\u4e2a MPH \u6587\u4ef6\u8868\u793a\u5728\u201c\u7535\u611f\u6548\u5e94\u4e09\u7ef4\u6a21\u578b\u201d\u6559\u7a0b\u4e2d\u6784\u5efa\u548c\u5206\u6790\u7684\u6a21\u578b\u7684\u4e2d\u95f4\u72b6\u6001\u3002\u6709\u5173\u66f4\u591a\u4fe1\u606f\uff0c\u5305\u62ec\u8be6\u7ec6\u7684\u4ecb\u7ecd\u3001\u5206\u6b65\u64cd\u4f5c\u8bf4\u660e\u548c\u7ed3\u679c\u5206\u6790\uff08\u5305\u542b\u9a8c\u8bc1\u4ee5\u53ca\u5bf9\u5b66\u672f\u7814\u7a76\u7684\u5f15\u7528\uff09\uff0c\u8bf7\u53c2\u9605\u201c\u6848\u4f8b\u5e93\u201d\u4e2d\u672c\u6559\u7a0b\u7684\u4e3b\u5165\u53e3\u70b9\u9644\u5e26\u7684 *.pdf \u6587\u4ef6\uff1asubmarine_cable_08_inductive_effects_3d\u3002");

    model.label("submarine_cable_08_a_inductive_effects_3d.mph");

    model.result("pg4").run();

    model.component("comp1").view("view1").camera().set("projection", "orthographic");
    model.component("comp1").view("view1").camera().set("orthoscale", 0.3132075071334839);
    model.component("comp1").view("view2").camera().set("projection", "orthographic");
    model.component("comp1").view("view2").camera().set("orthoscale", 0.2982928454875946);
    model.component("comp1").view("view3").camera().set("projection", "orthographic");
    model.component("comp1").view("view3").camera().set("orthoscale", 0.2982928454875946);
    model.component("comp1").view("view4").camera().set("projection", "orthographic");
    model.component("comp1").view("view4").camera().set("orthoscale", 0.26664286851882935);
    model.component("comp1").view("view1").set("locked", true);
    model.component("comp1").view("view2").set("locked", true);
    model.component("comp1").view("view3").set("locked", true);
    model.component("comp1").view("view4").set("locked", true);
    model.component("comp1").view("view5").set("locked", true);

    model.result("pg4").run();
    model.result("pg4").label("\u78c1\u901a\u5bc6\u5ea6\uff0cz \u5206\u91cf\u6a21 (mf)");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "abs(mf.Bz)");
    model.result("pg4").feature("surf1").set("unit", "mT");
    model.result("pg4").feature("surf1").set("descractive", true);
    model.result("pg4").feature("surf1").set("descr", "\u78c1\u901a\u5bc6\u5ea6\uff0cz \u5206\u91cf\u6a21");
    model.result("pg4").run();
    model.result("pg4").feature("vol1").set("expr", "abs(mf.Bz)");
    model.result("pg4").feature("vol1").set("unit", "mT");
    model.result("pg4").run();

    model.param("par3").set("Nper", "1");

    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").stat().setQualityMeasure("skewness");

    model.component("comp1").coordSystem().create("sys2", "Rotated");
    model.component("comp1").coordSystem("sys2").set("angle", new String[]{"Tsec", "0", "0"});
    model.component("comp1").coordSystem().create("sys3", "Cylindrical");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u94e0\u88c5\u7ebf\u53d8\u91cf");
    model.component("comp1").variable("var1").selection().geom("geom1", 3);
    model.component("comp1").variable("var1").selection().named("adj3");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("e_awx", "if(Tenab,sys3.e_phi1[1/rad]*(2*pi*Darm/2)/sqrt((2*pi*Darm/2)^2+LLarm^2),0)", "\u94e0\u88c5\u7ebf\u65b9\u5411\uff0cx \u5206\u91cf\uff08\u5355\u4f4d\u77e2\u91cf\uff09");
    model.component("comp1").variable("var1")
         .set("e_awy", "if(Tenab,sys3.e_phi2[1/rad]*(2*pi*Darm/2)/sqrt((2*pi*Darm/2)^2+LLarm^2),0)", "\u94e0\u88c5\u7ebf\u65b9\u5411\uff0cy \u5206\u91cf\uff08\u5355\u4f4d\u77e2\u91cf\uff09");
    model.component("comp1").variable("var1")
         .set("e_awz", "if(Tenab,LLarm/sqrt((2*pi*Darm/2)^2+LLarm^2),1)", "\u94e0\u88c5\u7ebf\u65b9\u5411\uff0cz \u5206\u91cf\uff08\u5355\u4f4d\u77e2\u91cf\uff09");
    model.component("comp1").variable("var1")
         .set("BL", "e_awx*mf.Bx+e_awy*mf.By+e_awz*mf.Bz", "\u78c1\u901a\u5bc6\u5ea6\uff0c\u7eb5\u5411\u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("BLx", "e_awx*BL", "\u7eb5\u5411\u78c1\u901a\u5bc6\u5ea6\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("BLy", "e_awy*BL", "\u7eb5\u5411\u78c1\u901a\u5bc6\u5ea6\uff0cy \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("BLz", "e_awz*BL", "\u7eb5\u5411\u78c1\u901a\u5bc6\u5ea6\uff0cz \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("BTx", "mf.Bx-BLx", "\u6a2a\u5411\u78c1\u901a\u5bc6\u5ea6\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("BTy", "mf.By-BLy", "\u6a2a\u5411\u78c1\u901a\u5bc6\u5ea6\uff0cy \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("BTz", "mf.Bz-BLz", "\u6a2a\u5411\u78c1\u901a\u5bc6\u5ea6\uff0cz \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("JL", "e_awx*mf.Jx+e_awy*mf.Jy+e_awz*mf.Jz", "\u7535\u6d41\u5bc6\u5ea6\uff0c\u7eb5\u5411\u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("JLx", "e_awx*JL", "\u7eb5\u5411\u7535\u6d41\u5bc6\u5ea6\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("JLy", "e_awy*JL", "\u7eb5\u5411\u7535\u6d41\u5bc6\u5ea6\uff0cy \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("JLz", "e_awz*JL", "\u7eb5\u5411\u7535\u6d41\u5bc6\u5ea6\uff0cz \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("JTx", "mf.Jx-JLx", "\u6a2a\u5411\u7535\u6d41\u5bc6\u5ea6\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("JTy", "mf.Jy-JLy", "\u6a2a\u5411\u7535\u6d41\u5bc6\u5ea6\uff0cy \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("JTz", "mf.Jz-JLz", "\u6a2a\u5411\u7535\u6d41\u5bc6\u5ea6\uff0cz \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("normBLi", "sqrt(real(BLx)^2+real(BLy)^2+real(BLz)^2)", "\u7eb5\u5411\u78c1\u901a\u5bc6\u5ea6\u6a21\uff08\u77ac\u65f6\uff09");
    model.component("comp1").variable("var1")
         .set("normBTi", "sqrt(real(BTx)^2+real(BTy)^2+real(BTz)^2)", "\u6a2a\u5411\u78c1\u901a\u5bc6\u5ea6\u6a21\uff08\u77ac\u65f6\uff09");
    model.component("comp1").variable("var1")
         .set("normJLi", "sqrt(real(JLx)^2+real(JLy)^2+real(JLz)^2)", "\u7eb5\u5411\u7535\u6d41\u5bc6\u5ea6\u6a21\uff08\u77ac\u65f6\uff09");
    model.component("comp1").variable("var1")
         .set("normJTi", "sqrt(real(JTx)^2+real(JTy)^2+real(JTz)^2)", "\u6a2a\u5411\u7535\u6d41\u5bc6\u5ea6\u6a21\uff08\u77ac\u65f6\uff09");
    model.component("comp1").variable("var1")
         .set("normBL", "sqrt(abs(BLx)^2+abs(BLy)^2+abs(BLz)^2)", "\u7eb5\u5411\u78c1\u901a\u5bc6\u5ea6\u6a21\uff08\u7edd\u5bf9\uff09");
    model.component("comp1").variable("var1")
         .set("normBT", "sqrt(abs(BTx)^2+abs(BTy)^2+abs(BTz)^2)", "\u6a2a\u5411\u78c1\u901a\u5bc6\u5ea6\u6a21\uff08\u7edd\u5bf9\uff09");
    model.component("comp1").variable("var1")
         .set("normJL", "sqrt(abs(JLx)^2+abs(JLy)^2+abs(JLz)^2)", "\u7eb5\u5411\u7535\u6d41\u5bc6\u5ea6\u6a21\uff08\u7edd\u5bf9\uff09");
    model.component("comp1").variable("var1")
         .set("normJT", "sqrt(abs(JTx)^2+abs(JTy)^2+abs(JTz)^2)", "\u6a2a\u5411\u7535\u6d41\u5bc6\u5ea6\u6a21\uff08\u7edd\u5bf9\uff09");

    model.component("comp1").physics("mf").feature("pc1").set("TransformationMethod", "GlobalSystem");
    model.component("comp1").physics("mf").feature("pc1").set("manualDestinationSelection", true);
    model.component("comp1").physics("mf").feature("pc1").selection("destinationDomains").named("cyl1");
    model.component("comp1").physics("mf").feature("pc1").set("TransformationMethod_dst", "sys2");
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").feature("ct1").set("SlantedCut", true);
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").feature("cg1").set("SlantedCut", true);
    model.component("comp1").physics("mf").feature("coil2").feature("ccc1").feature("ct1").set("SlantedCut", true);
    model.component("comp1").physics("mf").feature("coil2").feature("ccc1").feature("cg1").set("SlantedCut", true);
    model.component("comp1").physics("mf").feature("coil3").feature("ccc1").feature("ct1").set("SlantedCut", true);
    model.component("comp1").physics("mf").feature("coil3").feature("ccc1").feature("cg1").set("SlantedCut", true);

    model.sol("sol1").feature("st2").clearXmesh();
    model.sol("sol1").feature("st2").xmeshInfo();
    model.sol("sol1").feature("st2").clearXmesh();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg4").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").feature("vol1").set("expr", "JL");
    model.result("pg5").run();
    model.result("pg5").feature("arws1").set("expr", new String[]{"JLx", "JLy", "JLz"});
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").feature("vol1").set("expr", "normJTi");
    model.result("pg6").run();
    model.result("pg6").feature("arws1").set("expr", new String[]{"JTx", "JTy", "JTz"});
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("vol1").set("expr", "if(-mf.iomega*BL>0,1,-1)*normJTi");
    model.result("pg6").feature("vol1").set("descr", "\u7535\u6d41\u5bc6\u5ea6\uff0c\u6a2a\u5411\u5206\u91cf");
    model.result("pg6").run();
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").set("param", "xy");
    model.result().dataset("surf1").selection().named("cyl15");
    model.result().dataset("dset2").selection().geom("geom1", 2);
    model.result().dataset("dset2").selection().named("cyl15");
    model.result().dataset("dset2").selection().allGeom();
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").run();
    model.result("pg7").label("\u7eb5\u5411\u548c\u6a2a\u5411\u7535\u6d41\u5bc6\u5ea6 (mf)");
    model.result("pg7").set("data", "surf1");
    model.result("pg7").set("showlegendsmaxmin", true);
    model.result("pg7").create("line1", "Line");
    model.result("pg7").feature("line1").set("expr", "1");
    model.result("pg7").feature("line1").set("titletype", "none");
    model.result("pg7").feature("line1").set("coloring", "uniform");
    model.result("pg7").feature("line1").set("color", "black");
    model.result("pg7").feature("line1").create("def1", "Deform");
    model.result("pg7").run();
    model.result("pg7").feature("line1").feature("def1").set("expr", new String[]{"x", "y"});
    model.result("pg7").feature("line1").feature("def1").set("scaleactive", true);
    model.result("pg7").feature("line1").feature("def1").set("scale", 0.1);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "JL");
    model.result("pg7").feature("surf1").set("colortable", "Dipole");
    model.result("pg7").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg7").feature("surf1").create("hght1", "Height");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").feature("hght1").set("scaleactive", true);
    model.result("pg7").feature("surf1").feature("hght1").set("scale", "5E-7");
    model.result("pg7").run();

    model.view("view11").set("showgrid", false);

    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf2", "surf1");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").set("expr", "if(-mf.iomega*BL>0,1,-1)*normJTi");
    model.result("pg7").feature("surf2").set("descractive", true);
    model.result("pg7").feature("surf2").set("descr", "\u7535\u6d41\u5bc6\u5ea6\uff0c\u6a2a\u5411\u5206\u91cf");
    model.result("pg7").feature("surf2").set("inheritplot", "surf1");
    model.result("pg7").feature("surf2").set("inheritdeformscale", false);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("surf2").feature().copy("def1", "pg7/line1/def1");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().dataset("surf1").selection().named("cyl16");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result().dataset("surf1").selection().named("cyl15");
    model.result("pg7").run();
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
    model.result().export("anim1").set("plotgroup", "pg7");
    model.result().export("anim1").set("sweeptype", "dde");
    model.result().export("anim1").set("maxframes", 6);
    model.result().export("anim1").run();
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").run();
    model.result("pg8").label("\u4f53\u79ef\u635f\u8017\u5bc6\u5ea6\uff0c\u7535\u78c1 (mf)");
    model.result("pg8").set("data", "cpl3");
    model.result("pg8").set("view", "view5");
    model.result("pg8").set("showlegendsmaxmin", true);
    model.result("pg8").create("vol1", "Volume");
    model.result("pg8").feature("vol1").set("data", "dset1");
    model.result("pg8").feature("vol1").set("expr", "mf.Qh");
    model.result("pg8").feature("vol1").set("colortable", "Disco");
    model.result("pg8").feature("vol1").create("sel1", "Selection");
    model.result("pg8").feature("vol1").feature("sel1").selection().named("dif1");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("vol2", "vol1");
    model.result("pg8").run();
    model.result("pg8").feature("vol2").set("titletype", "none");
    model.result("pg8").feature("vol2").set("colortable", "HeatCamera");
    model.result("pg8").run();
    model.result("pg8").feature("vol2").feature("sel1").selection().named("adj3");
    model.result("pg8").run();
    model.result().numerical("int1")
         .setIndex("descr", "\u76f8\u635f\u8017\uff08\u4e09\u7ef4\u626d\u8f6c\u6a21\u578b\uff09", 0);
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").appendResult();
    model.result().numerical("int2")
         .setIndex("descr", "\u5c4f\u853d\u5c42\u635f\u8017\uff08\u4e09\u7ef4\u626d\u8f6c\u6a21\u578b\uff09", 0);
    model.result().numerical("int2").set("table", "tbl2");
    model.result().numerical("int2").appendResult();
    model.result().numerical("int3")
         .setIndex("descr", "\u94e0\u88c5\u635f\u8017\uff08\u4e09\u7ef4\u626d\u8f6c\u6a21\u578b\uff09", 0);
    model.result().numerical("int3").set("table", "tbl3");
    model.result().numerical("int3").appendResult();
    model.result().numerical("gev1")
         .setIndex("descr", "\u76f8\u4ea4\u6d41\u7535\u963b\uff08\u4e09\u7ef4\u626d\u8f6c\u6a21\u578b\uff09", 0);
    model.result().numerical("gev1").set("table", "tbl4");
    model.result().numerical("gev1").appendResult();
    model.result().numerical("gev2")
         .setIndex("descr", "\u76f8\u7535\u611f\uff08\u4e09\u7ef4\u626d\u8f6c\u6a21\u578b\uff09", 0);
    model.result().numerical("gev2").set("table", "tbl5");
    model.result().numerical("gev2").appendResult();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("maxframes", 60);
    model.result().export("anim1").set("repeat", "forever");
    model.result("pg4").run();
    model.result("pg4").run();

    model.component("comp1").view("view5").set("transparency", true);
    model.component("comp1").view("view1").set("locked", false);
    model.component("comp1").view("view2").set("locked", false);
    model.component("comp1").view("view3").set("locked", false);
    model.component("comp1").view("view4").set("locked", false);
    model.component("comp1").view("view5").set("locked", false);
    model.component("comp1").view("view1").camera().set("projection", "orthographic");
    model.component("comp1").view("view1").camera().set("orthoscale", 0.32947847247123724);
    model.component("comp1").view("view2").camera().set("projection", "orthographic");
    model.component("comp1").view("view2").camera().set("orthoscale", 0.3977237939834595);
    model.component("comp1").view("view3").camera().set("projection", "orthographic");
    model.component("comp1").view("view3").camera().set("orthoscale", 0.3977237939834595);
    model.component("comp1").view("view4").camera().set("projection", "orthographic");
    model.component("comp1").view("view4").camera().set("orthoscale", 0.3555237948894501);

    model.title("\u6d77\u5e95\u7535\u7f06 8b - \u7535\u611f\u6548\u5e94\u4e09\u7ef4\u6a21\u578b");

    model
         .description("\u8fd9\u4e2a MPH \u6587\u4ef6\u8868\u793a\u5728\u201c\u7535\u611f\u6548\u5e94\u4e09\u7ef4\u6a21\u578b\u201d\u6559\u7a0b\u4e2d\u6784\u5efa\u548c\u5206\u6790\u7684\u6a21\u578b\u7684\u4e2d\u95f4\u72b6\u6001\u3002\u6709\u5173\u66f4\u591a\u4fe1\u606f\uff0c\u5305\u62ec\u8be6\u7ec6\u7684\u4ecb\u7ecd\u3001\u5206\u6b65\u64cd\u4f5c\u8bf4\u660e\u548c\u7ed3\u679c\u5206\u6790\uff08\u5305\u542b\u9a8c\u8bc1\u4ee5\u53ca\u5bf9\u5b66\u672f\u7814\u7a76\u7684\u5f15\u7528\uff09\uff0c\u8bf7\u53c2\u9605\u201c\u6848\u4f8b\u5e93\u201d\u4e2d\u672c\u6559\u7a0b\u7684\u4e3b\u5165\u53e3\u70b9\u9644\u5e26\u7684 *.pdf \u6587\u4ef6\uff1asubmarine_cable_08_inductive_effects_3d\u3002");

    model.label("submarine_cable_08_b_inductive_effects_3d.mph");

    model.result("pg4").run();

    model.component("comp1").view("view1").camera().set("projection", "orthographic");
    model.component("comp1").view("view1").camera().set("orthoscale", 0.3132075071334839);
    model.component("comp1").view("view2").camera().set("projection", "orthographic");
    model.component("comp1").view("view2").camera().set("orthoscale", 0.2982928454875946);
    model.component("comp1").view("view3").camera().set("projection", "orthographic");
    model.component("comp1").view("view3").camera().set("orthoscale", 0.2982928454875946);
    model.component("comp1").view("view4").camera().set("projection", "orthographic");
    model.component("comp1").view("view4").camera().set("orthoscale", 0.26664286851882935);
    model.component("comp1").view("view1").set("locked", true);
    model.component("comp1").view("view2").set("locked", true);
    model.component("comp1").view("view3").set("locked", true);
    model.component("comp1").view("view4").set("locked", true);
    model.component("comp1").view("view5").set("locked", true);

    model.param().create("par5");
    model.param("par5").label("\u70ed\u53c2\u6570");

//    To import content from file, use:
//    model.param("par5").loadFile("FILENAME");
    model.param("par5")
         .set("Ntcon", "0.075281", "\u4e3b\u5bfc\u4f53\u70ed\u5305\u88c5\u5bc6\u5ea6\uff08\u76f8\uff09");
    model.param("par5").set("Tmcon", "89.4[degC]", "\u4e3b\u5bfc\u4f53\u5e73\u5747\u6e29\u5ea6\uff08\u76f8\uff09");
    model.param("par5")
         .set("Tmpbs", "82.7[degC]", "\u5c4f\u853d\u5c42\u5e73\u5747\u6e29\u5ea6\uff08\u94c5\u5305\uff09");
    model.param("par5").set("Tmarm", "70.1[degC]", "\u94e0\u88c5\u73af\u5e73\u5747\u6e29\u5ea6");
    model.param("par5").set("Tmext", "20[degC]", "\u5916\u90e8\u6e29\u5ea6\uff08\u6d77\u5e8a\uff09");
    model.param("par5").set("Tmref", "20[degC]", "\u7ebf\u6027\u7535\u963b\u7387\u53c2\u8003\u6e29\u5ea6");
    model.param("par5").set("R0cup", "1/Scup", "\u53c2\u8003\u7535\u963b\u7387\uff0c\u94dc\uff0c20\u00b0C");
    model.param("par5").set("R0pbs", "1/Spbs", "\u53c2\u8003\u7535\u963b\u7387\uff0c\u94c5\u5305\uff0c20\u00b0C");
    model.param("par5")
         .set("R0arm", "1/Sarm", "\u53c2\u8003\u7535\u963b\u7387\uff0c\u94e0\u88c5\u7ebf\uff0c20\u00b0C");
    model.param("par5").set("ALcup", "3.9e-3[1/K]", "\u7535\u963b\u7387\u6e29\u5ea6\u7cfb\u6570\uff0c\u94dc");
    model.param("par5").set("ALpbs", "3.86e-3[1/K]", "\u7535\u963b\u7387\u6e29\u5ea6\u7cfb\u6570\uff0c\u94c5\u5305");
    model.param("par5")
         .set("ALarm", "5.3e-3[1/K]", "\u7535\u963b\u7387\u6e29\u5ea6\u7cfb\u6570\uff0c\u94e0\u88c5\u7ebf");

    model.component("comp1").physics("mf").feature("coil1").set("ConstitutiveRelationJcE", "LinearizedResistivity");
    model.component("comp1").physics("mf").feature("coil1").set("minput_temperature_src", "userdef");
    model.component("comp1").physics("mf").feature("coil1").set("minput_temperature", "Tmcon");
    model.component("comp1").physics("mf").feature("coil2").set("ConstitutiveRelationJcE", "LinearizedResistivity");
    model.component("comp1").physics("mf").feature("coil2").set("minput_temperature_src", "userdef");
    model.component("comp1").physics("mf").feature("coil2").set("minput_temperature", "Tmcon");
    model.component("comp1").physics("mf").feature("coil3").set("ConstitutiveRelationJcE", "LinearizedResistivity");
    model.component("comp1").physics("mf").feature("coil3").set("minput_temperature_src", "userdef");
    model.component("comp1").physics("mf").feature("coil3").set("minput_temperature", "Tmcon");
    model.component("comp1").physics("mf").create("als2", "AmperesLawSolid", 3);
    model.component("comp1").physics("mf").feature("als2").label("\u5c4f\u853d\u5c42");
    model.component("comp1").physics("mf").feature("als2").selection().named("dif1");
    model.component("comp1").physics("mf").feature("als2").set("ConstitutiveRelationJcE", "LinearizedResistivity");
    model.component("comp1").physics("mf").feature("als2").set("minput_temperature_src", "userdef");
    model.component("comp1").physics("mf").feature("als2").set("minput_temperature", "Tmpbs");
    model.component("comp1").physics("mf").feature().duplicate("als3", "als2");
    model.component("comp1").physics("mf").feature("als3").label("\u7535\u7f06\u94e0\u88c5");
    model.component("comp1").physics("mf").feature("als3").selection().named("adj3");
    model.component("comp1").physics("mf").feature("als3").set("minput_temperature", "Tmarm");

    model.component("comp1").material("mat1").propertyGroup().create("linzRes", "linzRes", "Linearized_resistivity");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("rho0", new String[]{"R0cup/Ncon"});
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("Tref", new String[]{"Tmref"});
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("alpha", new String[]{"ALcup"});
    model.component("comp1").material("mat2").propertyGroup().create("linzRes", "linzRes", "Linearized_resistivity");
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("rho0", new String[]{"R0pbs"});
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("Tref", new String[]{"Tmref"});
    model.component("comp1").material("mat2").propertyGroup("linzRes").set("alpha", new String[]{"ALpbs"});
    model.component("comp1").material("mat3").propertyGroup().create("linzRes", "linzRes", "Linearized_resistivity");
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("rho0", new String[]{"R0arm"});
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("Tref", new String[]{"Tmref"});
    model.component("comp1").material("mat3").propertyGroup("linzRes").set("alpha", new String[]{"ALarm"});

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg4").run();
    model.result().numerical("int1").setIndex("expr", "mf.Qh/Lsec", 0);
    model.result().numerical("int1").setIndex("unit", "W/km", 0);
    model.result().numerical("int1").setIndex("descr", "\u76f8\u635f\u8017\uff08\u7ebf\u6027\u4e09\u7ef4\uff09", 0);
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").appendResult();
    model.result().numerical("int2").setIndex("expr", "mf.Qh/Lsec", 0);
    model.result().numerical("int2").setIndex("unit", "W/km", 0);
    model.result().numerical("int2")
         .setIndex("descr", "\u5c4f\u853d\u5c42\u635f\u8017\uff08\u7ebf\u6027\u4e09\u7ef4\uff09", 0);
    model.result().numerical("int2").set("table", "tbl2");
    model.result().numerical("int2").appendResult();
    model.result().numerical("int3").setIndex("expr", "mf.Qh/Lsec", 0);
    model.result().numerical("int3").setIndex("unit", "W/km", 0);
    model.result().numerical("int3")
         .setIndex("descr", "\u94e0\u88c5\u635f\u8017\uff08\u7ebf\u6027\u4e09\u7ef4\uff09", 0);
    model.result().numerical("int3").set("table", "tbl3");
    model.result().numerical("int3").appendResult();
    model.result().numerical("gev1").setIndex("expr", "(mf.RCoil_1/Lsec+mf.RCoil_2/Lsec+mf.RCoil_3/Lsec)/3", 0);
    model.result().numerical("gev1").setIndex("unit", "mohm/km", 0);
    model.result().numerical("gev1")
         .setIndex("descr", "\u76f8\u4ea4\u6d41\u7535\u963b\uff08\u7ebf\u6027\u4e09\u7ef4\uff09", 0);
    model.result().numerical("gev1").set("table", "tbl4");
    model.result().numerical("gev1").appendResult();
    model.result().numerical("gev2").setIndex("expr", "(mf.LCoil_1/Lsec+mf.LCoil_2/Lsec+mf.LCoil_3/Lsec)/3", 0);
    model.result().numerical("gev2").setIndex("unit", "mH/km", 0);
    model.result().numerical("gev2").setIndex("descr", "\u76f8\u7535\u611f\uff08\u7ebf\u6027\u4e09\u7ef4\uff09", 0);
    model.result().numerical("gev2").set("table", "tbl5");
    model.result().numerical("gev2").appendResult();
    model.result("pg8").run();
    model.result("pg8").run();

    model.component("comp1").view("view5").set("transparency", true);
    model.component("comp1").view("view1").set("locked", false);
    model.component("comp1").view("view2").set("locked", false);
    model.component("comp1").view("view3").set("locked", false);
    model.component("comp1").view("view4").set("locked", false);
    model.component("comp1").view("view5").set("locked", false);
    model.component("comp1").view("view1").camera().set("projection", "orthographic");
    model.component("comp1").view("view1").camera().set("orthoscale", 0.32947847247123724);
    model.component("comp1").view("view2").camera().set("projection", "orthographic");
    model.component("comp1").view("view2").camera().set("orthoscale", 0.3977237939834595);
    model.component("comp1").view("view3").camera().set("projection", "orthographic");
    model.component("comp1").view("view3").camera().set("orthoscale", 0.3977237939834595);
    model.component("comp1").view("view4").camera().set("projection", "orthographic");
    model.component("comp1").view("view4").camera().set("orthoscale", 0.3555237948894501);

    model.title("\u6d77\u5e95\u7535\u7f06 8c - \u7535\u611f\u6548\u5e94\u4e09\u7ef4\u6a21\u578b");

    model
         .description("\u8fd9\u4e2a MPH \u6587\u4ef6\u8868\u793a\u5728\u201c\u7535\u611f\u6548\u5e94\u4e09\u7ef4\u6a21\u578b\u201d\u6559\u7a0b\u4e2d\u6784\u5efa\u548c\u5206\u6790\u7684\u6a21\u578b\u7684\u4e2d\u95f4\u72b6\u6001\u3002\u6709\u5173\u66f4\u591a\u4fe1\u606f\uff0c\u5305\u62ec\u8be6\u7ec6\u7684\u4ecb\u7ecd\u3001\u5206\u6b65\u64cd\u4f5c\u8bf4\u660e\u548c\u7ed3\u679c\u5206\u6790\uff08\u5305\u542b\u9a8c\u8bc1\u4ee5\u53ca\u5bf9\u5b66\u672f\u7814\u7a76\u7684\u5f15\u7528\uff09\uff0c\u8bf7\u53c2\u9605\u201c\u6848\u4f8b\u5e93\u201d\u4e2d\u672c\u6559\u7a0b\u7684\u4e3b\u5165\u53e3\u70b9\u9644\u5e26\u7684 *.pdf \u6587\u4ef6\uff1asubmarine_cable_08_inductive_effects_3d\u3002");

    model.label("submarine_cable_08_c_inductive_effects_3d.mph");

    model.result("pg8").run();

    model.component("comp1").view("view1").camera().set("projection", "orthographic");
    model.component("comp1").view("view1").camera().set("orthoscale", 0.3132075071334839);
    model.component("comp1").view("view2").camera().set("projection", "orthographic");
    model.component("comp1").view("view2").camera().set("orthoscale", 0.2982928454875946);
    model.component("comp1").view("view3").camera().set("projection", "orthographic");
    model.component("comp1").view("view3").camera().set("orthoscale", 0.2982928454875946);
    model.component("comp1").view("view4").camera().set("projection", "orthographic");
    model.component("comp1").view("view4").camera().set("orthoscale", 0.26664286851882935);
    model.component("comp1").view("view1").set("locked", true);
    model.component("comp1").view("view2").set("locked", true);
    model.component("comp1").view("view3").set("locked", true);
    model.component("comp1").view("view4").set("locked", true);
    model.component("comp1").view("view5").set("locked", true);

    model.result().numerical().create("int4", "IntVolume");
    model.result().numerical("int4").label("\u7edd\u7f18\u4f53\u635f\u8017");
    model.result().numerical("int4").selection().named("com1");
    model.result().numerical("int4").setIndex("expr", "mf.Qh/Lsec", 0);
    model.result().numerical("int4").setIndex("unit", "W/km", 0);
    model.result().numerical("int4")
         .setIndex("descr", "\u7edd\u7f18\u4f53\u635f\u8017\uff08\u7ebf\u6027\u4e09\u7ef4\uff09", 0);
    model.result().table().create("tbl6", "Table");
    model.result().table("tbl6").comments("\u7edd\u7f18\u4f53\u635f\u8017");
    model.result().numerical("int4").set("table", "tbl6");
    model.result().numerical("int4").setResult();
    model.result().create("pg9", "PlotGroup3D");
    model.result("pg9").run();
    model.result("pg9").label("\u5e73\u5747\u7eb5\u5411\u7535\u6d41\u5bc6\u5ea6 (mf)");

    return model;
  }

  public static Model run5(Model model) {
    model.result("pg9").set("data", "cpl3");
    model.result("pg9").set("view", "view1");
    model.result("pg9").set("showlegendsmaxmin", true);
    model.result("pg9").create("line1", "Line");
    model.result("pg9").feature("line1").set("data", "dset1");
    model.result("pg9").feature("line1").set("expr", "0");
    model.result("pg9").feature("line1").set("linetype", "tube");
    model.result("pg9").feature("line1").set("radiusexpr", "Tarm/2");
    model.result("pg9").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg9").feature("line1").set("colortable", "Prism");
    model.result("pg9").feature("line1").create("sel1", "Selection");
    model.result("pg9").feature("line1").feature("sel1").selection().named("cyl4");
    model.result("pg9").run();
    model.result("pg9").feature("line1").create("filt1", "Filter");
    model.result("pg9").run();
    model.result("pg9").feature("line1").feature("filt1").set("expr", "(-0.45*Lsec<z) && (z<0.45*Lsec)");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").feature("line1").set("descractive", true);
    model.result("pg9").feature("line1").set("descr", "\u5e73\u5747\u7eb5\u5411\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg9").feature("line1").set("expr", "abs(diskavg(Tarm/2,JL))");
    model.result("pg9").run();

    model.param("par4").set("i_sc", "0");
    model.param("par4")
         .descr("i_sc", "\u8f85\u52a9\u626b\u63cf\u6307\u6570\uff08\u7528\u4e8e\u7a33\u5b9a\u6027\u8865\u507f\uff09");

    model.component("comp1").common().create("state1", "StateVariables");
    model.component("comp1").common("state1").label("\u7a33\u5b9a\u6027\u8865\u507f");
    model.component("comp1").common("state1").selection().named("com1");
    model.component("comp1").common("state1").setIndex("state", "Jex_sc", 0);
    model.component("comp1").common("state1").setIndex("initialValue", "", 0);
    model.component("comp1").common("state1").setIndex("updateExpression", "", 0);
    model.component("comp1").common("state1").setIndex("description", "", 0);
    model.component("comp1").common("state1").setIndex("initialValue", 0, 0);
    model.component("comp1").common("state1").setIndex("updateExpression", "if(i_sc==0,-mf.Jix,Jex_sc)", 0);
    model.component("comp1").common("state1").setIndex("state", "Jey_sc", 1);
    model.component("comp1").common("state1").setIndex("initialValue", "", 1);
    model.component("comp1").common("state1").setIndex("updateExpression", "", 1);
    model.component("comp1").common("state1").setIndex("description", "", 1);
    model.component("comp1").common("state1").setIndex("initialValue", 0, 1);
    model.component("comp1").common("state1").setIndex("updateExpression", "if(i_sc==0,-mf.Jiy,Jey_sc)", 1);
    model.component("comp1").common("state1").setIndex("state", "Jez_sc", 2);
    model.component("comp1").common("state1").setIndex("initialValue", "", 2);
    model.component("comp1").common("state1").setIndex("updateExpression", "", 2);
    model.component("comp1").common("state1").setIndex("description", "", 2);
    model.component("comp1").common("state1").setIndex("initialValue", 0, 2);
    model.component("comp1").common("state1").setIndex("updateExpression", "if(i_sc==0,-mf.Jiz,Jez_sc)", 2);
    model.component("comp1").common("state1").set("order", 2);
    model.component("comp1").common("state1").set("valueType", "complex");
    model.component("comp1").common("state1").set("update", "afterStep");

    model.component("comp1").physics("mf").create("ecd1", "ExternalCurrentDensity", 3);
    model.component("comp1").physics("mf").feature("ecd1").label("\u7a33\u5b9a\u6027\u8865\u507f");
    model.component("comp1").physics("mf").feature("ecd1").selection().named("com1");
    model.component("comp1").physics("mf").feature("ecd1")
         .set("Je", new String[]{"if(i_sc==0,0,Jex_sc)", "if(i_sc==0,0,Jey_sc)", "if(i_sc==0,0,Jez_sc)"});
    model.component("comp1").physics("mf").feature("ecd1").set("ExternalLosses", true);

    model.study("std1").feature("freq").set("preusesol", "yes");
    model.study("std1").feature("freq").set("useparam", true);
    model.study("std1").feature("freq").setIndex("pname_aux", "Acon", 0);
    model.study("std1").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("freq").setIndex("punit_aux", "m^2", 0);
    model.study("std1").feature("freq").setIndex("pname_aux", "Acon", 0);
    model.study("std1").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("freq").setIndex("punit_aux", "m^2", 0);
    model.study("std1").feature("freq").setIndex("pname_aux", "i_sc", 0);
    model.study("std1").feature("freq").setIndex("plistarr_aux", "0, 1", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg4").run();
    model.result("pg3").run();
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().numerical("int4").setIndex("descr", "\u7edd\u7f18\u4f53\u635f\u8017 (comstab)", 0);
    model.result().table().create("tbl7", "Table");
    model.result().table("tbl7").comments("\u7edd\u7f18\u4f53\u635f\u8017");
    model.result().numerical("int4").set("table", "tbl7");
    model.result().numerical("int4").setResult();
    model.result().numerical("int1").setIndex("descr", "\u76f8\u635f\u8017 (comstab)", 0);
    model.result().numerical("int1").set("table", "tbl7");
    model.result().numerical("int1").appendResult();
    model.result().numerical("int2").setIndex("descr", "\u5c4f\u853d\u5c42\u635f\u8017 (comstab)", 0);
    model.result().numerical("int2").set("table", "tbl7");
    model.result().numerical("int2").appendResult();
    model.result().numerical("int3").setIndex("descr", "\u94e0\u88c5\u635f\u8017 (comstab)", 0);
    model.result().numerical("int3").set("table", "tbl7");
    model.result().numerical("int3").appendResult();
    model.result().numerical("gev1").setIndex("descr", "\u76f8\u4ea4\u6d41\u7535\u963b (comstab)", 0);
    model.result().numerical("gev1").set("table", "tbl7");
    model.result().numerical("gev1").appendResult();
    model.result().numerical("gev2").setIndex("descr", "\u76f8\u7535\u611f (comstab)", 0);
    model.result().numerical("gev2").set("table", "tbl7");
    model.result().numerical("gev2").appendResult();
    model.result("pg8").run();
    model.result("pg8").run();

    model.component("comp1").view("view5").set("transparency", true);
    model.component("comp1").view("view1").set("locked", false);
    model.component("comp1").view("view2").set("locked", false);
    model.component("comp1").view("view3").set("locked", false);
    model.component("comp1").view("view4").set("locked", false);
    model.component("comp1").view("view5").set("locked", false);
    model.component("comp1").view("view1").camera().set("projection", "orthographic");
    model.component("comp1").view("view1").camera().set("orthoscale", 0.32947847247123724);
    model.component("comp1").view("view2").camera().set("projection", "orthographic");
    model.component("comp1").view("view2").camera().set("orthoscale", 0.3977237939834595);
    model.component("comp1").view("view3").camera().set("projection", "orthographic");
    model.component("comp1").view("view3").camera().set("orthoscale", 0.3977237939834595);
    model.component("comp1").view("view4").camera().set("projection", "orthographic");
    model.component("comp1").view("view4").camera().set("orthoscale", 0.3555237948894501);

    model.title("\u6d77\u5e95\u7535\u7f06 8d - \u7535\u611f\u6548\u5e94\u4e09\u7ef4\u6a21\u578b");

    model
         .description("\u8fd9\u4e2a MPH \u6587\u4ef6\u8868\u793a\u5728\u201c\u7535\u611f\u6548\u5e94\u4e09\u7ef4\u6a21\u578b\u201d\u6559\u7a0b\u4e2d\u6784\u5efa\u548c\u5206\u6790\u7684\u6a21\u578b\u7684\u4e2d\u95f4\u72b6\u6001\u3002\u6709\u5173\u66f4\u591a\u4fe1\u606f\uff0c\u5305\u62ec\u8be6\u7ec6\u7684\u4ecb\u7ecd\u3001\u5206\u6b65\u64cd\u4f5c\u8bf4\u660e\u548c\u7ed3\u679c\u5206\u6790\uff08\u5305\u542b\u9a8c\u8bc1\u4ee5\u53ca\u5bf9\u5b66\u672f\u7814\u7a76\u7684\u5f15\u7528\uff09\uff0c\u8bf7\u53c2\u9605\u201c\u6848\u4f8b\u5e93\u201d\u4e2d\u672c\u6559\u7a0b\u7684\u4e3b\u5165\u53e3\u70b9\u9644\u5e26\u7684 *.pdf \u6587\u4ef6\uff1asubmarine_cable_08_inductive_effects_3d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("submarine_cable_08_d_inductive_effects_3d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    model = run3(model);
    model = run4(model);
    run5(model);
  }

}
