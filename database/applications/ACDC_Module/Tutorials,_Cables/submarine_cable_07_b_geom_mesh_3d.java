/*
 * submarine_cable_07_b_geom_mesh_3d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:24 by COMSOL 6.3.0.290. */
public class submarine_cable_07_b_geom_mesh_3d {

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

    model.mesh().clearMeshes();

    model.label("submarine_cable_07_b_geom_mesh_3d.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
